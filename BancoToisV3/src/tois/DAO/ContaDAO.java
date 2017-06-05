package tois.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import tois.entity.Conta;

public class ContaDAO {

	public static void adicionar(Conta curConta) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BANCO");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(curConta);
		em.getTransaction().commit();
		em.clear();
		factory.close();
		
	}

	public static Conta lerConta(String numConta) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BANCO");
		EntityManager em = factory.createEntityManager();
		Conta c = em.find(Conta.class, numConta);
		if (c != null) {
			return c;
		}
		return null;
	}

	public static void atualizarConta(Conta contaTo) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BANCO");
		EntityManager em = factory.createEntityManager();
		try {
			em.refresh(contaTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Conta validar(String auxUser, String auxSenha) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BANCOTOIS");
		EntityManager em = factory.createEntityManager();
		try {
			Query query = em
					.createQuery("select c from Conta c where " 
							   + "conta = :acc " 
							   + "and senha = :pass");
			
			query.setParameter("acc", auxUser);
			query.setParameter("pass", auxUser);
			List<Conta> contas = query.getResultList();
			if (contas != null && contas.size() > 0) {
				return contas.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



}
