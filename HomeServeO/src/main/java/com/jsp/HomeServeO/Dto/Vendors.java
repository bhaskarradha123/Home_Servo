package com.jsp.HomeServeO.Dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Entity
@Data
public class Vendors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(unique = true)
	private String email;
	private long phone;
	private String password;
	private double costPerDay;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	private String role;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ServiceCost> costs;
	
}
