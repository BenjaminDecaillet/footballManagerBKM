package ch.hevs.footballmanager;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
import ch.hevs.managedbeans.PersonBean;

@Stateless
public class FootballBean implements Football{
	
	@PersistenceContext(name = "FootPU")
	private EntityManager em;

	@Override
	public League getLeagueById(long id) {
		// TODO Auto-generated method stub
		return (League) em.createQuery("FROM League l WHERE l.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public League getLeagueByName(String name) {
		// TODO Auto-generated method stub
		return (League) em.createQuery("FROM League l WHERE l.name=:name").setParameter("name", name).getSingleResult();
	}

	@Override
	public League getLeagueByNationality(String nationality) {
		// TODO Auto-generated method stub
		return (League) em.createQuery("FROM League l WHERE l.nationality=:nationality").setParameter("nationality", nationality).getSingleResult();
	}

	@Override
	public Account getAccountById(long id) {
		// TODO Auto-generated method stub
		return (Account) em.createQuery("FROM Account c WHERE c.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public Club getClubById(long id) {
		// TODO Auto-generated method stub
		return (Club) em.createQuery("FROM Club cl WHERE cl.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public Club getClubByName(String name) {
		// TODO Auto-generated method stub
		return (Club) em.createQuery("FROM Club cl WHERE cl.name=:name").setParameter("name", name).getSingleResult();
	}

	@Override
	public List<Player> getTitularPlayersFromClub(Club club) {
		// TODO Auto-generated method stub
		return (List<Player>) em.createQuery("SELECT cl.players FROM Club cl WHERE cl.id=:id").setParameter("id", club.getId()).getResultList();
	}

	@Override
	public List<Person> getPersonsByLastname(String lastname) {
		// TODO Auto-generated method stub
		return (List<Person>) em.createQuery("FROM Person p WHERE p.lastname=:lastname").setParameter("lastname", lastname).getResultList();
	}

	@Override
	public List<Person> getPersonsByFirstname(String firstname) {
		// TODO Auto-generated method stub
		return (List<Person>) em.createQuery("FROM Person p WHERE p.firstname=:firstname").setParameter("firstname", firstname).getResultList();
	}

	@Override
	public List<Person> getPersonsByNationality(String nationality) {
		// TODO Auto-generated method stub
		return (List<Person>) em.createQuery("FROM Person p WHERE p.nationality=:nationality").setParameter("nationality", nationality).getResultList();
	}

	@Override
	public Player getPlayerById(long id) {
		// TODO Auto-generated method stub
		return (Player) em.createQuery("FROM Player j WHERE j.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public Trainer getTrainerById(long id) {
		// TODO Auto-generated method stub
		return (Trainer) em.createQuery("FROM Trainer e WHERE e.id=:id").setParameter("id", id).getSingleResult();
	}
	
	@Override
	public List<Trainer> getTrainersWithoutJob() {
		// TODO Auto-generated method stub
		return (List<Trainer>) em.createQuery("FROM Trainer e WHERE e.contract.beginningDate IS NULL AND e.contract.endDate IS NULL").getResultList();
	}

	@Override
	public President getPresidentById(long id) {
		// TODO Auto-generated method stub
		return (President) em.createQuery("FROM President p WHERE p.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public List<Player> getPlayers() {
		// TODO Auto-generated method stub
		return (List<Player>) em.createQuery("FROM Player").getResultList();
	}

	@Override
	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return (List<Account>) em.createQuery("FROM Account").getResultList();
	}

	@Override
	public List<Club> getClubs() {
		// TODO Auto-generated method stub
		return (List<Club>) em.createQuery("FROM Club").getResultList();
	}

	@Override
	public List<League> getLeagues() {
		// TODO Auto-generated method stub
		return (List<League>) em.createQuery("FROM League").getResultList();
	}

	@Override
	public List<Trainer> getTrainers() {
		// TODO Auto-generated method stub
		return (List<Trainer>) em.createQuery("FROM Trainer").getResultList();
	}

	@Override
	public Account getAccountByPlayerId(Person player) {
		// TODO Auto-generated method stub
		return (Account) em.createQuery("FROM Account c WHERE c.owner=:player").setParameter("player", player).getSingleResult();
	}
	
	public Person login(String firstname,String lastname) {
		President pres = null;
		Trainer train=null;
		try {
			pres = (President) em.createQuery("FROM President p WHERE p.firstname=:firstname AND p.lastname=:lastname")
					.setParameter("firstname", firstname)
					.setParameter("lastname", lastname)
					.getSingleResult();	
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        if(pres!= null)
        	return (Person) pres;
        else
        	try {
        		train = (Trainer) em.createQuery("FROM Trainer p WHERE p.firstname=:firstname AND p.lastname=:lastname")
        				.setParameter("firstname", firstname)
        				.setParameter("lastname", lastname)
        				.getSingleResult();
			} catch (Exception e) {
				// TODO: handle exception
			}
        	
        if(train!= null)
        	return train;
        
        return null;
	}	

	@Override
	public void populate() {
		Trainer p2 = new Trainer();
		p2.setFirstname("Kevin");
		p2.setLastname("Berret");
		p2.setNationality("Swiss");
		p2.setContract(new Contract(LocalDate.of(2010, 01, 01), LocalDate.of(2018, 12, 01), 500000));
		em.persist(p2);

		President p3 = new President();
		p3.setFirstname("Benjamin");
		p3.setLastname("Decaillet");
		p3.setNationality("Swiss");
		em.persist(p3);
		
		League l1 = new League();
		l1.setName("Professional");
		l1.setNationality("Swiss");
		
		Club club1 = new Club();
		club1.setName("FC Bâle");
		club1.setNationality("Swiss");
		club1.setPresident(p3);
		club1.setTrainer(p2);
		club1.setAccountClub(new Account(30000000, club1));
		club1.setLeague(l1);
		em.persist(club1);
		
		l1.addClub(club1);
		em.persist(l1);

		Player p1 = new Player();
		p1.setFirstname("Mike");
		p1.setLastname("Wigger");
		p1.setNationality("Swiss");
		p1.setTitular(true);
		p1.setContract(new Contract(LocalDate.of(2018, 01, 01), LocalDate.of(2019, 03, 31), 100000));
		p1.setCharacteristics(new Characteristics(18, 11, 5));
		p1.setClub(club1);
		p1.setAccount(new Account(7777777,p1));
		em.persist(p1);
	}
	
	@Override
	public void transfer(Account srcAccount, Account destAccount, int amount) {
		// TODO Auto-generated method stub
		
		//Meilleure solution
		Account srcRealAccount = em.merge(srcAccount);
		Account destRealAccount = em.merge(destAccount);
		srcRealAccount.debit(amount);
		destRealAccount.credit(amount);
	}
	
	
	/**
	 * 
	 * PLAYER's methods
	 * 
	 */
	
	@Override
	public void newPlayer(Player newPlayerObj){
		// TODO Auto-generated method stub	
		em.merge(newPlayerObj);
	}
	
	@Override
	public void updatePlayer(Player updatedPlayerObj) {
		// TODO Auto-generated method stub
		em.merge(updatedPlayerObj);
	}
	
	@Override
	public void removePlayer(Player player) {
		// TODO Auto-generated method stub
		em.remove(em.contains(player) ? player : em.merge(player));
	}
	
	/**
	 * 
	 * TRAINER's methods
	 * 
	 */
	@Override
	public void newTrainer(Trainer newTrainerObj) {
		// TODO Auto-generated method stub
		em.persist(newTrainerObj);
	}
	
	@Override
	public void updateTrainer(Trainer updatedTrainerObj) {
		// TODO Auto-generated method stub
		em.merge(updatedTrainerObj);
	}
	
	@Override
	public void removeTrainer(Trainer trainer) {
		em.remove(em.contains(trainer) ? trainer : em.merge(trainer));
	}

	/**
	 * 
	 * PRESIDENT's methods
	 * 
	 */	
	
	@Override
	public void newPresident(PersonBean newPresidentObj) {
		// TODO Auto-generated method stub
		em.persist(newPresidentObj);	
	}

	@Override
	public void updatePresident(PersonBean updatedPresidentObj) {
		// TODO Auto-generated method stub
		em.merge(updatedPresidentObj);
	}

	@Override
	public void removePresident(President president) {
		// TODO Auto-generated method stub
		em.remove(em.contains(president) ? president : em.merge(president));
	}

	/*
	 * 
	 * Club methods
	 * 
	 */
	@Override
	public void newClub(Club newClubObj) {
		em.merge(newClubObj);
	}

	@Override
	public void updateClub(Club updatedClubObj) {
		em.merge(updatedClubObj);
	}

	@Override
	public void removeClub(Club club) {
		em.remove(em.contains(club) ? club : em.merge(club));		
	}	
}

