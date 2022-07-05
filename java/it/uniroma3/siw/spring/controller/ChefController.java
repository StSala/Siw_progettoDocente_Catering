package it.uniroma3.siw.spring.controller;

import java.io.IOException;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import it.uniroma3.siw.spring.controller.validator.ChefValidator;
import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.service.ChefService;
import it.uniroma3.siw.upload.FileUploadUtil;

@Controller
public class ChefController {
	
	@Autowired
	private ChefService chefService;
	
   @Autowired
    private ChefValidator chefValidator;
        
    @RequestMapping(value="/admin/Chef", method = RequestMethod.GET)
    public String addDChef(Model model) {
    	model.addAttribute("chef", new Chef());
        return "chefForm";
    }

    @RequestMapping(value = "/chef/{id}", method = RequestMethod.GET)
    public String getChef(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("chef", this.chefService.chefById(id));
    	model.addAttribute("role", this.chefService.getCredentialsService().getRoleAuthenticated());

    	return "chef";
    }

    @RequestMapping(value = "/chef", method = RequestMethod.GET)
    public String getChefs(Model model) {
    		model.addAttribute("chefs", this.chefService.allChefs());
        	model.addAttribute("role", this.chefService.getCredentialsService().getRoleAuthenticated());
    		return "chefs";
    }
    @RequestMapping(value = "/chefFree", method = RequestMethod.GET)
    public String getDoctorsFree(Model model) {
    		model.addAttribute("chefs", this.chefService.allChefs());
    		return "chefsFree";
    }
    
    
    @RequestMapping(value = "/admin/chef", method = RequestMethod.POST)
    public String addChef(@ModelAttribute("chef") Chef chef,@RequestParam("image") MultipartFile multipartFile, 
    									Model model, BindingResult bindingResult)throws IOException {
    	this.chefValidator.validate(chef, bindingResult);
        if (!bindingResult.hasErrors()) {
        	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        	chef.setPhotos(fileName);
        	
        	Chef savedChef =this.chefService.insert(chef);

        	model.addAttribute("chefs", this.chefService.allChefs());
        	model.addAttribute("role", this.chefService.getCredentialsService().getRoleAuthenticated());
        	
        	return "chefs";
        	}
          return "chefForm";
}
}
