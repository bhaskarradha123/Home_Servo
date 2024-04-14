package com.jsp.HomeServeO.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.HomeServeO.Dao.CustomerDao;
import com.jsp.HomeServeO.Dao.ServiceCostDao;
import com.jsp.HomeServeO.Dao.VendorDao;
import com.jsp.HomeServeO.Dao.WorkDao;
import com.jsp.HomeServeO.Dto.Customer;
import com.jsp.HomeServeO.Dto.ServiceCost;
import com.jsp.HomeServeO.Dto.Vendors;
import com.jsp.HomeServeO.Dto.Work;
import com.jsp.HomeServeO.exception.NoSuchElementFoundByCustomerexception;
import com.jsp.HomeServeO.exception.NoSuchElementFoundByVendorException;
import com.jsp.HomeServeO.util.ResponseStructure;

@Service
public class ServiceCostService {

	@Autowired
	private ServiceCostDao serviceCostDao;

	@Autowired
	private VendorDao vendorDao;

	@Autowired
	private WorkDao workDao;

	@Autowired
	private CustomerDao customerDao;

	
	

	public ResponseEntity<ResponseStructure<ServiceCost>> saveCost(int v_id, int w_id) {
		Vendors vendors = vendorDao.getVendorsById(v_id);
		if (vendors != null) {

			Work work = workDao.getWorkById(w_id);
			if (work != null) {
				double costPerDay = vendors.getCostPerDay();
				
				Date start = work.getStartDate();
				Date end = work.getEndDate();

				java.time.Duration duration = java.time.Duration.between(start.toLocalDate().atStartOfDay(),
						end.toLocalDate().atStartOfDay());

				int days = (int) duration.toDays();
				
				ServiceCost cost = new ServiceCost();

				cost.setDays(days);
				cost.setTotalAmount(days * costPerDay);
				ServiceCost cost2 = serviceCostDao.saveCost(cost);

				List<ServiceCost> list = new ArrayList<ServiceCost>();
				list.add(cost2);
				list.addAll(vendors.getCosts());

				vendors.setCosts(list);
				vendorDao.updateVendors(vendors);
				work.setCost(cost2);
				workDao.updateWork(work);

				ResponseStructure<ServiceCost> structure = new ResponseStructure<ServiceCost>();

				structure.setData(cost2);
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setMessage("cost saved successfully.");

				return new ResponseEntity<ResponseStructure<ServiceCost>>(structure, HttpStatus.CREATED);

			}
			throw new NoSuchElementFoundByCustomerexception();

		}
		throw new NoSuchElementFoundByVendorException();

	}

	/*-------------------------------------------------------------------------------------------------------*/

	public ResponseEntity<ResponseStructure<ServiceCost>> payment(int c_id, ServiceCost cost2) {

		Customer customer = customerDao.getCustomerById(c_id);

		if (customer != null) {
			ServiceCost cost = serviceCostDao.getServiceCost(cost2.getId());

			if (cost != null) {
				System.out.println(cost.getMode());

				ResponseStructure<ServiceCost> structure = new ResponseStructure<>();
				structure.setMessage("cost saved successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(serviceCostDao.payServiceCost(cost2));

				return new ResponseEntity<ResponseStructure<ServiceCost>>(structure, HttpStatus.CREATED);
			} else

				throw new NoSuchElementFoundByCustomerexception();

		} else
			throw new NoSuchElementFoundByCustomerexception();

	}

}
