package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.repository.ChefRepository;

@Service
public class ChefService {
	
	@Autowired
	private ChefRepository chefRepository; 
	@Autowired
	private CredentialsService credentialsService;
	
	@Transactional
	public Chef insert(Chef chef) {
		return chefRepository.save(chef);
	}

	@Transactional
	public List<Chef> allChefs() {
		return (List<Chef>) chefRepository.findAll();
	}

	@Transactional
	public Chef chefById(Long id) {
		Optional<Chef> optional = chefRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public Chef chefByName(String name) {
		Optional<Chef> optional = chefRepository.findByName(name);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Chef chef) {
		List<Chef> chefs = this.chefRepository.findByNameAndSurname(chef.getName(),chef.getSurname());
		if (chefs.size() > 0)
			return true;
		else 
			return false;
	}
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}
}
