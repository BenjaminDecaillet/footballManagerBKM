package ch.hevs.businessobject;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;

@Entity
@Table(name="Entraineur")
public class Trainer extends Person{

	//Relations
	@Embedded
	private Contract contract;
	
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Club club;
	
	@PreRemove
	private void preRemove(){
		if(getClub() != null)
			club.setTrainer(null);
	}
	
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
	
	
	
	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	@Override
	public boolean equals(Object object) {
		return (object instanceof Trainer) && (((Long)id) != null) 
	             ? ((Long)id).equals(((Trainer) object).id) 
	             : (object == this);
	}
}