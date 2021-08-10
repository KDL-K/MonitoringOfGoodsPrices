package com.shevlik.pricemonitoring.controller;

import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ControllerConfig {
	
	@Bean
	public Validator getValidator() {
		return Validation.buildDefaultValidatorFactory().getValidator();
	}
	
	/*@Bean
	public ModelValidator getModelValidator() {
		return new ModelValidator(Validator validator);
	}*/

}
