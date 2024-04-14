package com.jsp.HomeServeO.exception;

import lombok.Data;

@Data
public class EmailNotFoundForCustomer extends RuntimeException {

	private String message = "email not found please enter correct email";
	

	public EmailNotFoundForCustomer(String message) {
		super();
		this.message = message;
	}

	public EmailNotFoundForCustomer() {
		super();
	}

}
