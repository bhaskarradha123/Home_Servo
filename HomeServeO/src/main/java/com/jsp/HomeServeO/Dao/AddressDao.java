package com.jsp.HomeServeO.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.HomeServeO.Dto.Address;
import com.jsp.HomeServeO.Repo.AddressRepo;

@Repository
public class AddressDao {

	// updateAddress,getAddressById because delete and save operations are performed through customer & vendor

	@Autowired
	AddressRepo repo;

	public Address updatAddress(Address address) {

		Address db = repo.findById(address.getId()).get();

		if (db != null) {
			if (address.getD_no() == null) {
				address.setD_no(db.getD_no());
			}
			if (address.getDistrict() == null) {
				address.setDistrict(db.getDistrict());
			}
			if (address.getLandmark() == null) {
				address.setLandmark(db.getLandmark());
			}
			if (address.getPinCode() == 0) {
				address.setPinCode(db.getPinCode());
			}
			if (address.getState() == null) {
				address.setState(db.getState());
			}
			if (address.getStreet() == null) {
				address.setStreet(db.getStreet());
			}
			repo.save(address);
			return address;
		} else
			
			return null;
	}

	/*-------------------------------------------------------------------------------------------------------*/

	public Address getAddresssById(int id) {

		if (repo.findById(id).isPresent()) {
			Address address = repo.findById(id).get();
			return address;
		} else
			return null;
	}
	
	

}
