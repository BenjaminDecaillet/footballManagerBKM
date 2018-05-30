package ch.hevs.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Column;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Club;
import ch.hevs.businessobject.League;
import ch.hevs.businessobject.Person;
import ch.hevs.businessobject.Player;
import ch.hevs.footballmanager.Football;

@ManagedBean
@ViewScoped
public class ClubBean {

	//Interface
	private Football foot;
	
	//Properties of all types of club
	private long id;
	private Club club;
	private String name;
	private String nationality;
	private League league;
	private Account account;
	private Person trainer;
	private Person president;
	private List<Player> players;
	
	@PostConstruct
	public void initialize() throws NamingException {
		// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		foot = (Football) ctx.lookup("java:global/FootballManagerBKM-0.0.1-SNAPSHOT/FootballBean!ch.hevs.footballmanager.Football");
	}
	
	public void getClub(){		
		this.club = foot.getClubById(id);
	}

	/**
	 * @return the foot
	 */
	public Football getFoot() {
		return foot;
	}

	/**
	 * @param foot the foot to set
	 */
	public void setFoot(Football foot) {
		this.foot = foot;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the league
	 */
	public League getLeague() {
		return league;
	}

	/**
	 * @param league the league to set
	 */
	public void setLeague(League league) {
		this.league = league;
	}

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * @return the trainer
	 */
	public Person getTrainer() {
		return trainer;
	}

	/**
	 * @param trainer the trainer to set
	 */
	public void setTrainer(Person trainer) {
		this.trainer = trainer;
	}

	/**
	 * @return the president
	 */
	public Person getPresident() {
		return president;
	}

	/**
	 * @param president the president to set
	 */
	public void setPresident(Person president) {
		this.president = president;
	}

	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * @param players the players to set
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	/**
	 * @param club the club to set
	 */
	public void setClub(Club club) {
		this.club = club;
	}
	
	
	
	
}
