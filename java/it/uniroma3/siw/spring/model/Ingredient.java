package it.uniroma3.siw.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String origin;
	private String description;
	@ManyToOne
	private TypeOfBuffet typeOfBuffet;
	
	public String getName() {
		return name;
	}

	public Object getTypeOfBuffet() {
		return typeOfBuffet;
	}

	public void setTypeOfBuffet(TypeOfBuffet typeOfBuffet) {
		
		
	}
}
