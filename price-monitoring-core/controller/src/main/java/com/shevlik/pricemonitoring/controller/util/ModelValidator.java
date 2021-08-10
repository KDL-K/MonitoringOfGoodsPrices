package com.shevlik.pricemonitoring.controller.util;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class ModelValidator {
	
	private final Validator validator;
	
	public void validate(Object obj) {
		Set<ConstraintViolation<Object>> violations = validator.validate(obj);
		
		if(!violations.isEmpty()) {
			violations.stream().forEach(v->log.error(v.getMessage()));
			throw new ModelFieldValueException("Invalid field value(s): " 
			       + violations.stream().map(v->v.getMessage()).collect(Collectors.toList()));
		}
		
	}

}
