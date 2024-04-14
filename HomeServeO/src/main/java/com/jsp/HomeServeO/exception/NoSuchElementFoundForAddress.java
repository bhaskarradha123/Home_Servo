package com.jsp.HomeServeO.exception;

import lombok.Data;

@Data
public class NoSuchElementFoundForAddress extends RuntimeException {
	
		private String message="No such element found";

		public NoSuchElementFoundForAddress(String message) {
			super();
			this.message = message;
		}

		public NoSuchElementFoundForAddress() {
			super();
		}
		

		

	

}
