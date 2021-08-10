package com.shevlik.pricemonitoring.service.util;

public class GraphicCreationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public GraphicCreationException() {
		super();
	}
	
    public GraphicCreationException(String message) {
		super(message);
	}
    
    public GraphicCreationException(Throwable cause) {
		super(cause);
	}
    
    public GraphicCreationException(String message, Throwable cause) {
		super(message, cause);
	}
}
