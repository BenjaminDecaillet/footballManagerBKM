package ch.hevs.businessobject;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *	metaclass player extension of a person
 */
@Entity
@Table(name="Joueur")
public class Player extends Person{

	@Column(name="titulaire")
	private Boolean titular;

	//Relations
	@OneToOne(cascade = CascadeType.ALL)
	private Characteristics characteristics;
	@OneToOne(cascade = CascadeType.ALL)
	private Contract contract;
	@ManyToOne (cascade={CascadeType.MERGE, CascadeType.PERSIST})
	private Club club;


	public Player() {
		contract = new Contract();
		characteristics = new Characteristics();
	}
	/**
	 * Create a player with the specified value for titular
	 * @param titular boolean 
	 */
	public Player(Boolean titular) {
		this.titular = titular;
	}

	/**
	 * Gets if a player is titular, return a boolean
	 * @return boolean value
	 */
	public Boolean getTitular() {
		return titular;
	}
	/**
	 * Sets if a player is titular 
	 * @param titular boolean
	 */
	public void setTitular(Boolean titular) {
		this.titular = titular;
	}
	/**
	 * Gets the characteristics of a player
	 * @return characteristics Characteristics object representing the Characteristics of a player
	 */
	public Characteristics getCharacteristics() {
		return characteristics;
	}
	/**
	 * Set the characteristics of a player
	 * @param characteristics Characteristics object representing characteristics of a player
	 */
	public void setCharacteristics(Characteristics characteristics) {
		this.characteristics = characteristics;
	}
	/**
	 * @return the contract
	 */
	public Contract getContract() {
		return contract;
	}
	/**
	 * @param contract the contract to set
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	/**
	 * Gets the club of a player
	 * @return the club object representing the club of a player
	 */
	public Club getClub() {
		return club;
	}
	/**
	 * Set the club of a player
	 * @param club the club object representing the club of a player
	 */
	public void setClub(Club club) {
		this.club = club;
	}

}
