package ch.hevs.footballmanager;

import java.time.LocalDate;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Characteristics;
import ch.hevs.businessobject.Club;
import ch.hevs.businessobject.Contract;
import ch.hevs.businessobject.League;
import ch.hevs.businessobject.Person;
import ch.hevs.businessobject.Player;
import ch.hevs.businessobject.President;
import ch.hevs.businessobject.Trainer;
import ch.hevs.exception.TransferException;
import ch.hevs.managedbeans.PersonBean;

@Stateless
@RolesAllowed(value = { "president", "trainer" })
public class FootballBean implements Football{
	
	@PersistenceContext(name = "FootPU")
	private EntityManager em;

	@Override
	public League getLeagueById(long id) {		
		return (League) em.createQuery("FROM League l WHERE l.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public League getLeagueByName(String name) {		
		return (League) em.createQuery("FROM League l WHERE l.name=:name").setParameter("name", name).getSingleResult();
	}

	@Override
	public League getLeagueByNationality(String nationality) {		
		return (League) em.createQuery("FROM League l WHERE l.nationality=:nationality").setParameter("nationality", nationality).getSingleResult();
	}

	@Override
	public Account getAccountById(long id) {		
		return (Account) em.createQuery("FROM Account c WHERE c.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public Club getClubById(long id) {
		try{
			return (Club) em.createQuery("FROM Club cl WHERE cl.id=:id").setParameter("id", id).getSingleResult();
		}catch(NoResultException e) {
	        return null;
	    }
	}

	@Override
	public Club getClubByName(String name) {		
		return (Club) em.createQuery("FROM Club cl WHERE cl.name=:name").setParameter("name", name).getSingleResult();
	}
	
	@Override
	public List<Club> getOtherClubsThanCurent(Player player) {
		return (List<Club>) em.createQuery("FROM Club cl WHERE cl.id!=:id").setParameter("id", player.getClub().getId()).getResultList();
	}

	@Override
	public List<Player> getTitularPlayersFromClub(Club club) {		
		return (List<Player>) em.createQuery("SELECT cl.players FROM Club cl WHERE cl.id=:id").setParameter("id", club.getId()).getResultList();
	}

	@Override
	public List<Person> getPersonsByLastname(String lastname) {		
		return (List<Person>) em.createQuery("FROM Person p WHERE p.lastname=:lastname").setParameter("lastname", lastname).getResultList();
	}

	@Override
	public List<Person> getPersonsByFirstname(String firstname) {		
		return (List<Person>) em.createQuery("FROM Person p WHERE p.firstname=:firstname").setParameter("firstname", firstname).getResultList();
	}

	@Override
	public List<Person> getPersonsByNationality(String nationality) {		
		return (List<Person>) em.createQuery("FROM Person p WHERE p.nationality=:nationality").setParameter("nationality", nationality).getResultList();
	}

	@Override
	public Player getPlayerById(long id) {
		try{
			return (Player) em.createQuery("FROM Player j WHERE j.id=:id").setParameter("id", id).getSingleResult();
		}catch(NoResultException e) {
	        return null;
	    }
	}

	@Override
	public Trainer getTrainerById(long id) {		
		try{
			return (Trainer) em.createQuery("FROM Trainer e WHERE e.id=:id").setParameter("id", id).getSingleResult();
		}catch(NoResultException e) {
	        return null;
	    }
	}
	
	@Override
	public List<Trainer> getTrainersWithoutJob() {		
		return (List<Trainer>) em.createQuery("FROM Trainer e WHERE e.contract.beginningDate IS NULL AND e.contract.endDate IS NULL").getResultList();
	}
	
	@Override
	public List<Club> getClubsWithoutTrainer() {
		// TODO Auto-generated method stub
		return (List<Club>) em.createQuery("FROM Club cl WHERE cl.trainer.id IS NULL").getResultList();
	}	

	@Override
	public President getPresidentById(long id) {		
		return (President) em.createQuery("FROM President p WHERE p.id=:id").setParameter("id", id).getSingleResult();
	}

	@Override
	public List<Player> getPlayers() {		
		return (List<Player>) em.createQuery("FROM Player").getResultList();
	}

	@Override
	public List<Account> getAccounts() {		
		return (List<Account>) em.createQuery("FROM Account").getResultList();
	}

	@Override
	public List<Club> getClubs() {		
		return (List<Club>) em.createQuery("FROM Club").getResultList();
	}

	@Override
	public List<League> getLeagues() {		
		return (List<League>) em.createQuery("FROM League").getResultList();
	}

	@Override
	public List<Trainer> getTrainers() {		
		return (List<Trainer>) em.createQuery("FROM Trainer").getResultList();
	}

	@Override
	public Account getAccountByPlayerId(Person player) {		
		return (Account) em.createQuery("FROM Account c WHERE c.owner=:player").setParameter("player", player).getSingleResult();
	}

	@Override
	public void populate() {
		President p3 = new President();
		p3.setFirstname("Benjamin");
		p3.setLastname("Decaillet");
		p3.setNationality("Swiss");
		
		President p4 = new President();
		p4.setFirstname("Christian");
		p4.setLastname("Constantin");
		p4.setNationality("Swiss");
		
		League l1 = new League();
		l1.setName("Professional");
		l1.setNationality("Swiss");
		
		Trainer p2 = new Trainer();
		p2.setFirstname("Kevin");
		p2.setLastname("Berret");
		p2.setNationality("Swiss");
		p2.setContract(new Contract(LocalDate.of(2010, 01, 01), LocalDate.of(2018, 12, 01), 550));
		p2.setAccount(new Account(500, p2));		
		
		Club club1 = new Club();
		club1.setName("FC Bâle");
		club1.setNationality("Swiss");
		club1.setPresident(p3);
		p3.setClub(club1);
		club1.setAccountClub(new Account(5000, club1));
		club1.setLeague(l1);		
		club1.setTrainer(p2);
		p2.setClub(club1);
		em.persist(club1);
		
		Trainer p5 = new Trainer();
		p5.setFirstname("Maurizio");
		p5.setLastname("Jaccobacci");
		p5.setNationality("Swiss");
		p5.setContract(new Contract(LocalDate.of(2010, 01, 01), LocalDate.of(2018, 12, 01), 500));
		p5.setAccount(new Account(600, p5));
		
		Club club2 = new Club();
		club2.setName("FC Sion");
		club2.setNationality("Swiss");
		club2.setPresident(p4);
		p4.setClub(club2);
		club2.setTrainer(p5);
		club2.setAccountClub(new Account(1000, club2));
		club2.setLeague(l1);
		p5.setClub(club2);
		em.persist(club2);
		
		
		l1.addClub(club1);
		l1.addClub(club2);
		em.persist(l1);

		Player p1 = new Player();
		p1.setFirstname("Mike");
		p1.setLastname("Wigger");
		p1.setNationality("Swiss");
		p1.setTitular(true);
		p1.setContract(new Contract(LocalDate.of(2018, 01, 01), LocalDate.of(2019, 03, 31), 100000));
		p1.setCharacteristics(new Characteristics(18, 11, 5));
		p1.setClub(club1);
		p1.setAccount(new Account(10,p1));
		em.persist(p1);		
	}
	
	@Override
	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	@RolesAllowed("trainer")
	public void transfer(Player playerSrc, Club clubDst, int montant, Contract newContract) throws TransferException {
		Player player = em.merge(playerSrc);
		Club dst = em.merge(clubDst);
		
		player.getClub().getAccountClub().credit(montant);
		dst.getAccountClub().debit(montant);
		player.setClub(dst);
		player.setContract(newContract);
	}
	
	/**
	 * 
	 * PLAYER's methods
	 * 
	 */
	
	@Override
	public void newPlayer(Player newPlayerObj){			
		em.merge(newPlayerObj);
	}
	
	@Override
	public void updatePlayer(Player updatedPlayerObj) {		
		em.merge(updatedPlayerObj);
	}
	
	@Override
	public void removePlayer(Player player) {		
		em.remove(em.contains(player) ? player : em.merge(player));
	}
	
	/**
	 * 
	 * TRAINER's methods
	 * 
	 */
	@Override
	public void newTrainer(Trainer newTrainerObj) {	
		if(newTrainerObj.getClub() == null){
			newTrainerObj.getContract().setBeginningDate(null);
			newTrainerObj.getContract().setEndDate(null);
			newTrainerObj.getContract().setSalary(0);
		}
		else
			newTrainerObj.getClub().setTrainer(newTrainerObj);
			
		em.merge(newTrainerObj);
	}
	
	@Override
	public void updateTrainer(Trainer updatedTrainerObj) {	
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
		em.persist(newPresidentObj);	
	}

	@Override
	public void updatePresident(PersonBean updatedPresidentObj) {		
		em.merge(updatedPresidentObj);
	}

	@Override
	public void removePresident(President president) {		
		em.remove(em.contains(president) ? president : em.merge(president));
	}

	/**
	 * 
	 * CLUB's methods
	 * 
	 */
	@Override
	public void newClub(Club newClubObj) {
		if(newClubObj.getTrainer() == null){
			newClubObj.setTrainer(null);
		}else
			newClubObj.getTrainer().setClub(newClubObj);
		
		newClubObj.getPresident().setClub(newClubObj);
		
		em.merge(newClubObj);
	}

	@Override
	public void updateClub(Club updatedClubObj) {
		
		if(updatedClubObj.getTrainer() == null){
			updatedClubObj.setTrainer(null);
		}
		else{
			updatedClubObj.getTrainer().setClub(updatedClubObj);
		
			try{
				Trainer tOld = (Trainer) em.createQuery("FROM Trainer t WHERE t.club.id=:idClub AND t.id!=:idTrainer").setParameter("idClub", updatedClubObj.getId()).setParameter("idTrainer", updatedClubObj.getTrainer().getId()).getSingleResult();
				tOld.setClub(null);
				tOld.getContract().setBeginningDate(null);
				tOld.getContract().setEndDate(null);
				tOld.getContract().setSalary(0);
			}catch(NoResultException e) {
		        e.printStackTrace();
		    }
		}
		
		em.merge(updatedClubObj);
	}

	@Override
	public void removeClub(Club club) {
		em.remove(em.contains(club) ? club : em.merge(club));		
	}

	
}