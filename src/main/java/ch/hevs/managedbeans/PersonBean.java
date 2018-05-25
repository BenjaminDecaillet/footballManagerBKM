package ch.hevs.managedbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Characteristics;
import ch.hevs.businessobject.Club;
import ch.hevs.businessobject.Contract;
import ch.hevs.businessobject.Player;
import ch.hevs.businessobject.President;
import ch.hevs.businessobject.Trainer;
import ch.hevs.footballmanager.Football;

@ManagedBean
@RequestScoped
public class PersonBean {
	
	//Interface
	private Football foot;
	
	//Properties of all types of person (Player, Trainer and President)
	private long id;
	private String firstname;
	private String lastname;
	private String nationality;
	private Boolean titular;
	private Characteristics characteristics;
	private Contract contract;
	private Club club;
	
	@PostConstruct
	public void initialize() throws NamingException {
		// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		foot = (Football) ctx.lookup("java:global/FootballManagerBKM-0.0.1-SNAPSHOT/FootballBean!ch.hevs.footballmanager.Football");
	}
	
	/*
	 * CRUD METHODS
	 */
	
	//CREATION
	public void createPlayer(PersonBean newPlayer){
		foot.newPlayer(newPlayer);
	}
	public void createTrainer(PersonBean newTrainer){
		foot.newTrainer(newTrainer);
	}
	public void createPresident(PersonBean newPresident){
		foot.newPresident(newPresident);
	}
	
	//UPDATE
	public void updatePlayer(PersonBean updatedPlayer){
		foot.updatePlayer(updatedPlayer);
	}
	public void updateTrainer(PersonBean updatedTrainer){
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
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public Boolean getTitular() {
		return titular;
	}
	public void setTitular(Boolean titular) {
		this.titular = titular;
	}
	public Characteristics getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(Characteristics characteristics) {
		this.characteristics = characteristics;
	}
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
