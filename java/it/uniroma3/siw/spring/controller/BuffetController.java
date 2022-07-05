package it.uniroma3.siw.spring.controller;



import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.lowagie.text.DocumentException;
import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.model.Client;
import it.uniroma3.siw.spring.service.BuffetService;


@Controller
public class BuffetController {
	
	@Autowired
	private BuffetService buffetService;
	

	//private final Logger logger = LoggerFactory.getLogger(this.getClass());	
	

    @RequestMapping(value="/admin/buffet", method = RequestMethod.GET)
    public String addBuffet(Model model) {
    	model.addAttribute("buffet", new Buffet());
    	model.addAttribute("dishes", this.buffetService.getDishService().getAllDishes());
    	model.addAttribute("chefs", this.buffetService.getChefService().allChefs());
        return "buffetForm";
    }

    @RequestMapping(value = "/buffet/{id}", method = RequestMethod.GET)
    public String getBuffet(@PathVariable("id") Long id, Model model) {
    	Buffet buffet= this.buffetService.buffetById(id);
    	Client client=this.buffetService.getCredentialsService().getClientAuthenticated();
    	String role=this.buffetService.getCredentialsService().getRoleAuthenticated();
    	if( role.equals("ADMIN")) {
    	model.addAttribute("buffet", buffet);
    	model.addAttribute("role", this.buffetService.getCredentialsService().getRoleAuthenticated());

    	return "buffet";}
    	else
    		return "buffets";
    }

   
    @RequestMapping(value ="/admin/buffetUpdate")
    public String updateBuffet(@ModelAttribute("buffet") Buffet buffet,
    		Model model, BindingResult bindingResult){
    	this.buffetService.insert(buffet);
    	
    	return "buffets";
    	

}
    @RequestMapping(value = "admin/buffets", method = RequestMethod.GET)
    public String getBuffets(Model model) {
    		model.addAttribute("buffets", this.buffetService.allBuffets());
    		model.addAttribute("role", this.buffetService.getCredentialsService().getRoleAuthenticated());
    		return "buffets";
    }
    @RequestMapping(value = "/buffets/chef", method = RequestMethod.GET)
    public String getBuffetsByChef(Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		model.addAttribute("buffets", this.buffetService.buffetByChef(this.buffetService.getChefService().chefByName(userDetails.getUsername())));
    		return "buffets";
    }
   
   
    @RequestMapping(value = "/admin/buffet", method = RequestMethod.POST)
    public String newBuffet(@ModelAttribute("buffet") Buffet buffet, 
    									Model model, BindingResult bindingResult) {
    	
    	
        if (!bindingResult.hasErrors()) {
        	
        	this.buffetService.insert(buffet);
        	
            model.addAttribute("buffets", this.buffetService.allBuffets());
            model.addAttribute("role", this.buffetService.getCredentialsService().getRoleAuthenticated());
            return "buffets";
        }
        return "buffetForm";
    }
    
}
