package ch.hevs.businessobject;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Personne")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected long id;
	@Column(name="prénom")
	private String firstname;
	@Column(name="nom")
	private String lastname;
	@Column(name="nationalité")
	private String nationality;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="account_id",nullable=true)
	private Account account;	
	
	public Person() {
	}
	
	/**
	 * Create a person with the specified first name, last name and nationality
	 * @param lastname the person's last name
	 * @param firstname the person's first name
	 * @param nationality the person's nationality
	 */
	public Person(String lastname, String firstname, String nationality) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.nationality = nationality;
	}
	
	/**
	 * gets the id of the person
	 * @return long with the id value
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * sets the id of the person
	 * @param id long with the id value
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * gets the person's first name
	 * @return String representing the person's first name
	 */
	public String getFirstname() {
		return firstname;
	}
	
	/**
	 * sets the person's first name
	 * @param firstname String representing the person's first name
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	/**
	 * gets the person's last name
	 * @return  String representing the person's last name
	 */
	public String getLastname() {
		return lastname;
	}
	
	/**
	 * sets the person's last name
	 * @param lastname  String representing the person's last name
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	/**
	 * gets the person's nationality
	 * @return String representing the person's nationality
	 */
	public String getNationality() {
		return nationality;
	}
	
	/**
	 * sets the person's nationality
	 * @param nationality String representing the person's nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	/**
	 * get the person's account
	 * @return the person's account
	 */
	public Account getAccount() {
		return account;
	}
	
	/**
	 * set the person's account
	 * @param account the person's account
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
}