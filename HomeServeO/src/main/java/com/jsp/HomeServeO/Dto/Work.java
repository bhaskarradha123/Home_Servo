package com.jsp.HomeServeO.Dto;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
@Entity
@Data
public class Work {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;
	private String type;
	private Date startDate;
	private Date endDate;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	@ManyToMany
	private List<Vendors> vendor;
	
	@ManyToOne
	@JoinColumn
	private Customer customer;
	
	@OneToOne
	private ServiceCost cost;
	
	
	
	
	

}
