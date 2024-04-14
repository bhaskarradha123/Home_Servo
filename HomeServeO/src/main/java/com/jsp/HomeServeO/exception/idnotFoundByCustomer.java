package com.jsp.HomeServeO.exception;

import lombok.Data;

@Data
public class idnotFoundByCustomer extends RuntimeException {
	
	private String message="Id not found";

	public idnotFoundByCustomer(String message) {
		super();
		this.message = message;
	}
	

}
