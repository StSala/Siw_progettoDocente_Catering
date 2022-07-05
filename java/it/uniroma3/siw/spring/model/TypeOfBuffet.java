package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;

@Entity
@Data
public class TypeOfBuffet {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String description;
	private Long cost;
	@OneToMany(mappedBy="typeOfBuffet")
	private List<Ingredient> ingredients;
	
	@OneToMany(mappedBy="typeOfBuffet")
	@LazyCollection( LazyCollectionOption.TRUE )
	private List<Buffet> buffet;

	@Override
	public String toString() {
		return "TypeOfBuffet [id=" + id + ", name=" + name + ", description=" + description + ", cost=" + cost
				+ "]";
	}
	
	public String toStringPDF() {
		return "TypeOfBuffet:  " + name + "      Description:  " + description + "      Cost:  " + cost;
	}

	public String getName() {
		return name;
	}
	

	
	
	
	
}
