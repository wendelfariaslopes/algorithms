package tools4web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;



@ManagedBean(name = "contextoBean")
@SessionScoped
public class ContextoBean {
	/*
	private Usuario usuarioLogado = null;

	private String confirmaSenha;
	
	public ContextoBean(){
		this.usuarioLogado = null;
	}

	public Usuario getUsuarioLogado() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();

		String login = external.getRemoteUser();

		if (login != null) {

			this.usuarioLogado = UsuarioRN.buscarPorCpf(login);

		}

		return this.usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public String getRequestURL() {
		return ContextoUtil.getRequestURL();
	}
	
	public void salvar() {
	 
		 String senha = this.usuarioLogado.getSenha();

			if (!senha.equals(this.confirmaSenha)) {
				JsfUtil.addGlobalMsgError("growl", "AVISO",
								"AS SENHAS NAO CONFEREM! TENTE NOVAMENTE.");
			   return;
			}
			
		try{
			
			this.usuarioLogado.setAtivo(true);
			
			if (UsuarioRN.salvar(this.usuarioLogado)) {
						
				JsfUtil.addGlobalMsgInfo("growl", "INFORMACAO",
								"Sua senha foi salva com sucesso!");

			} else {
				JsfUtil.addGlobalMsgError(JsfUtil.ID_NULL, "AVISO",
								"O InfObras identificou um erro ao tentar salvar.");
						
				JsfUtil.addGlobalMsgError(JsfUtil.ID_NULL, "TENTE NOVAMENTE",
								"OU FALE COM O ADMISTRADOR DO INFOBRAS");
			}
			
		}catch (Exception e) {
			
			JsfUtil.addGlobalErrorMessageExcecao(e,
					"O InfObras identificou um erro ao tentar salvar.");
			e.printStackTrace();
		}
				
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	*/
}
