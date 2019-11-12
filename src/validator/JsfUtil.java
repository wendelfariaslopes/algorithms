package validator;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
//import javax.servlet.http.HttpSession;

/**
 * Classe Responsavel pela infraestrutura de utilidades gerais da camada de
 * apresentacao (JSF).
 * 
 * @author Wendel F. Lopes
 * 
 */

public class JsfUtil {

	public static final String ID_NULL = null;

	/**
	 * 
	 * 
	 * @author Wendel F. Lopes
	 */

	public static void addGlobalErrorMessageExcecao(Exception ex,
			String defaultMsg) {

		String msg = ex.getLocalizedMessage();

		if ((msg != null) && (msg.length() > 0)) {

			addGlobalMsgError(null, "Aviso", msg);

		} else {

			addGlobalMsgError(null, "Aviso", defaultMsg);

		}

	}

	public static void addGlobalErrorMessages(List<String> messages) {

		for (String message : messages) {

			addGlobalMsgError(null, "Aviso", message);

		}

	}

	private static void mensagem(String idComponente, String tituloMensagem,
			String mensagem, Severity severity) {
		FacesContext.getCurrentInstance().addMessage(idComponente,
				new FacesMessage(severity, tituloMensagem, mensagem));
	}

	public static void addGlobalMsgInfo(String idComponente,
			String tituloMensagem, String mensagem) {
		JsfUtil.mensagem(idComponente, tituloMensagem, mensagem,
				FacesMessage.SEVERITY_INFO);
	}

	public static void addGlobalMsgError(String idComponente,
			String tituloMensagem, String mensagem) {
		JsfUtil.mensagem(idComponente, tituloMensagem, mensagem,
				FacesMessage.SEVERITY_ERROR);
	}

	public static void addGlobalMsgWarn(String idComponente,
			String tituloMensagem, String mensagem) {
		JsfUtil.mensagem(idComponente, tituloMensagem, mensagem,
				FacesMessage.SEVERITY_WARN);
	}

	public static void addGlobalMsgFatal(String idComponente,
			String tituloMensagem, String mensagem) {
		JsfUtil.mensagem(idComponente, tituloMensagem, mensagem,
				FacesMessage.SEVERITY_FATAL);
	}

	/**
	 * 
	 * 
	 * 
	 * @author Wendel F. Lopes
	 */
	public static String getRequestParameter(String key) {

		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(key);

	}

	public static Object getObjectFromRequestParameter(
			String requestParameterName, Converter converter,

			UIComponent component) {

		String theId = JsfUtil.getRequestParameter(requestParameterName);

		return converter.getAsObject(FacesContext.getCurrentInstance(),
				component, theId);

	}

	public static Object getMethod(Object obj, String name) throws Exception {

		Method createMethod = obj.getClass().getMethod(name, new Class[0]);

		return createMethod.invoke(obj, new Object[0]);

	}

//	public static HttpSession getSession() {
//
//		FacesContext ctx = FacesContext.getCurrentInstance();
//
//		HttpSession session = (HttpSession) ctx.getExternalContext()
//				.getSession(false);
//
//		return session;
//
//	}

	public static void setAttribute(String valorObjeto, Object tipoObjeto) {

		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(valorObjeto, tipoObjeto);

	}

	public static Object getAttribute(String valorObjeto) {

		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().get(valorObjeto);

	}

	public static String geti18nMsg(ResourceBundle bundle, String msgKey,
			String paramValue) {
		String msgValue = bundle.getString(msgKey);
		MessageFormat messageFormat = new MessageFormat(msgValue);
		Object[] args = { paramValue };
		return messageFormat.format(args);
	}

}
