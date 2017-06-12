package tois.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="conta")
public class Conta {
	
	private String agencia = "1507-96";
	
	private int conta = genNumAcc();
	private String nome = "";
	private String CPF = "";
	private String RG = "";
	private double saldo = 0;

	@NotEmpty(message="Selecione uma op��o")
	private String emprego = "";
	
	@NotEmpty(message="Selecione uma op��o")
	private String tipoConta = "";
	
	@NotEmpty(message="Escolha uma senha")
	private String senha = "";
	//------------------------------------------------

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	@Id
	@GeneratedValue
	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getEmprego() {
		return emprego;
	}

	public void setEmprego(String emprego) {
		this.emprego = emprego;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public int genNumAcc(){
		
		int i = (int) (100000 + Math.random() * 89999l);
		
		return i;
	}

}
