package com.jsp.HomeServeO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.HomeServeO.Dao.AddressDao;
import com.jsp.HomeServeO.Dto.Address;
import com.jsp.HomeServeO.exception.NoSuchElementFoundForAddress;
import com.jsp.HomeServeO.exception.NoSuchelementFoundForWork;
import com.jsp.HomeServeO.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponseStructure<Address>> getAddressById(int id) {

		Address address = addressDao.getAddresssById(id);
		if (address != null) {
			ResponseStructure<Address> structure = new ResponseStructure<>();
			structure.setMessage("address found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(address);
			
			return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.FOUND);

		}
		throw new NoSuchelementFoundForWork();
	}
	/*-------------------------------------------------------------------------------------------------------*/

	public ResponseEntity<ResponseStructure<Address>> updateAddress(Address address){
		
		Address address2 = addressDao.getAddresssById(address.getId());
		
		if(address2 != null) {
			ResponseStructure<Address> structure = new ResponseStructure<>();
			structure.setMessage("address found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(addressDao.updatAddress(address));
			
			return new ResponseEntity<ResponseStructure<Address>>(structure,HttpStatus.FOUND);

		}
		else 
			throw new NoSuchElementFoundForAddress();
	}

}
