package com.shevlik.pricemonitoring.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class AppExceptionHandler {
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> DAOExceptionHandler(IllegalArgumentException e){
		log.warn(e);
		return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.NOT_FOUND);
	}

	/* ReadWriteDataFileException, 
	 * GraphicCreationException,
	 * UserNameOccupiedException, other exceptions
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> unexpectedExceptionHandler(Exception e){
		log.warn(e);
		return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
