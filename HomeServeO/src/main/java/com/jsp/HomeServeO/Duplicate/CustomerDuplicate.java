package com.jsp.HomeServeO.Duplicate;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CustomerDuplicate {
	
	private int id;
	private String name;
	private String email;
	private long phone;
	private int familyCount;

}
