package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;


@Entity
@Table(name="Compte")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name="solde")
	private long saldo;	

	@ManyToOne
	@JoinColumn(name = "FK_Owner")
	private Person owner;

	/**
	 * Gets the id of the account
	 * @return long with the id value
	 */
	public long getId() {
		return id;
	}
	/**
	 * Sets the id of the account
	 * @param id long value of the id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Gets the saldo of the account
	 * @return long value representing the saldo
	 */
	public long getSaldo() {
		return saldo;
	}
	/**
	 * Sets the saldo of the account
	 * @param saldo long value representing the saldo
	 */
	public void setSaldo(long saldo) {
		this.saldo = saldo;
	}
	/**
	 * Gets the account's owner
	 * @return the owner of the account
	 */
	public Person getOwner() {
		return owner;
	}
	/**
	 * Sets the account's owner
	 * @param owner the owner to set
	 */
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	/**
	 * Debit an account of specified amount
	 * @param amount int amount to debit
	 */
	public void debit(int amount) {
		long newAmount = getSaldo() - amount;
		setSaldo(newAmount);
	}

	/**
	 * Credit an account of specified amoun
	 * @param amount int amount to credit
	 */
	public void credit(int amount) {
		setSaldo(getSaldo() + amount);
	}

	// constructors
	public Account() {
	}
	/**
	 * Create Account with saldo and owner
	 * @param saldo long with saldo value
	 * @param owner owner of the account
	 */
	public Account(long saldo, Person owner) {
		this.saldo = saldo;
		this.owner = owner;
	}

	@PostPersist
	public void acknowledgePersist() {
		System.out.println("account persisted!!!");
	}
}