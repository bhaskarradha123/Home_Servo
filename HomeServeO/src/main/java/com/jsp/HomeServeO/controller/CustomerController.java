package com.jsp.HomeServeO.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.HomeServeO.Dto.Customer;
import com.jsp.HomeServeO.Duplicate.CustomerDuplicate;
import com.jsp.HomeServeO.service.CustomerService;
import com.jsp.HomeServeO.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*",methods =  {RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})
public class CustomerController {
	
	
	@Autowired
	private CustomerService service;
	    /*@ApiOperation("this api saves the customer data")
	     * This will give custom description of () in swagger application.
	     */
	@PostMapping("/customer")
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(@RequestBody Customer customer) {
		
		return service.saveCustomer(customer);
	}
	
	/*-------------------------------------------------------------------------------------------------------*/

	
	@PutMapping("/customer")
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody Customer customer) {
		return service.updateCustomer(customer);
	}
	
	/*-------------------------------------------------------------------------------------------------------*/

	
	//while passing url in postman add "customer/id"  because we will be fetching based on the path variable id.
	
	@GetMapping("/customer")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerByID(@RequestParam int id) {
		return service.getCustomerById(id);
	}
	
	/*-------------------------------------------------------------------------------------------------------*/

	
	@GetMapping("/customer/login")
	public ResponseEntity<ResponseStructure<Customer>> login(@RequestParam String email ,@RequestParam String pasword ){
		return service.login(email, pasword);
	}
	
	/*-------------------------------------------------------------------------------------------------------*/

	
	@DeleteMapping("/customer")
	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(@RequestParam int id){
		return service.deleteCustomer(id);
		
	}
	
	/*-------------------------------------------------------------------------------------------------------*/

//	//we don't need this its extra
//	@GetMapping("/customer")
//	public  ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer(){
//		return service.getAllCustomer();
//		
//	}
//	
	/*-------------------------------------------------------------------------------------------------------*/

	//clone Customer API to fetch only assential details through url
	
	@GetMapping("/clonecustomerbyid")
	public ResponseEntity<ResponseStructure<CustomerDuplicate>> getCloneCustomerByID(@RequestParam int id){
		return service.getCustomer(id);
	}
	
	
	
	
	/*-------------------------------------------------------------------------------------------------------*/

	
}
