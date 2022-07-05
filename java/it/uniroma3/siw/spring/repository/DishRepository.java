package it.uniroma3.siw.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Dish;

public class DishRepository {

	public Optional<Dish> findByName(String name) {
		return null;
	}

	public List<Dish> findByNameAndDescription(String name, String description) {
		return null;
	}

	public Dish save(Dish dish) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Dish> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

