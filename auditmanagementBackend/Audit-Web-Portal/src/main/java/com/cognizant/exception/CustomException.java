package com.cognizant.exception;

public class CustomException extends Exception {
	
    private static final long serialVersionUID = 1L;
	public CustomException(String error) {
		super(error);
	}
}