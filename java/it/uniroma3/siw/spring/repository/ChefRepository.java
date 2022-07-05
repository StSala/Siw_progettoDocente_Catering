package it.uniroma3.siw.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Chef;



public interface ChefRepository extends CrudRepository<Chef, Long> {

	Optional<Chef> findByName(String name);

	List<Chef> findByNameAndSurname(String name, String surname);

}
