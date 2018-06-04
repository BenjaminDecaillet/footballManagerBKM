package ch.hevs.managedbeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ch.hevs.businessobject.Player;
import ch.hevs.footballmanager.Football;

@ManagedBean
@ViewScoped
public class PlayerBean {
	
	//Interface
	private Football foot;
	
	@ManagedProperty("#{param.id}")
	private long id;
	
	private Player player;
	
	@PostConstruct
	public void initialize() throws NamingException {
		// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		foot = (Football) ctx.lookup("java:global/FootballManagerBKM-0.0.1-SNAPSHOT/FootballBean!ch.hevs.footballmanager.Football");
		
		player = foot.getPlayerById(id);
	}
	
	/*
	 * CRUD METHODS
	 */
	
	//CREATION
	public void createPlayer(Player newPlayer){
		foot.newPlayer(newPlayer);
	}
	
	// EDIT
	public Player editPlayer(Long id){
		return foot.getPlayerById(id);
	}
	
	//UPDATE
	public void updatePlayer(Player updatedPlayer){
		foot.updatePlayer(updatedPlayer);
	}
	
	//DELETE
	public void deletePlayer(Player deletedPlayer){
		foot.removePlayer(deletedPlayer);
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
}