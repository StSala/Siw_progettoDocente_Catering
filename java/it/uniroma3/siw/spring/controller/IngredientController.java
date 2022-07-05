package it.uniroma3.siw.spring.controller;

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

@Controller
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;
	

        
    @RequestMapping(value="/admin/ingredient", method = RequestMethod.GET)
    public String addIngredient(Model model) {
    	model.addAttribute("ingredient", new Ingredient());
        return "ingredientForm";
    }

    @RequestMapping(value = "/ingredient/{id}", method = RequestMethod.GET)
    public String getIngredient(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("ingredient", this.ingredientService.ingredientById(id));
    	model.addAttribute("role", this.ingredientService.getCredentialsService().getRoleAuthenticated());

    	return "ingredient";
    }
    
    @RequestMapping(value = "/ingredient/type", method = RequestMethod.GET)
    public String getIngredientByType(@ModelAttribute("typeOfBuffet") TypeOfBuffet typeOfBuffet, Model model) {
    	model.addAttribute("ingredient", this.ingredientService.ingredientByTypeOfBuffet(typeOfBuffet));
    	model.addAttribute("role", this.ingredientService.getCredentialsService().getRoleAuthenticated());

    	return "ingredient";
    }

    @RequestMapping(value = "/ingredient", method = RequestMethod.GET)
    public String getIngredients(Model model) {
    		model.addAttribute("ingredients", this.ingredientService.allIngredients());
        	model.addAttribute("role", this.ingredientService.getCredentialsService().getRoleAuthenticated());
    		return "ingredients";
    }
    
    @RequestMapping(value = "/admin/ingredient", method = RequestMethod.POST)
    public String addIngredient(@ModelAttribute("ingredient") Ingredient ingredient,
    									Model model, BindingResult bindingResult) {
    	
        if (!bindingResult.hasErrors()) {
        	this.ingredientService.insert(ingredient);
             ingredient.setTypeOfBuffet(ingredient.getTypeOfBuffet());
             model.addAttribute("ingredient",ingredient);
            return "ingredientForm";
        }
        return "ingredientForm";
    }
    @RequestMapping(value = "/ingredientComplete/{id}", method = RequestMethod.GET)
    public String stopIngredients(@PathVariable("id") Long id,Model model) {
    		model.addAttribute("typeOfExamination",this.ingredientService.getTypeOfBuffetService().typeOfBuffetById(id));
        	model.addAttribute("role", this.ingredientService.getCredentialsService().getRoleAuthenticated());
    		return "typeOfExamination";
    }
}
