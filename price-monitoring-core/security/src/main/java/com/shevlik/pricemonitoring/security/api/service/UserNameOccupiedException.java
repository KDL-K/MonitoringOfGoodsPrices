package com.shevlik.pricemonitoring.security.api.service;

public class UserNameOccupiedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public UserNameOccupiedException() {
		super();
	}
	
    public UserNameOccupiedException(String message) {
		super(message);
	}
    
    public UserNameOccupiedException(Throwable cause) {
		super(cause);
	}
    
    public UserNameOccupiedException(String message, Throwable cause) {
		super(message, cause);
	}

}
