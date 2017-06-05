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

	public String logar() {
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

	
}

