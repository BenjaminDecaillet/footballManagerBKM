package ch.hevs.managedbeans;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ch.hevs.businessobject.Club;
import ch.hevs.businessobject.Contract;
import ch.hevs.businessobject.Player;
import ch.hevs.exception.TransferException;
import ch.hevs.footballmanager.Football;

/**
 * @author Benjamin
 *	TransferBean.java
 */
@ManagedBean
public class TransferBean {
	private int transactionAmount;
	private boolean error;
	private String transactionResult;	
	private Football foot;	
	private Club destinationClub;
	private Player player;
	private Contract newContract;	
	private ArrayList<Club> clubs;

	@PostConstruct
	public void initialize() throws NamingException {
		// use JNDI to inject reference to bank EJB
		InitialContext ctx = new InitialContext();
		foot = (Football) ctx.lookup("java:global/FootballManagerBKM-0.0.1-SNAPSHOT/FootballBean!ch.hevs.footballmanager.Football");
		newContract = new Contract();
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
	 * Populate the database with initial data
	 */
	public void populate(){
		foot.populate();
	}

	/**
	 * Performs a transfer between two account
	 * @return String representing the outcome used by the navigation handler to determine what page to display next
	 * @throws TransferException, Exception 
	 */
	public void performTransfer() throws TransferException {
		try {				
			// Transfer
			foot.transfer(player, destinationClub, transactionAmount, newContract);
			this.transactionResult="Success!";
			this.error = false;
		} catch(TransferException  e){
			this.transactionResult = e.toString();
			this.error = true;
		}
	}

	/*
	 * GETTERS AND SETTERS
	 */
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

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Contract getNewContract() {
		return newContract;
	}

	public void setNewContract(Contract newContract) {
		this.newContract = newContract;
	}	
	
	public ArrayList<Club> getClubs() {
		return clubs;
	}

	public void setClubs(ArrayList<Club> clubs) {
		this.clubs = clubs;
	}

	/**
	 * Update the clubs list according to the selected player (i.e. gets all clubs except the player's one
	 */
	public void changeClubs(){
		clubs = (ArrayList<Club>) foot.getOtherClubsThanCurent(player);
	}
}