package tois.control;

import java.text.ParseException;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import tois.DAO.*;
import tois.entity.Conta;
import tois.entity.Transacao;

@RequestScoped
@ManagedBean
public class TransacaoMB {
	
	private Transacao curTransacao = new Transacao();

	public Transacao getCurTransacao() {
		return curTransacao;
	}

	public void setCurTransacao(Transacao curTransacao) {
		this.curTransacao = curTransacao;
	}
	
	public void transferir(){
		FacesContext context = FacesContext.getCurrentInstance();
		Conta c = (Conta) context.getApplication().evaluateExpressionGet(context, "#{contaMB.conta}", Conta.class);
		String numContaOrigem = String.valueOf(c.getConta());
		
		if (curTransacao.getContaOrigem().equals(numContaOrigem)) {
			//mostrar erro de conta igual
		} else {
			
			Conta contaTo = ContaDAO.lerConta(curTransacao.getContaDestino());
			
			if (contaTo != null) {
				if (curTransacao.getValor() <= c.getSaldo()) {
					double auxTransacao = curTransacao.getValor() + contaTo.getSaldo();
					ContaDAO.atualizarConta(contaTo);
					curTransacao.setTipo("Transferência");
					
					TransacaoDAO.addTransacao(curTransacao);
					
				} else {
					// Mensagem de saldo insuficiente
				}

			} else {
				// Mensagem de conta não existente
			}
		}
	}
	
	public void depositar(){		
		FacesContext context = FacesContext.getCurrentInstance();
		
		Conta c = (Conta) context.getApplication().evaluateExpressionGet(context, "#{contaMB.conta}", Conta.class);
		c.setSaldo(c.getSaldo() +curTransacao.getValor());
		ContaDAO.atualizarConta(c);
		
		curTransacao.setContaOrigem(String.valueOf(c.getConta()));
		curTransacao.setContaDestino(String.valueOf(c.getConta()));
		curTransacao.setTipo("Depósito");
		
		TransacaoDAO.addTransacao(curTransacao);
	}


}
