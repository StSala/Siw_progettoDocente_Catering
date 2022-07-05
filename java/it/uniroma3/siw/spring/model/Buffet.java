package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.List;


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
@Table(name = "buffets")
public class Buffet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	
	private LocalDate dateOfPrenotation;
	
	
	@OneToMany(mappedBy="buffet",cascade=CascadeType.ALL)
	private List<Dish> dishes;
	@ManyToOne
	private Client client;
	@ManyToOne
	private Chef chef;
	@ManyToOne
	private TypeOfBuffet typeOfBuffet;
	
	@Override
	public String toString() {
		return "Buffet [id=" + id + ", dateOfPrenotation=" + dateOfPrenotation + ", typeOfBuffet=" + typeOfBuffet + "]";
	}
	
	public String toStringPDF() {
		return "Buffet id:" + id + "     DateOfPrenotation: " + dateOfPrenotation ;
	}

	public Long getId() {
		return id;
	}

	public Chef getChef() {
		return chef;
	}
	
	
	
	
}
