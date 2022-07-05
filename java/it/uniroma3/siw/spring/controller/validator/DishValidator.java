package it.uniroma3.siw.spring.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Dish;
import it.uniroma3.siw.spring.service.DishService;

@Component
public class DishValidator implements Validator {
	
	@Autowired
	private DishService dishService;
	
    private static final Logger logger = LoggerFactory.getLogger(ChefValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required");

		if (!errors.hasErrors()) {
			logger.debug("ok");
			if (this.dishService.alreadyExists((Dish)o)) {
				logger.debug("doppio");
				errors.reject("doppio");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Dish.class.equals(aClass);
	}
}
