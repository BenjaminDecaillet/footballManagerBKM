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
import exception.TransferException;


/**
 * @author Benjamin
 *	TransferBean.java
 */
public class TransferBean {

	private List<Player> players;
	private List<String> playerNames;
	private Person sourcePerson;
	private Club sourceClub;
	
	private Person destinationPerson;
	private String transactionResult;
	private int transactionAmount;
	private Football foot;
	
	private Long clubId;
	private Long playerId;
	private Club destinationClub;
	private Player player;

	@PostConstruct
	public void initialize() throws NamingException {
		// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		foot = (Football) ctx.lookup("java:global/FootballManagerBKM-0.0.1-SNAPSHOT/FootballBean!ch.hevs.footballmanager.Football");
	}
	
	/**
	 * @return the sourceClub
	 */
	public Club getSourceClub() {
		return sourceClub;
	}
	/**
	 * @param sourceClub the sourceClub to set
	 */
	public void setSourceClub(Club sourceClub) {
		this.sourceClub = sourceClub;
	}
	/**
	 * @return the sourcePerson
	 */
	public Person getSourcePerson() {
		return sourcePerson;
	}
	/**
	 * @param sourcePersonName the sourcePerson to set
	 */
	public void setSourcePerson(Person sourcePerson) {
		this.sourcePerson = sourcePerson;
	}
	/**
	 * @return the destinationPerson
	 */
	public Person getDestinationPerson() {
		return destinationPerson;
	}
	/**
	 * @param destinationPersonName the destinationPersonName to set
	 */
	public void setDestinationPerson(Person destinationPerson) {
		this.destinationPerson = destinationPerson;
	}
	/**
	 * @return the transactionResult
	 */
	public String getTransactionResult() {
		return transactionResult;
	}
	/**
	 * @param transactionResult the transactionResult to set
	 */
	public void setTransactionResult(String transactionResult) {
		this.transactionResult = transactionResult;
	}
	/**
	 * @return the transactionAmount
	 */
	public int getTransactionAmount() {
		return transactionAmount;
	}
	/**
	 * @param transactionAmount the transactionAmount to set
	 */
	public void setTransactionAmount(int transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public void populate(){
		foot.populate();
	}

	/**
	 * Performs a transfer between two account
	 * @return String representing the outcome used by the navigation handler to determine what page to display next
	 * @throws TransferException, Exception 
	 */
	public String performTransfer() throws TransferException, Exception {
		try {				
			// Transfer
			foot.transfer(player, destinationClub, transactionAmount);
			this.transactionResult="Success!";
		} catch(TransferException  e){
			this.transactionResult = "Not enough money";
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return "transfer";
	}

	public Club getDestinationClub() {
		return destinationClub;
	}

	public void setDestinationClub(Club destinationClub) {
		this.destinationClub = destinationClub;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}