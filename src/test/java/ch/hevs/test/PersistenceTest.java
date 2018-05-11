package ch.hevs.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Characteristics;
import ch.hevs.businessobject.Club;
import ch.hevs.businessobject.Contract;
import ch.hevs.businessobject.League;
import ch.hevs.businessobject.Player;
import ch.hevs.businessobject.President;
import ch.hevs.businessobject.Trainer;

public class PersistenceTest {

	@Test
	public void test() {
		EntityTransaction tx = null;
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("footPU");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Contract cont1 = new Contract();
			cont1.setBeginningDate(new Date());
			cont1.setEndDate(new Date());
			cont1.setSalary(100000);
			em.persist(cont1);

			Contract cont2 = new Contract();
			cont2.setBeginningDate(new Date());
			cont2.setEndDate(new Date());
			cont2.setSalary(500000);
//			em.persist(cont2);

			Player p1 = new Player();
			p1.setFirstname("Mike");
			p1.setLastname("Wigger");
			p1.setNationality("Swiss");
			p1.setTitular(true);
			p1.setContract(cont1);
			p1.setCharacteristics(new Characteristics(18, 11, 5));
//			em.persist(p1);

			Trainer p2 = new Trainer();
			p2.setFirstname("Kevin");
			p2.setLastname("Berret");
			p2.setNationality("Swiss");
			p2.setContract(cont2);
//			em.persist(p2);

			President p3 = new President();
			p3.setFirstname("Benjamin");
			p3.setLastname("Décaillet");
			p3.setNationality("Swiss");
//			em.persist(p3);

			Club club1 = new Club();
			club1.setName("FC Bâle");
			club1.setNationality("Swiss");
			club1.setPresident(p3);
			club1.setTrainer(p2);
//			em.persist(club1);

			Account a1 = new Account();
			a1.setOwner(p3);
			a1.setSaldo(15000000);
//			em.persist(a1);

			Account a2 = new Account();
			a2.setOwner(p1);
			a2.setSaldo(5000000);
//			em.persist(a2);

			League l1 = new League();
			l1.setName("Professional");
			l1.setNationality("Swiss");
			l1.addClub(club1);
			
			
			em.persist(l1);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				tx.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
		}

	}

}
