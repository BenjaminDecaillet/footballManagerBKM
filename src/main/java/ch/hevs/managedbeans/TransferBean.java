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
	private Person sourcePerson;
	private Club sourceClub;
	private Person destinationPerson;
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



	/**
	 * Performs a transfer between two account
	 * @return String representing the outcome used by the navigation handler to determine what page to display next
	 */
	public String performTransfer() {

		try {
			Account compteSrc;
				if(sourceClub != null)
					compteSrc = foot.getAccountById(sourceClub.getAccount().getId());
				else
					compteSrc = foot.getAccountByPlayerId(sourcePerson);
				
				Account compteDest = foot.getAccountByPlayerId(destinationPerson);

				// Transfer
				foot.transfer(compteSrc, compteDest, transactionAmount);
				this.transactionResult="Success!";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "showTransferResult";
	}

}