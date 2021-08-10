package com.shevlik.pricemonitoring.controller.util;

public class ModelFieldValueException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ModelFieldValueException() {
		super();
	}
	
    public ModelFieldValueException(String message) {
		super(message);
	}
    
    public ModelFieldValueException(Throwable cause) {
		super(cause);
	}
    
    public ModelFieldValueException(String message, Throwable cause) {
		super(message, cause);
	}
}
