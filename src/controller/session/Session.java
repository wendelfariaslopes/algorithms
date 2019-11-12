/**
 * 
 */
package controller.session;

import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

/**
 * @author Wendel F. Lopes
 *
 */
public class Session {
	
//	public static <T> Object getObjectSession(String attribute){		
//		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
//		HttpSession session = request.getSession(true);  
//		return session.getAttribute(attribute);				
//	}
//	
//	public static String logout(){  
//        FacesContext facesContext = FacesContext.getCurrentInstance();  
//        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);  
//        //Aluno al2 = (Aluno) session.getAttribute("aluno");
//        session.invalidate();
//	    return "index.xhtml";  
//	 }
//	
//	 /** Identifica o endereço IP remoto*/
//    public static String getIP() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//        return request.getRemoteAddr();
//    }
//    
//    /** Identifica o ID da sessão */
//    public static String getIDSession() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
//        return session.getId();
//    }

}
