package com.jsp.HomeServeO.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.HomeServeO.Dto.Vendors;
import com.jsp.HomeServeO.util.ResponseStructure;


@ControllerAdvice
public class ExceptionHandleForHomeServo extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> sQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex){
		ResponseStructure<String > structure = new ResponseStructure<String>();
		structure.setMessage(ex.getMessage());
		structure.setStatus(HttpStatus.BAD_REQUEST.value());
		structure.setData("you can't perform the operation");
		
		// return object is a constructor hence pass two call values 1.structure 2.request
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);
	}
	
	/*-------------------------------------------------------------------------------------------------------*/
//	
//	@ExceptionHandler(idnotFoundByCustomer.class)
//	public ResponseEntity<ResponseStructure<String>> idnotFoundByCustomer(idnotFoundByCustomer ex){
//		ResponseStructure<String > structure = new ResponseStructure<String>();
//		structure.setMessage("hoi");
//		structure.setStatus(HttpStatus.BAD_REQUEST.value());
//		structure.setData("hlo");
//		
//		// return object is a constructor hence pass two call values 1.structure 2.request
//		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);
//	}
//	
	/*-------------------------------------------------------------------------------------------------------*/

	
	@ExceptionHandler(EmailNotFoundForCustomer.class)
	public ResponseEntity<ResponseStructure<String>> emailNotFoundForCustomer(EmailNotFoundForCustomer em){
		ResponseStructure<String > structure = new ResponseStructure<String>();
		structure.setMessage(em.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("wrong email pleas enter corect eamil!");
		
		// return object is a constructor hence pass two call values 1.structure 2.request
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	
	}
	
	/*-------------------------------------------------------------------------------------------------------*/
	
	@ExceptionHandler(PaswordIncorrectForCustomer.class)
	public ResponseEntity<ResponseStructure<String>> paswordIncorrectForCustomer(PaswordIncorrectForCustomer pd){
		ResponseStructure<String> structure= new ResponseStructure<String>();
		structure.setMessage(pd.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("Please enter correct password");

		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
	}
	
	/*-------------------------------------------------------------------------------------------------------*/

	@ExceptionHandler(NoSuchElementFoundByCustomerexception.class)
	public ResponseEntity<ResponseStructure<String>> noSuchElementFoundByCustomerexception(NoSuchElementFoundByCustomerexception nef){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(nef.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("check the id properly");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	/*-------------------------------------------------------------------------------------------------------*/

	//Email not found is for login check handling
	@ExceptionHandler(EmailNotFoundForVendor.class)
	public ResponseEntity<ResponseStructure<String>> emaiNotFoundForVendors(EmailNotFoundForVendor ev){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		System.out.println(ev.getMessage());
		structure.setMessage(ev.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("you entered wrong email please enter correct one.");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
	
	/*-------------------------------------------------------------------------------------------------------*/

	@ExceptionHandler(PasswordIncorrectForVendor.class)
	public ResponseEntity<ResponseStructure<String>> passwordIncorrectForVendor(PasswordIncorrectForVendor pv){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage(pv.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("wrong combination of password!");
		
		return new ResponseEntity<ResponseStructure<String>> (structure, HttpStatus.NOT_FOUND);
	}
	
	/*-------------------------------------------------------------------------------------------------------*/

	@ExceptionHandler(NoSuchElementFoundByVendorException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchElementFoundByVendorException(NoSuchElementFoundByVendorException nefv){
		
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage(nefv.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("no data found");
		
		return new ResponseEntity<ResponseStructure<String>> (structure,HttpStatus.NOT_FOUND);
	}
	
	/*-------------------------------------------------------------------------------------------------------*/

	@ExceptionHandler(NoSuchelementFoundForWork.class)
	public ResponseEntity<ResponseStructure<String>> noSuchelementFoundForWork(NoSuchelementFoundForWork nesw){
		
		ResponseStructure<String> structure = new ResponseStructure<>();
		
		structure.setMessage(nesw.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("no data to be updated");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
		
	}
	
	/*-------------------------------------------------------------------------------------------------------*/

	@ExceptionHandler(NoSuchElementFoundForAddress.class)
	public ResponseEntity<ResponseStructure<String>> noSuchelementFoundFor(NoSuchElementFoundForAddress nesf){
		
		ResponseStructure<String> structure = new ResponseStructure<>();
		
		structure.setMessage(nesf.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData("no data to be updated");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		
		
	}
	
	
	
	
	
	
	
	
	
}
