package ch.hevs.businessobject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Président")
public class President extends Person{
	
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Club club;

	public President(){
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}
}