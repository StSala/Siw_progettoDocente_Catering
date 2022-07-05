package it.uniroma3.siw.spring.controller.validator;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Buffet;

@Component
public class BuffetValidator implements Validator {
	
	
    private static final Logger logger = LoggerFactory.getLogger(BuffetValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required");


		if (!errors.hasErrors()) {
			logger.debug("ok");
			
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Buffet.class.equals(aClass);
	}
}