package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.service.ClientService;

@Controller
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value = "/admin/client", method = RequestMethod.GET)
    public String getClients(Model model) {
    		model.addAttribute("clients", this.clientService.getAllClients());
    		model.addAttribute("role", this.clientService.getCredentialsService().getRoleAuthenticated());
    		return "clients";
}
	@RequestMapping(value = "/admin/clients/{id}", method = RequestMethod.GET)
    public String getBuffet(@PathVariable("id") Long id, Model model) {
		
    	model.addAttribute("client", this.clientService.getClient(id));
    	model.addAttribute("role", this.clientService.getCredentialsService().getRoleAuthenticated());

    	return "client";
    }
}
