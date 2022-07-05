package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.spring.model.TypeOfBuffet;


public interface TypeOfBuffetRepository extends CrudRepository<TypeOfBuffet, Long>{

	List<TypeOfBuffet> findByName(String name);
	
	

}
