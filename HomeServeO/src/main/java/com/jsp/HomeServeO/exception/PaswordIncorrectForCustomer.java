package com.jsp.HomeServeO.exception;

import lombok.Data;

@Data
public class PaswordIncorrectForCustomer extends RuntimeException {
	
	private String message = "Incorrect password please enter correct password";

	public PaswordIncorrectForCustomer() {
		super();
	}

	public PaswordIncorrectForCustomer(String message) {
		super();
		this.message = message;
	}
	
	
	

}
