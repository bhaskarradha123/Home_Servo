package com.jsp.HomeServeO.exception;

import lombok.Data;

@Data
public class NoSuchElementFoundByVendorException extends RuntimeException{
	
	private String message="No such element found";

	public NoSuchElementFoundByVendorException(String msg) {
		super();
		this.message = msg;
	}

	public NoSuchElementFoundByVendorException() {
		super();
	}
	
	

}
