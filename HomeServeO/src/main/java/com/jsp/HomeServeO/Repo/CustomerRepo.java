package com.jsp.HomeServeO.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.HomeServeO.Dto.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{
	
	//@Query("select a from Customer a where email=?1")
	public Customer findByEmail(String email);
	
	
	
	

}
