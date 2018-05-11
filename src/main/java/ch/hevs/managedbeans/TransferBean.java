package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Club;
import ch.hevs.businessobject.League;
import ch.hevs.businessobject.Person;
import ch.hevs.businessobject.Player;
import ch.hevs.businessobject.President;
import ch.hevs.businessobject.Trainer;
import ch.hevs.footballmanager.Football;


/**
 * @author Benjamin
 *	TransferBean.java
 */
public class TransferBean {
	
	private List<Player> players;
    private List<String> playerNames;
    private String sourcePersonName;
    private String destinationPersonName;
    private String transactionResult;
    private int transactionAmount;
    private Football foot;
	
	@PostConstruct
    public void initialize() throws NamingException {
    	
    	// use JNDI to inject reference to bank EJB
    	InitialContext ctx = new InitialContext();
		foot = (Football) ctx.lookup("java:global/FootballManagerBKM-SNAPSHOT/Footballbean!ch.hevs.footballmanager.Football");    	
			
    	// get clients
		List<Player> playerList = foot.getPlayers();
		this.playerNames = new ArrayList<String>();
		for (Player player : playerList) {
			this.playerNames.add(player.getLastname());
		}
		
		foot.populate();
    }

}
