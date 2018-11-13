package tools4web;

import java.net.URI;
import java.net.URISyntaxException;

import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Captura sessao do usuario corrente
 * 
 * @author Wendel F. Lopes
 *
 */

public class ContextoUtil {

	public static ContextoBean getContextoBean() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);

		ContextoBean contextoBean = (ContextoBean) session
				.getAttribute("contextoBean");

		return contextoBean;
	}

	public static String getRequestURL() {

		Object request = FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		if (request instanceof HttpServletRequest) {
			return ((HttpServletRequest) request).getRequestURL().toString();
		} else {
			return "";
		}
	}

	public static String getApplicationUri() {
		  try {
		    FacesContext ctxt = FacesContext.getCurrentInstance();
		    ExternalContext ext = ctxt.getExternalContext();
		    URI uri = new URI(ext.getRequestScheme(),
		          null, ext.getRequestServerName(), ext.getRequestServerPort(),
		          ext.getRequestContextPath(), null, null);
		    return uri.toASCIIString();
		  } catch (URISyntaxException e) {
		    throw new FacesException(e);
		  }
		}
}
