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
		contract = new Contract();
		setAccount(new Account());
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
	
	@Override
	public boolean equals(Object object) {
		return (object instanceof Trainer) && (((Long)id) != null) 
	             ? ((Long)id).equals(((Trainer) object).id) 
	             : (object == this);
	}
}