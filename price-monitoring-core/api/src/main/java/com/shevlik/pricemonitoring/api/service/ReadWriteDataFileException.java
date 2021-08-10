package com.shevlik.pricemonitoring.api.service;

public class ReadWriteDataFileException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ReadWriteDataFileException() {
		super();
	}
	
    public ReadWriteDataFileException(String message) {
		super(message);
	}
    
    public ReadWriteDataFileException(Throwable cause) {
		super(cause);
	}
    
    public ReadWriteDataFileException(String message, Throwable cause) {
		super(message, cause);
	}

}
