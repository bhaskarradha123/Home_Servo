package com.jsp.HomeServeO.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.HomeServeO.Dao.CustomerDao;
import com.jsp.HomeServeO.Dao.VendorDao;
import com.jsp.HomeServeO.Dto.Customer;
import com.jsp.HomeServeO.Dto.Vendors;
import com.jsp.HomeServeO.exception.EmailNotFoundForVendor;
import com.jsp.HomeServeO.exception.NoSuchElementFoundByCustomerexception;
import com.jsp.HomeServeO.exception.NoSuchElementFoundByVendorException;
import com.jsp.HomeServeO.exception.PasswordIncorrectForVendor;
import com.jsp.HomeServeO.util.ResponseStructure;

@Service
public class VendorsService {

	@Autowired
	private VendorDao dao;
	
	@Autowired
	private CustomerDao customerDao;

	public ResponseEntity<ResponseStructure<Vendors>> saveVendors(Vendors vendor) {
		// why we are creating this object? to initialize it with response structure class data.
		ResponseStructure<Vendors> structure = new ResponseStructure<Vendors>();

		structure.setData(dao.saveVendor(vendor));
		structure.setMessage("Vendor saved Successfully ");
		structure.setStatus(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Vendors>>(structure, HttpStatus.CREATED);

	}

	/*-------------------------------------------------------------------------------------------------------*/

	public ResponseEntity<ResponseStructure<Vendors>> updateVendors(Vendors vendors) {

		ResponseStructure<Vendors> structure = new ResponseStructure<Vendors>();

		Vendors db = dao.getVendorsById(vendors.getId());

		if (db != null) {
			structure.setData(dao.updateVendors(vendors));
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("vendor data updated successfully.");

			return new ResponseEntity<ResponseStructure<Vendors>>(structure, HttpStatus.FOUND);
		} else
			throw new NoSuchElementFoundByVendorException();

	}

	/*-------------------------------------------------------------------------------------------------------*/
	public ResponseEntity<ResponseStructure<Vendors>> getVendorById(int id) {

		Vendors db = dao.getVendorsById(id);

		ResponseStructure<Vendors> structure = new ResponseStructure<Vendors>();

		if (db != null) {
			structure.setMessage("Vendors data fetched successfully.");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(db);// or dao.getVendorsById(id);

			return new ResponseEntity<ResponseStructure<Vendors>>(structure, HttpStatus.FOUND);
		} else

			throw new NoSuchElementFoundByVendorException();

	}

	/*-------------------------------------------------------------------------------------------------------*/

	public ResponseEntity<ResponseStructure<List<Vendors>>> getAllVendors(int c_id) {


		Customer customer = customerDao.getCustomerById(c_id);
		

		if (customer != null) {
			
			ResponseStructure<List<Vendors>> structure = new ResponseStructure<>();
			
			List<Vendors> vendors = dao.getAllVendors();
			if(vendors != null) {

			structure.setMessage("All the vendors fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(vendors);

			return new ResponseEntity<ResponseStructure<List<Vendors>>>(structure, HttpStatus.FOUND);
		} else

			throw new NoSuchElementFoundByVendorException("No vendors are ther to show.");
		}
		throw new NoSuchElementFoundByCustomerexception("No customers are ther to show.");


	}

	/*-------------------------------------------------------------------------------------------------------*/

	public ResponseEntity<ResponseStructure<Vendors>> deleteVendors(int id) {

		ResponseStructure<Vendors> structure = new ResponseStructure<>();

		Vendors db = dao.getVendorsById(id);
		
	

		if (db != null) {
			structure.setMessage("id found and deleted");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dao.deleteVendor(id));

			return new ResponseEntity<ResponseStructure<Vendors>>(structure, HttpStatus.FOUND);

		} else
			throw new NoSuchElementFoundByVendorException("id is not there in the data base.");

	}

	/*-------------------------------------------------------------------------------------------------------*/

	public ResponseEntity<ResponseStructure<Vendors>> login(String email,String pasword) {

		Vendors vendors = dao.getVendorByEmail(email);
		if (vendors != null) {
			if (vendors.getPassword().equals(pasword)) {
				ResponseStructure<Vendors> structure = new ResponseStructure<Vendors>();
				structure.setData(vendors);
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setMessage("login successfully");

				return new ResponseEntity<ResponseStructure<Vendors>>(structure, HttpStatus.FOUND);
			} else
				throw new PasswordIncorrectForVendor();
		} else
			throw new EmailNotFoundForVendor("email not found");

	}

	/*-------------------------------------------------------------------------------------------------------*/

}
