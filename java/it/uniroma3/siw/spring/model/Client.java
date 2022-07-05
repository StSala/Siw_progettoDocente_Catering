package it.uniroma3.siw.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;
import javax.persistence.OneToMany;


import lombok.Data;

@Entity
@Data
@Table(name = "clients")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	
	@OneToMany(mappedBy="patient")
	@LazyCollection( LazyCollectionOption.TRUE )
	private List<Buffet> exam;

	@Override
	public String toString() {
		return "Client [id=" + id + ", nome=" + nome + ", cognome=" + cognome + "]";
	}
	
	public String toStringPDF() {
		return "Name client:  " + nome + "     Surname client:  " + cognome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNome() {
		return nome;
	}


}
