package com.jsp.HomeServeO.controller;

import java.util.List;

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
import com.jsp.HomeServeO.Dto.Vendors;
import com.jsp.HomeServeO.service.VendorsService;
import com.jsp.HomeServeO.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*",methods =  {RequestMethod.POST,RequestMethod.GET,RequestMethod.DELETE,RequestMethod.PUT})

public class VendorsController {
	
	@Autowired
	private VendorsService service;
	
	@PostMapping("/vendors")
	public ResponseEntity<ResponseStructure<Vendors>> saveVendor(@RequestBody Vendors vendors) {
		
		return service.saveVendors(vendors);
	}
	/*-------------------------------------------------------------------------------------------------------*/

	
	@PutMapping("/vendors")
	public ResponseEntity<ResponseStructure<Vendors>> updateVendors(@RequestBody Vendors vendors){
		return service.updateVendors(vendors);
	}
	/*-------------------------------------------------------------------------------------------------------*/

	
	@GetMapping("/vendors/login")
	public ResponseEntity<ResponseStructure<Vendors>> login(@RequestParam String email,@RequestParam String password){
		return service.login(email, password);
	}
	/*-------------------------------------------------------------------------------------------------------*/

	
	@GetMapping("/vendorsById")
	public ResponseEntity<ResponseStructure<Vendors>> getVendorById(@RequestParam int id){
		return service.getVendorById(id);
		
	}
	/*-------------------------------------------------------------------------------------------------------*/
	
	@GetMapping("/vendors")  //use customer id as the argument for checking if the customer is their first and handle the exception
	public ResponseEntity<ResponseStructure<List<Vendors>>> getAllVendors(@RequestParam int id){
		return service.getAllVendors(id);
	}
	/*-------------------------------------------------------------------------------------------------------*/

	
	@DeleteMapping("/vendors")
	public ResponseEntity<ResponseStructure<Vendors>> deletVendores(@RequestParam int id){
		return service.deleteVendors(id);
	}
	/*-------------------------------------------------------------------------------------------------------*/

	
	
	

}
