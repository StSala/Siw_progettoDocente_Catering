package it.uniroma3.siw.spring.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Ingredient;
import it.uniroma3.siw.spring.service.IngredientService;

@Component
public class IngridientValidator implements Validator {
	@Autowired
	private IngredientService ingredientService;
	
    private static final Logger logger = LoggerFactory.getLogger(IngridientValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "origin", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required");
		

		if (!errors.hasErrors()) {
			logger.debug("ok");
			if (this.ingredientService.alreadyExists((Ingredient)o)) {
				logger.debug("dopiio");
				errors.reject("doppio");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Ingredient.class.equals(aClass);
	}
}
