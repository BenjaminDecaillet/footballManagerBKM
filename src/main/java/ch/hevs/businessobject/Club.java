package ch.hevs.businessobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Club")
public class Club {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name="nom")
	private String name;
	@Column(name="nationalité")
	private String nationality;
	
	public Club(){
		
	}

	public Club(String name, String nationality) {
		super();
		this.name = name;
		this.nationality = nationality;
	}
	/**
	 * Gets the id of the Club
	 * @return long with the id value
	 */
	public long getId() {
		return id;
	}
	/**
	 * Sets the id of the Club
	 * @param id long value of the id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Gets the Club's name
	 * @return String representing the Club's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the Club's name
	 * @param name String representing the Club's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets the Club's nationality
	 * @return String representing the Club's nationality
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * String representing the Club's nationality
	 * @param nationality String representing the Club's nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
}
