package it.uniroma3.siw.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.spring.model.Ingredient;
import it.uniroma3.siw.spring.model.TypeOfBuffet;




public interface IngridientRepository extends CrudRepository<Ingredient, Long>{

	List<Ingredient> findByName(String name);

	Optional<Ingredient> findByTypeOfBuffet(TypeOfBuffet typeOfBuffet);

}
