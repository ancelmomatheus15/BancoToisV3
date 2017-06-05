package tois.control;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tois.DAO.ContaDAO;
import tois.entity.Conta;

@SessionScoped
@ManagedBean
public class ContaMB {
	
	private String auxUser = "";
	private String auxSenha = "";
	
	private Conta curConta = new Conta();
	
	
	public void setConta(Conta conta) {
		this.curConta = conta;
	}	
	
	public Conta getConta() {
		return curConta;
	}

	public String login() {
		String pagina = "login";
		curConta = ContaDAO.validar(auxUser, auxSenha);
		if (curConta != null) {
			pagina = "conta?faces-redirect=true";
		} else {
			curConta = new Conta();
			pagina = "login";
		}

		return pagina;
	}
	
	public String salvar(){
		ContaDAO.adicionar(curConta);
		return "login";
	}
	
	public String sair(){
		curConta = null;
		return "login";
		
	}
	
	public String getAuxUser() {
		return auxUser;
	}

	public void setAuxUser(String auxUser) {
		this.auxUser = auxUser;
	}

	public String getAuxSenha() {
		return auxSenha;
	}

	public void setAuxSenha(String auxSenha) {
		this.auxSenha = auxSenha;
	}

	public Conta getCurConta() {
		return curConta;
	}

	public void setCurConta(Conta curConta) {
		this.curConta = curConta;
	}

	
}

