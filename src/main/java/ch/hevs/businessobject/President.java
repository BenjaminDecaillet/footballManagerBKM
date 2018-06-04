package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Président")
public class President extends Person{

	public President(){
	}
}