package ch.hevs.businessobject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Ligue")
public class League {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name="nom")
	private String name;
	@Column(name="nationalité")
	private String nationality;
	
	//Relations
	@OneToMany(cascade = CascadeType.ALL, mappedBy="league")
	private List<Club> clubs;
	
	public League(){
		clubs = new ArrayList<Club>();
	}
	/**
	 * Create a league with specified name and nationality
	 * @param name the league name
	 * @param nationality the league nationality
	 */
	public League(String name, String nationality) {
		clubs = new ArrayList<Club>();
		this.name = name;
		this.nationality = nationality;
	}
	/**
	 * Gets the id of the league
	 * @return long with the id value
	 */
	public long getId() {
		return id;
	}
	/**
	 * Sets the id of the league
	 * @param id long value of the id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Gets the league's name
	 * @return String representing the league's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the league's name
	 * @param name String representing the league's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets the league's nationality
	 * @return String representing the league's nationality
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * String representing the league's nationality
	 * @param nationality String representing the league's nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * Gets the list of clubs in the league
	 * @return the clubs of the league
	 */
	public List<Club> getClubs() {
		return clubs;
	}
	/**
	 * Sets the league's clubs list
	 * @param clubs the clubs to set
	 */
	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}
	/**
	 * Add a club to the league's clubs list
	 * @param club to add to the league
	 */
	public void addClub(Club club){
		this.clubs.add(club);
	}
	
	@Override
	public boolean equals(Object object) {
		return (object instanceof League) && (((Long)id) != null) 
	             ? ((Long)id).equals(((League) object).id) 
	             : (object == this);
	}
}
