package ch.hevs.businessobject;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *	metaclass player extension of a person
 */
@Entity
@Table(name="Joueur")
public class Player extends Person{

	/**
	 * Attribute titular
	 */
	@Column(name="titulaire")
	private Boolean titular;

	/**
	 * Represent the characteristics of a player
	 */
	@OneToOne(cascade = CascadeType.ALL)
	private Characteristics characteristics;
	
	public Player() {
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


}
