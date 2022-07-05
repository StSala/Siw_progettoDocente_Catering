package it.uniroma3.siw.spring.model;


import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "dishes")
public class Dish {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;

	
	@OneToMany(mappedBy="dish",cascade=CascadeType.ALL)
	private ArrayList<Ingredient> ingredients;
	@ManyToOne
	private Buffet buffet;
	
	
	@Override
	public String toString() {
		return "Dish [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
	public String toStringPDF() {
		return "Dish id:" + id + "    name: " + name ;
	}

	public Object getName() {
		// TODO Auto-generated method stub
		return name;
	}

}