package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Ingredient;
import it.uniroma3.siw.spring.model.TypeOfBuffet;
import it.uniroma3.siw.spring.service.IngredientService;
import it.uniroma3.siw.spring.service.TypeOfBuffetService;

@Controller
public class TypeOfBuffetController {
	
	@Autowired
	private TypeOfBuffetService typeOfBuffetService;
	

    
    @Autowired
	private IngredientService ingredientService;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
        
    @RequestMapping(value="/admin/typeOfBuffet", method = RequestMethod.GET)
    public String addTypeOfBuffet(Model model) {
    	model.addAttribute("typeOfBuffet", new TypeOfBuffet());
    	model.addAttribute("ingredient",this.ingredientService.allIngredients());
        return "typeOfBuffetForm";
    }

   @RequestMapping(value = "/typeOfBuffet/{id}", method = RequestMethod.GET)
    public String getTypeOfBuffet(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("typeOfBuffet", this.typeOfBuffetService.typeOfBuffetById(id));
    	model.addAttribute("role", this.typeOfBuffetService.getCredentialsService().getRoleAuthenticated());
    	return "typeOfBuffet";
    }
   @RequestMapping(value = "/typeOfBuffetFree/{id}", method = RequestMethod.GET)
   public String getTypeOfBuffetFree(@PathVariable("id") Long id, Model model) {
   	model.addAttribute("typeOfBuffet", this.typeOfBuffetService.typeOfBuffetById(id));
   	return "typeOfBuffetFree";
   }

    @RequestMapping(value = "/typeOfBuffetFree", method = RequestMethod.GET)
    public String getTypeOfBuffetFree(Model model) {
    		model.addAttribute("typeOfBuffets", this.typeOfBuffetService.allTypeOfBuffet());
    		
  
    		
    		return "typeOfBuffetsFree";
    }
    @RequestMapping(value = "/typeOfBuffet", method = RequestMethod.GET)
    public String getTypeOfBuffet(Model model) {
    		model.addAttribute("typeOfBuffets", this.typeOfBuffetService.allTypeOfBuffet());
    		
    		model.addAttribute("role", this.typeOfBuffetService.getCredentialsService().getRoleAuthenticated());
    		
    		return "typeOfBuffets";
    }
    
    @RequestMapping(value = "/admin/typeOfBuffet", method = RequestMethod.POST)
    public String newTypeOfBuffet(@ModelAttribute("typeOfBuffet") TypeOfBuffet typeOfBuffet,
    									
    									Model model, BindingResult bindingResult) {
    	
    	logger.debug("ok");
        if (!bindingResult.hasErrors()) {
        	
        	
            model.addAttribute("role", this.typeOfBuffetService.getCredentialsService().getRoleAuthenticated());
            Ingredient ingredient=new Ingredient();
            ingredient.setTypeOfBuffet(typeOfBuffet);
            model.addAttribute("ingredient",ingredient);
            this.typeOfBuffetService.insert(typeOfBuffet);
            return "ingredientForm";
        }
        return "typeOfBuffetForm";
    }
}
