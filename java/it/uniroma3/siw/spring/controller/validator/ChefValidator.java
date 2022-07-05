package it.uniroma3.siw.spring.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.service.ChefService;

@Component
public class ChefValidator implements Validator {
	
	@Autowired
	private ChefService doctorService;
	
    private static final Logger logger = LoggerFactory.getLogger(ChefValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nationality", "required");

		if (!errors.hasErrors()) {
			logger.debug("ok");
			if (this.doctorService.alreadyExists((Chef)o)) {
				logger.debug("doppio");
				errors.reject("doppio");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Chef.class.equals(aClass);
	}
}
