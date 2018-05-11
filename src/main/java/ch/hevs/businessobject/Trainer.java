package ch.hevs.businessobject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Entraineur")
public class Trainer extends Person{

	//Relations
	@OneToOne(cascade = CascadeType.ALL)
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
