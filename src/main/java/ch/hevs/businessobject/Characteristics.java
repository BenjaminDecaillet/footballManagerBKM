package ch.hevs.businessobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Characteristics class to store the attributes of a player
 * power, speed and endurance with value from 1 to 20 are the attributes of the class
 */
@Entity
@Table(name="Caractéristiques")
public class Characteristics {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name="force")
	private int power;
	@Column(name="vitesse")
	private int speed;
	@Column(name="endurance")
	private int endurance;
	
	public Characteristics() {
	}
	/**
	 * Create the characteristics
	 * @param power integer with the power value
	 * @param speed integer with the speed value
	 * @param endurance integer with the endurance value
	 */
	public Characteristics(int power, int speed, int endurance) {
		this.power = power;
		this.speed = speed;
		this.endurance = endurance;
	}
	/**
	 * @return integer with the power value
	 */
	public int getPower() {
		return power;
	}
	/**
	 * set the power value of the characteristics
	 * @param power integer with the power value
	 */
	public void setPower(int power) {
		this.power = power;
	}
	/**
	 * get the speed value
	 * @return integer with the speed value
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * set the speed value of the characteristics
	 * @param speed integer with the speed value
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/**
	 * get the value of endurance
	 * @return integer with the value of endurance
	 */
	public int getEndurance() {
		return endurance;
	}
	/**
	 * set the endurance value of the characteristics
	 * @param endurance integer with the value of endurance
	 */
	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}
	
	/**
	 * get the id of the characteristics
	 */
	public long getId() {
		return id;
	}
	/**
	 * set the id of the characteristics
	 * @param id long with the value of the Id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	
}
