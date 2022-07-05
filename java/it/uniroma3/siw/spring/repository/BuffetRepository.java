package it.uniroma3.siw.spring.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.model.Client;
import it.uniroma3.siw.spring.model.Buffet;

public interface BuffetRepository extends CrudRepository<Buffet, Long>{
	
	public Optional<List<Buffet>> findByPatient(Client client);
	
	public Optional<List<Buffet>> findByDoctor(Chef chef);
	
	public Optional<Buffet> findById(Long id);

	public Optional<Buffet> findByDateExaminationAndDoctor(String dateExamination, Chef chef);
}
