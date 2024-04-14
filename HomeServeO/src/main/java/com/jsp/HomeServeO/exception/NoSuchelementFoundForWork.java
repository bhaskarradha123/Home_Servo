package com.jsp.HomeServeO.exception;

import lombok.Data;

@Data
public class NoSuchelementFoundForWork extends RuntimeException {

	private String message="no particular id found to  be updated in Works";

	public NoSuchelementFoundForWork(String message) {
		super();
		this.message = message;
	}

	public NoSuchelementFoundForWork() {
		super();
	}
	
	
}
