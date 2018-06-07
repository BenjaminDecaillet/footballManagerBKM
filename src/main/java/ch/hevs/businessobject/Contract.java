package ch.hevs.businessobject;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Contract {

	@Column(name="dateDébut")
	private LocalDate beginningDate;
	@Column(name="dateFin")
	private LocalDate endDate;
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
	public Contract(LocalDate beginningDate, LocalDate endDate, double salary) {
		super();
		this.beginningDate = beginningDate;
		this.endDate = endDate;
		this.salary = salary;
	}

	/**
	 * Gets the contract's beginning date
	 * @return Date representing the contract's beginning
	 */
	public LocalDate getBeginningDate() {
		return beginningDate;
	}

	/**
	 * Sets the contract's beginning date
	 * @param beginningDate Date of the contract's beginning
	 */
	public void setBeginningDate(LocalDate beginningDate) {
		this.beginningDate = beginningDate;
	}

	/**
	 * Gets the contract's end date
	 * @return Date representing the contract's end date
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * Sets the contract's end date
	 * @param endDate Date of the contract's end date
	 */
	public void setEndDate(LocalDate endDate) {
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
