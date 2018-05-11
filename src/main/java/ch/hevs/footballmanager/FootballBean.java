package ch.hevs.footballmanager;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Characteristics;
import ch.hevs.businessobject.Club;
import ch.hevs.businessobject.Contract;
import ch.hevs.businessobject.League;
import ch.hevs.businessobject.Person;
import ch.hevs.businessobject.Player;
import ch.hevs.businessobject.President;
import ch.hevs.businessobject.Trainer;

@Stateful
public class FootballBean implements Football{
	
	@PersistenceContext(name = "FootPU",type=PersistenceContextType.EXTENDED)
	private EntityManager em;

	@Override
	public League getLeagueById(long id) {
		// TODO Auto-generated method stub
		return (League) em.createQuery("FROM Ligue l WHERE l.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public League getLeagueByName(String name) {
		// TODO Auto-generated method stub
		return (League) em.createQuery("FROM Ligue l WHERE l.nom=:name").setParameter("name", name).getSingleResult();
	}

	@Override
	public League getLeagueByNationality(String nationality) {
		// TODO Auto-generated method stub
		return (League) em.createQuery("FROM Ligue l WHERE l.nationalité=:nationality").setParameter("nationality", nationality).getSingleResult();
	}

	@Override
	public Account getAccountById(long id) {
		// TODO Auto-generated method stub
		return (Account) em.createQuery("FROM Compte c WHERE c.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public Club getClubById(long id) {
		// TODO Auto-generated method stub
		return (Club) em.createQuery("FROM Club cl WHERE cl.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public Club getClubByName(String name) {
		// TODO Auto-generated method stub
		return (Club) em.createQuery("FROM Club cl WHERE cl.nom=:name").setParameter("name", name).getSingleResult();
	}

	@Override
	public List<Player> getTitularPlayersFromClub(Club club) {
		// TODO Auto-generated method stub
		return (List<Player>) em.createQuery("SELECT cl.players FROM Club cl WHERE cl.id=:id").setParameter("id", club.getId()).getResultList();
	}

	@Override
	public List<Person> getPersonsByLastname(String lastname) {
		// TODO Auto-generated method stub
		return (List<Person>) em.createQuery("FROM Personne p WHERE p.nom=:lastname").setParameter("lastname", lastname).getResultList();
	}

	@Override
	public List<Person> getPersonsByFirstname(String firstname) {
		// TODO Auto-generated method stub
		return (List<Person>) em.createQuery("FROM Personne p WHERE p.prénom=:firstname").setParameter("firstname", firstname).getResultList();
	}

	@Override
	public List<Person> getPersonsByNationality(String nationality) {
		// TODO Auto-generated method stub
		return (List<Person>) em.createQuery("FROM Personne p WHERE p.nationalité=:nationality").setParameter("nationality", nationality).getResultList();
	}

	@Override
	public Player getPlayerById(long id) {
		// TODO Auto-generated method stub
		return (Player) em.createQuery("FROM Joueur j WHERE j.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public Trainer getTrainerById(long id) {
		// TODO Auto-generated method stub
		return (Trainer) em.createQuery("FROM Entraineur e WHERE e.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public President getPresidentById(long id) {
		// TODO Auto-generated method stub
		return (President) em.createQuery("FROM Président p WHERE p.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public List<Player> getPlayers() {
		// TODO Auto-generated method stub
		return (List<Player>) em.createQuery("FROM Joueur").getResultList();
	}

	@Override
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return (List<Account>) em.createQuery("FROM Compte").getResultList();
	}

	@Override
	public List<Club> getClubs() {
		// TODO Auto-generated method stub
		return (List<Club>) em.createQuery("FROM Club").getResultList();
	}

	@Override
	public List<League> getLeagues() {
		// TODO Auto-generated method stub
		return (List<League>) em.createQuery("FROM Ligue").getResultList();
	}

	@Override
	public List<Trainer> getTrainers() {
		// TODO Auto-generated method stub
		return (List<Trainer>) em.createQuery("FROM Entraineur").getResultList();
	}

	@Override
	public Account getAccountByPlayerId(Person player) {
		// TODO Auto-generated method stub
		return (Account) em.createQuery("FROM Compte c WHERE c.owner=:player").setParameter("player", player).getSingleResult();
	}

	@Override
	public void populate() {
		// TODO Auto-generated method stub
		Contract cont1 = new Contract();
		cont1.setBeginningDate(new Date(2000,01,12));
		cont1.setEndDate(new Date(2000,12,12));
		cont1.setSalary(100000);
		em.persist(cont1);

		Contract cont2 = new Contract();
		cont2.setBeginningDate(new Date(2010,01,01));
		cont2.setEndDate(new Date(2018,01,12));
		cont2.setSalary(500000);
		em.persist(cont2);

		Trainer p2 = new Trainer();
		p2.setFirstname("Kevin");
		p2.setLastname("Berret");
		p2.setNationality("Swiss");
		p2.setContract(cont2);
		em.persist(p2);

		President p3 = new President();
		p3.setFirstname("Benjamin");
		p3.setLastname("Décaillet");
		p3.setNationality("Swiss");
		em.persist(p3);

		Account a3 = new Account();
		a3.setSaldo(30000000);
		
		League l1 = new League();
		l1.setName("Professional");
		l1.setNationality("Swiss");
		
		Club club1 = new Club();
		club1.setName("FC Bâle");
		club1.setNationality("Swiss");
		club1.setPresident(p3);
		club1.setTrainer(p2);
		club1.setAccount(a3);
		club1.setLeague(l1);
		em.persist(club1);
		
		l1.addClub(club1);
		em.persist(l1);

		Player p1 = new Player();
		p1.setFirstname("Mike");
		p1.setLastname("Wigger");
		p1.setNationality("Swiss");
		p1.setTitular(true);
		p1.setContract(cont1);
		p1.setCharacteristics(new Characteristics(18, 11, 5));
		p1.setClub(club1);
		em.persist(p1);
		
		Account a1 = new Account();
		a1.setOwner(p3);
		a1.setSaldo(15000000);
		em.persist(a1);

		Account a2 = new Account();
		a2.setOwner(p1);
		a2.setSaldo(5000000);
		em.persist(a2);
	}
	
	public void transfer(Account srcAccount, Account destAccount, int amount) {
		
//		em.persist(srcAccount);
//		em.persist(destAccount);
//		srcAccount.debit(amount);
//		destAccount.credit(amount);
		
		//Meilleure solution
		Account srcRealAccount = em.merge(srcAccount);
		Account destRealAccount = em.merge(destAccount);
		srcRealAccount.debit(amount);
		destRealAccount.credit(amount);
	}

}
