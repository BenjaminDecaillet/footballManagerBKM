package ch.hevs.managedbeans;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.footballmanager.Football;

@Stateless
public class PlayerBean {
	
	private Football foot;
	
	private String firstname;
	private String lastname;
	
	@PostConstruct
	public void initialize() throws NamingException {
		// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		foot = (Football) ctx.lookup("java:global/FootballManagerBKM-0.0.1-SNAPSHOT/FootballBean!ch.hevs.footballmanager.Football");
	}
	
	
	public void createPlayer(PlayerBean newPlayer){
		foot.newPlayer(newPlayer);
	}
	
	
	//Getter and setter	
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
}
