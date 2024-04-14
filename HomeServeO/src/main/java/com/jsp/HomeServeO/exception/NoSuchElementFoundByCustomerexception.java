package com.jsp.HomeServeO.exception;

import lombok.Data;

@Data
public class NoSuchElementFoundByCustomerexception extends RuntimeException {
	
	private String message =" No such element found!";

	public NoSuchElementFoundByCustomerexception(String message) {
		super();
		this.message = message;
	}

	public NoSuchElementFoundByCustomerexception() {
		super();
	}
	

}
