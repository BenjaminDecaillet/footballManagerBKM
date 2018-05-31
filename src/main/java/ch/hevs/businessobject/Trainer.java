package ch.hevs.businessobject;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Entraineur")
public class Trainer extends Person{

	//Relations
	@Embedded
	private Contract contract;
	
	public Trainer(){		
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
	
	
}
