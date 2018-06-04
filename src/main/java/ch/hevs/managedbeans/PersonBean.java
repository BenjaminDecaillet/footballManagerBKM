package ch.hevs.managedbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Characteristics;
import ch.hevs.businessobject.Club;
import ch.hevs.businessobject.Contract;
import ch.hevs.businessobject.Person;
import ch.hevs.businessobject.Player;
import ch.hevs.businessobject.President;
import ch.hevs.businessobject.Trainer;
import ch.hevs.footballmanager.Football;

@ManagedBean
@ViewScoped
public class PersonBean {
	
	//Interface
	private Football foot;
	
	//Properties of all types of person
	private long id;
	private Player player = new Player();
	private Trainer trainer = new Trainer();
	
	@PostConstruct
	public void initialize() throws NamingException {
		// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		foot = (Football) ctx.lookup("java:global/FootballManagerBKM-0.0.1-SNAPSHOT/FootballBean!ch.hevs.footballmanager.Football");
	}
	
	public void getPerson(){		
		this.player = foot.getPlayerById(id);
	}
	
	public void getTrainerFromDb(){
		this.trainer = foot.getTrainerById(id);
	}
	
	/*
	 * CRUD METHODS
	 */
	
	//CREATION
	public void createPlayer(Player newPlayer){
		foot.newPlayer(newPlayer);
	}
	public void createTrainer(Trainer newTrainer){
		foot.newTrainer(newTrainer);
	}
	public void createPresident(PersonBean newPresident){
		foot.newPresident(newPresident);
	}
	
	//UPDATE
	public void updatePlayer(Player updatedPlayer){
		foot.updatePlayer(updatedPlayer);
	}
	public void updateTrainer(Trainer updatedTrainer){
		foot.updateTrainer(updatedTrainer);
	}
	public void updatePresident(PersonBean updatedPresident){
		foot.updatePresident(updatedPresident);
	}
	
	//DELETE
	public void deletePlayer(Player deletedPlayer){
		foot.removePlayer(deletedPlayer);
	}
	public void deleteTrainer(Trainer deletedTrainer){
		foot.removeTrainer(deletedTrainer);
	}
	public void deletePresident(President deletedPresident){
		foot.removePresident(deletedPresident);
	}	
	
	/*
	 * 
	 * GETTERS & SETTERS
	 * 
	 */	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}	
}