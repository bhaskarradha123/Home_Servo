package com.jsp.HomeServeO.Dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String d_no;
	private String street;
	private String landmark;
	private String district;
	private String state;
	private int pinCode;
	

}
