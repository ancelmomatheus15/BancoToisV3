package tois.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tois.entity.Transacao;

public class TransacaoDAO {

	public static void addTransacao(Transacao curTransacao) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("BANCO");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(curTransacao);
		em.getTransaction().commit();
		em.clear();
		factory.close();
		
	}

}
