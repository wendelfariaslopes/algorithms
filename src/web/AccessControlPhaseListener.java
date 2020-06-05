package web;

import static web.AccessControlPhaseListener.AccessLevel.ADMIN;
import static web.AccessControlPhaseListener.AccessLevel.LOGGED_IN;
import static web.AccessControlPhaseListener.AccessLevel.NONE;
import static web.AccessControlPhaseListener.AccessLevel.USER_ACTIVE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.sun.faces.util.MessageFactory;

/**
 * Phase Listener that checks the viewId (URL) against a set of filters to
 * determine the required access level. If the correct level is not there then
 * redirect.
 * 
 * See {@link UrlFilter} for details on the url matching.
 * 
 * @author Chris Watts 2009
 * 
 */
public class AccessControlPhaseListener implements PhaseListener {

	/** */
	private static final long serialVersionUID = 1L;
	private final static String SESSION_BEAN = "username";
	private final HashMap<AccessLevel, List<UrlFilter>> levelFilters = new HashMap<AccessLevel, List<UrlFilter>>();

	public enum AccessLevel {
		NONE, LOGGED_IN, USER_ACTIVE, ADMIN;
	}

	/**
	* 
	*/
	public AccessControlPhaseListener() {
		initLevels();

		requires(LOGGED_IN).include("*").exclude("/index.xhtml").exclude("/login.xhtml").exclude("/user/newUser.xhtml");

		requires(USER_ACTIVE).include("/user/*").exclude("/user/newUser.xhtml");

		requires(ADMIN).include("/admin/*");
	}

	private void initLevels() {
		AccessLevel[] levels = AccessLevel.values();
		for (int i = 1; i < levels.length; i++) {
			levelFilters.put(levels[i], new ArrayList<UrlFilter>());
		}
	}

	private UrlFilter requires(AccessLevel level) {
		// ALL is default
		if (level == NONE)
			return null;

		UrlFilter filter = new UrlFilter();
		List<UrlFilter> list = levelFilters.get(level);
		list.add(filter);
		return filter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	public void afterPhase(PhaseEvent event) {
		try {
			// check have correct access
			FacesContext context = event.getFacesContext();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			//SessionForm sessionBean = (SessionForm) session.getAttribute(SESSION_BEAN);

			//LoginView loginView = (LoginView) (HttpServletRequest) session.getAttribute("loginView");//((HttpServletRequest) request).getSession()

//			if (loginView.getSession() == null) {
//				System.out.println("Could not obtain instance of sessionBean");
//				return;
//			}

			// can't use this here. only valid at render response phase?
			String viewId = context.getViewRoot().getViewId();
			AccessLevel required = requiredLevel(viewId);
			System.out.println("Required level={} for viewId={} " + required + " " + viewId);

			// check if page require access:
			switch (required) {
			case NONE:
				break;
			case LOGGED_IN:
//				if (!loginView.isLoggedIn())
//					redirectLogin(event.getFacesContext());
				break;
//			case USER_ACTIVE:
//				if (!sessionBean.isActive())
//					redirectActive(event.getFacesContext());
//				break;
//			case ADMIN:
//				if (!sessionBean.isAdmin())
//					redirectAdmin(event.getFacesContext());
//				break;
			default:
				// error
				throw new IllegalArgumentException("Not a valid access level");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("beforePhase caught exception " + e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 */
	public void beforePhase(PhaseEvent event) {

	}

	private void redirectLogin(FacesContext context) {
		// trigger login popup to be shown on render.
		addError(context, "access.loginrequired");
		context.getApplication().getNavigationHandler().handleNavigation(context, null, "index");
	}

	private void redirectActive(FacesContext context) {
		addError(context, "access.activerequired");
		context.getApplication().getNavigationHandler().handleNavigation(context, null, "userActivate");
	}

	private void redirectAdmin(FacesContext context) {
		addError(context, "access.adminrequired");
		context.getApplication().getNavigationHandler().handleNavigation(context, null, "home");
	}

	/**
	 * Add keyed error/message.
	 * 
	 * @param level
	 * @param key   message key
	 */
	private void addError(FacesContext context, String key) {
		FacesMessage fMessage = MessageFactory.getMessage(key);
		if (fMessage != null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			fMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(null, fMessage);
		}
	}

	/**
	 * Checks defined filters for view id, checks starting at the highest level down
	 * to NONE.
	 * 
	 * @return the matching level or {@link AccessLevel#NONE} if none matching.
	 */
	private AccessLevel requiredLevel(String viewId) {
		AccessLevel[] levels = AccessLevel.values();
		for (int i = levels.length - 1; i > 0; i--) {
			if (checkLevel(levels[i], viewId))
				return levels[i];
		}

		return AccessLevel.NONE;
	}

	private boolean checkLevel(AccessLevel level, String viewId) {
		return matchUri(levelFilters.get(level), viewId);
	}

	private boolean matchUri(List<UrlFilter> list, String uri) {
		for (UrlFilter filter : list) {
			if (filter.matches(uri))
				return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	public PhaseId getPhaseId() {
		// ALL access go through RESTORE_VIEW and RENDER_VIEW (even direct url)
		return PhaseId.RESTORE_VIEW;
	}

}
