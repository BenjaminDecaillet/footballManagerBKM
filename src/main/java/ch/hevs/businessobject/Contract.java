package ch.hevs.businessobject;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Contrat")
public class Contract {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name="dateDébut")
	private Date beginningDate;
	@Column(name="dateFin")
	private Date endDate;
	@Column(name="salaire")
	private double salary;
	
	public Contract(){
	}

	/**
	 * Create a contract with a beginning date and end date for a specified salary
	 * @param beginningDate Date of the contract's beginning
	 * @param endDate Date of the contract's end
	 * @param salary Salary specified for the contract
	 */
	public Contract(Date beginningDate, Date endDate, double salary) {
		super();
		this.beginningDate = beginningDate;
		this.endDate = endDate;
		this.salary = salary;
	}

	/**
	 * Gets the contract's id
	 * @return long value of the contract's id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the contract's id
	 * @param id long with the id value
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the contract's beginning date
	 * @return Date representing the contract's beginning
	 */
	public Date getBeginningDate() {
		return beginningDate;
	}

	/**
	 * Sets the contract's beginning date
	 * @param beginningDate Date of the contract's beginning
	 */
	public void setBeginningDate(Date beginningDate) {
		this.beginningDate = beginningDate;
	}

	/**
	 * Gets the contract's end date
	 * @return Date representing the contract's end date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the contract's end date
	 * @param endDate Date of the contract's end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the contract's salary
	 * @return double representing the salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * Sets the contract's salary
	 * @param salary double with the contract's salary
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	
	
}
