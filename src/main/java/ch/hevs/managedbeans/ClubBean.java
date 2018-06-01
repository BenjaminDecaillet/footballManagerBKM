package ch.hevs.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Club;
import ch.hevs.footballmanager.Football;

@ManagedBean
@ViewScoped
public class ClubBean {

	//Interface
	private Football foot;
	
	//Properties of all types of club
	private long id;
	private Club club = new Club();	
	
	@PostConstruct
	public void initialize() throws NamingException {
		// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		foot = (Football) ctx.lookup("java:global/FootballManagerBKM-0.0.1-SNAPSHOT/FootballBean!ch.hevs.footballmanager.Football");
	}
	
	public void getClubFromDb(){
		this.club = foot.getClubById(id);
	}
	
	public Club getClub(){		
		return this.club;
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
	 * @param club the club to set
	 */
	public void setClub(Club club) {
		this.club = club;
	}
	
	/*
	 * CRUD METHODS
	 */
	public void createClub(Club club){
		foot.newClub(club);
	}
	
	public void updateClub(Club club){
		foot.updateClub(club);
	}
	
	public void deleteClub(Club club){
		foot.removeClub(club);
	}	
}