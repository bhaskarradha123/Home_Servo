package com.jsp.HomeServeO.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.jsp.HomeServeO.Dao.CustomerDao;
import com.jsp.HomeServeO.Dao.VendorDao;
import com.jsp.HomeServeO.Dao.WorkDao;
import com.jsp.HomeServeO.Dto.Customer;
import com.jsp.HomeServeO.Dto.Vendors;
import com.jsp.HomeServeO.Dto.Work;
import com.jsp.HomeServeO.exception.NoSuchElementFoundByCustomerexception;
import com.jsp.HomeServeO.exception.NoSuchElementFoundByVendorException;
import com.jsp.HomeServeO.exception.NoSuchelementFoundForWork;
import com.jsp.HomeServeO.util.ResponseStructure;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class WorkService {

	@Autowired
	private WorkDao dao;

	@Autowired
	private CustomerDao custoemrDao;

	@Autowired
	private VendorDao vendorDao;

	/*-------------------------------------------------------------------------------------------------------*/
	// work saved by customer will be assigned with id;
	public ResponseEntity<ResponseStructure<Work>> saveWork(Work work, int cus_id) {

		Customer cus = custoemrDao.getCustomerById(cus_id);
		if (cus != null) {
			// befor saving work save the customer
			work.setCustomer(cus);

			ResponseStructure<Work> structure = new ResponseStructure<>();

			structure.setMessage("work saved successfully.");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dao.saveWork(work));

			return new ResponseEntity<ResponseStructure<Work>>(structure, HttpStatus.CREATED);

		} else {
			throw new NoSuchElementFoundByCustomerexception("customer id not found");
		}
	}

	/*-------------------------------------------------------------------------------------------------------*/

	public ResponseEntity<ResponseStructure<Work>> updateWork(Work work) {

		ResponseStructure<Work> structure = new ResponseStructure<>();

		Work db = dao.getWorkById(work.getId());

		if (db != null) {

			structure.setMessage("work updated successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dao.updateWork(work));

			return new ResponseEntity<ResponseStructure<Work>>(structure, HttpStatus.FOUND);

		} else
			throw new NoSuchelementFoundForWork();

	}

	/*-------------------------------------------------------------------------------------------------------*/

	public ResponseEntity<ResponseStructure<Work>> getWorkById(int id) {

		ResponseStructure<Work> structure = new ResponseStructure<>();

		Work db2 = dao.getWorkById(id);

		if (db2 != null) {
			structure.setMessage("id fetched successfully.");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(db2);

			return new ResponseEntity<ResponseStructure<Work>>(structure, HttpStatus.FOUND);
		} else
			
			throw new NoSuchelementFoundForWork("No Work found for provided id");

	}

	/*-------------------------------------------------------------------------------------------------------*/

	public ResponseEntity<ResponseStructure<Work>> startWork(int w_id, int ven_id) {

		//updating the endDate of Work first checking if work is available or not interms of pk,
		Vendors vendor = vendorDao.getVendorsById(ven_id);

		if (vendor != null) {
			//if work is available then check the vendor/labor is there who will perform the work by id

			Work work = dao.getWorkById(w_id);
			
			if (work != null) {
				
				List<Vendors> list = new ArrayList<>();
				list.add(vendor);
				Date date = new Date(new java.util.Date().getTime());
				work.setStartDate(date);
				work.setVendor(list);
				ResponseStructure<Work> structure = new ResponseStructure<>();
				structure.setMessage("start date updated successfully.");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(dao.updateWork(work));

				return new ResponseEntity<ResponseStructure<Work>>(structure, HttpStatus.FOUND);

			} else

				throw new NoSuchelementFoundForWork();

		}
		
		throw new NoSuchElementFoundByVendorException();
	}

	/*-------------------------------------------------------------------------------------------------------*/


	public ResponseEntity<ResponseStructure<Work>> endWork(int w_id, int ven_id) {

		//updating the endDate of Work first checking if work is available or not interms of pk,
		Vendors vendor = vendorDao.getVendorsById(ven_id);
		//setting/getting it with the help of gitByIdOfVendor

		if (vendor != null) {

			//if work is available then check the vendor/labor is there who will perform the work by id
			Work work = dao.getWorkById(w_id);
			//setting/getting it with the help of gitByIdOfWork

			//ending started works.
			if (work != null) {

				Date date = new Date(new java.util.Date().getTime());
				work.setEndDate(date);

				ResponseStructure<Work> structure = new ResponseStructure<>();
				structure.setMessage("works end date updated.");
				structure.setStatus(HttpStatus.FOUND.value());
				structure.setData(dao.updateWork(work));

				return new ResponseEntity<ResponseStructure<Work>>(structure, HttpStatus.FOUND);

			} else

				throw new NoSuchelementFoundForWork();

		}
		
		throw new NoSuchElementFoundByVendorException();
	}
	
	/*-------------------------------------------------------------------------------------------------------*/
	
	//finding the total works that he can perform searching based on id;
	public ResponseEntity<ResponseStructure<List<Work>>> listOfWork(int ven_id){
		
		Vendors vendor = vendorDao.getVendorsById(ven_id);
		if(vendor!= null) {
			
			ResponseStructure<List<Work>> structure = new ResponseStructure<>();
			structure.setMessage("The list of work availble for vendor is :");
			structure.setData(dao.listOfWork());
			structure.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<List<Work>>> (structure,HttpStatus.FOUND);
	
			
		}
		else 
			throw new NoSuchElementFoundByCustomerexception("no work is availble for vendor.");

		
	}
	
	/*-------------------------------------------------------------------------------------------------------*/

	
	public ResponseEntity<ResponseStructure<List<Work>>> ongoingWork(int v_id){
		Vendors vendors= vendorDao.getVendorsById(v_id);
		if(vendors!=null) {
			ResponseStructure<List<Work>> structure = new ResponseStructure<>();
			structure.setData(dao.ongoinWork());
			structure.setMessage("list of ongoind works");
			structure.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<List<Work>>>(structure,HttpStatus.FOUND);
		} 
		else 
			throw new NoSuchElementFoundByVendorException("no vendor found");
	}
	
	/*-------------------------------------------------------------------------------------------------------*/

	public ResponseEntity<ResponseStructure<List<Work>>> completedWorks(int v_id){
		Vendors vendors = vendorDao.getVendorsById(v_id);
		if(vendors !=null) {
			ResponseStructure<List<Work>> structure = new ResponseStructure<>();
			structure.setData(dao.completedWorks());
			structure.setMessage("Here is the list of completed works");
			structure.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<List<Work>>>(structure,HttpStatus.FOUND);
	
		}
		throw new NoSuchElementFoundByVendorException("no vendor found.");
	}
	
	
	
	
}
