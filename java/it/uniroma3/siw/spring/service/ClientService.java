package it.uniroma3.siw.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.model.Client;
import it.uniroma3.siw.spring.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The UserService handles logic for Users.
 */
@Service
public class ClientService {

    @Autowired
    protected ClientRepository clientRepository;
    
    @Autowired
	private CredentialsService credentialsService;

    /**
     * This method retrieves a Client from the DB based on its ID.
     * @param id the id of the User to retrieve from the DB
     * @return the retrieved User, or null if no User with the passed ID could be found in the DB
     */
    @Transactional
    public Client getClient(Long id) {
        Optional<Client> result = this.clientRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * This method saves a User in the DB.
     * @param user the User to save into the DB
     * @return the saved User
     * @throws DataIntegrityViolationException if a User with the same username
     *                              as the passed User already exists in the DB
     */
    @Transactional
    public Client saveClient(Client client ) {
        return this.clientRepository.save(client);
    }

    /**
     * This method retrieves all Users from the DB.
     * @return a List with all the retrieved Users
     */
    @Transactional
    public List<Client> getAllClients() {
        List<Client> result = new ArrayList<>();
        Iterable<Client> iterable = this.clientRepository.findAll();
        for(Client client : iterable)
            	result.add(client);
        return result;
    }

    @Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}

	public Client getClientByUsername(String username) {
		
		return this.credentialsService.findByUsername(username).get().getClient();
	}
    
    

	
}
