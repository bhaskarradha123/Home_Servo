package com.jsp.HomeServeO.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.HomeServeO.Dto.ServiceCost;
import com.jsp.HomeServeO.Dto.Vendors;
import com.jsp.HomeServeO.Dto.Work;
import com.jsp.HomeServeO.Repo.VendorRepo;

@Repository
public class VendorDao {

	@Autowired
	VendorRepo repo;

	@Autowired
	WorkDao dao;

	public Vendors saveVendor(Vendors vendor) {
		return repo.save(vendor);

	}

	/*-------------------------------------------------------------------------------------------------------*/

	public List<Vendors> getAllVendors() {
		return repo.findAll();
	}

	/*-------------------------------------------------------------------------------------------------------*/

	public Vendors getVendorsById(int id) {
		if (repo.findById(id).isPresent()) {
			Vendors vendors = repo.findById(id).get();
			return vendors;
		} else
			return null;

	}

	/*-------------------------------------------------------------------------------------------------------*/

	

	/*-------------------------------------------------------------------------------------------------------*/

	public Vendors updateVendors(Vendors vendors) {

		Vendors db = repo.findById(vendors.getId()).get();
		if (db != null) {

			if (vendors.getName() == null) {
				vendors.setName(db.getName());
			}
			if (vendors.getAddress() == null) {
				vendors.setAddress(db.getAddress());
			}
			if (vendors.getCostPerDay() == 0) {
				vendors.setCostPerDay(db.getCostPerDay());
			}
			if (vendors.getCosts() == null) {
				vendors.setCostPerDay(db.getCostPerDay());
			}
			if (vendors.getEmail() == null) {
				vendors.setEmail(db.getEmail());
			}
			if (vendors.getPassword() == null) {
				vendors.setPassword(db.getPassword());
			}
			if (vendors.getPhone() == 0) {
				vendors.setPhone(db.getPhone());
			}
			if (vendors.getRole() == null) {
				vendors.setRole(db.getRole());
			}

			repo.save(vendors);
			return vendors;
		}
		return null;

	}

	/*-------------------------------------------------------------------------------------------------------*/

	// CRUD operations By using email

	public Vendors getVendorByEmail(String email) {
		Vendors vendors = repo.findByEmail(email);
		if (vendors != null) {

			return vendors;

		} else
			return null;
	}

	/*-------------------------------------------------------------------------------------------------------*/

	public Vendors deleteVendorByEmail(String email) {

		Vendors vendors = repo.findByEmail(email);
		if (vendors != null) {
			repo.delete(vendors);

			return vendors;
		}

		return null;

	}

	/*-------------------------------------------------------------------------------------------------------*/
	public Vendors deleteVendor(int vendorId) {
		if (repo.findById(vendorId).isPresent()) {
			Vendors v1 = repo.findById(vendorId).get();

			List<Work> list = dao.listOfAllWork();
			
			if (list != null) {
				List<Vendors> updatedVendor1 = new ArrayList<>();

				for (Work work : list) {
					List<Vendors> ven = work.getVendor();
					if (ven != null) {
						for (Vendors vendor : ven) {

							if (vendor.getId() != vendorId) {
								updatedVendor1.add(vendor);

							}
						}
					}

					work.setVendor(updatedVendor1);
					dao.updateWork(work);

				}
			}

			v1.setCosts(null);
			repo.deleteById(vendorId);
			return v1;
			
		} else
			
			return null;
	}
	
/*-------------------------------------------------------------------------------------------------------*/
}