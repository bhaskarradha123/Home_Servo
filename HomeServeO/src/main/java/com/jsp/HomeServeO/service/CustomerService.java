package com.jsp.HomeServeO.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.HomeServeO.Dao.CustomerDao;
import com.jsp.HomeServeO.Dto.Customer;
import com.jsp.HomeServeO.Duplicate.CustomerDuplicate;
import com.jsp.HomeServeO.exception.EmailNotFoundForCustomer;
import com.jsp.HomeServeO.exception.NoSuchElementFoundByCustomerexception;
import com.jsp.HomeServeO.exception.PaswordIncorrectForCustomer;
import com.jsp.HomeServeO.util.ResponseStructure;


@Service
public class CustomerService {
	@Autowired
	private CustomerDao dao;
	@Autowired
	private CustomerDuplicate customerclone;
	
	@Autowired
	private ModelMapper mapper;
	
	
	

	/*----------------------------------------------------------------------------------------------------*/

	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer) {

		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
		structure.setData(dao.saveCustomer(customer));
		structure.setMessage("customer saved successfully");
		structure.setStatus(HttpStatus.CREATED.value());

		/*
		 * here we are passing object of responseEntity object which is a constructor
		 * accepts two value one is structure and httpStataus of particular value (dont
		 * use valu())
		 */
		return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.CREATED);

	}

	/*------------------------------------------------------------------------------------------------------*/

	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Customer customer) {

		ResponseStructure<Customer> structure = new ResponseStructure<Customer>();

		Customer c = dao.getCustomerById(customer.getId());

		if (c != null) {

			structure.setData((dao.updateCustomer(customer)));
			structure.setMessage(("Customer updated Successfully"));
			structure.setStatus((HttpStatus.FOUND.value()));

			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.FOUND);
		} else
			throw new NoSuchElementFoundByCustomerexception("no object found for the provided id " + customer.getId());

	}

	/*-------------------------------------------------------------------------------------------------------*/

	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(int id) {

		Customer db = dao.getCustomerById(id);
		
		//This is developers created response structure class initializing it for handling the exception
		
				ResponseStructure<Customer> structure = new ResponseStructure<Customer>();

		if (db != null) {
			structure.setData(db);// where db=dao.getCustomerById(id)
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("found the id");
			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.FOUND);

		} else
		{
		
			/*NoSuchElementFoundByCustomerexception exm = new NoSuchElementFoundByCustomerexception();
		     exm.setMsg("no object found for the particular id " + id);
		     throw new NoSuchElementFoundByCustomerexception(exm+" ");
		     tryied to print msgs using setters but its also not working
		     */
			
			throw new NoSuchElementFoundByCustomerexception("no object found for the particular id " + id);
		}
	}

	/*-------------------------------------------------------------------------------------------------------*/

	public ResponseEntity<ResponseStructure<Customer>> login(String email, String pasword) {

		Customer customer = dao.getCustomerByEmail(email);
		if (customer != null) {
			if (customer.getPasword().equals(pasword)) {

				ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
				structure.setData(customer);
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setMessage("login successfully");
				return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.FOUND);

			} else {
				throw new PaswordIncorrectForCustomer();
			}
		} else {
			throw new EmailNotFoundForCustomer();
		}
	}

	/*-------------------------------------------------------------------------------------------------------*/

	public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(int id) {

		Customer c1 = dao.getCustomerById(id);

		if (c1 != null) {
			ResponseStructure<Customer> structure = new ResponseStructure<Customer>();
			structure.setData(dao.deleteCustomer(id));
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("id deleted successfully  ");

			return new ResponseEntity<>(structure, HttpStatus.FOUND);
		} else
			throw new NoSuchElementFoundByCustomerexception(
					"no such object found for id = " + id + " provided to be deleted ");

	}

	/*-------------------------------------------------------------------------------------------------------*/

	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer() {

		ResponseStructure<List<Customer>> structure = new ResponseStructure<>();

		List<Customer> customers = dao.getAllCustomer();
		if (customers != null) {

			structure.setData(dao.getAllCustomer());
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage(" All the elements fetched");

			return new ResponseEntity<ResponseStructure<List<Customer>>>(structure, HttpStatus.FOUND);

		} else
			throw new NoSuchElementFoundByCustomerexception("No data is there in the database");

	}

	/*-------------------------------------------------------------------------------------------------------*/

	
	//this is duplicate class of customer 
	
	public ResponseEntity<ResponseStructure<CustomerDuplicate>> getCustomer(int id){
		
		Customer customer = dao.getCustomerById(id);
		
		
		if(customer!= null) {
			
			CustomerDuplicate customerDuplicate= this.mapper.map(customer,CustomerDuplicate.class);

//			customerclone.setId(customer.getId());
//			customerclone.setName(customer.getName());
//			customerclone.setEmail(customer.getEmail());
//			customerclone.setPhone(customer.getPhone());
//			customerclone.setFamilyCount(customer.getFamilyCount());
//
			ResponseStructure<CustomerDuplicate> structure = new ResponseStructure<>();
			structure.setMessage("data fetched successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(customerDuplicate);
			
			return new ResponseEntity<ResponseStructure<CustomerDuplicate>>(structure,HttpStatus.FOUND);
			
		}
		
		throw new NoSuchElementFoundByCustomerexception("No data found for particular id");
	}
	
}

/*-------------------------------------------------------------------------------------------------------*/
