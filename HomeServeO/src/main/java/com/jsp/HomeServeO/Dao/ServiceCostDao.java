package com.jsp.HomeServeO.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.HomeServeO.Dto.ServiceCost;
import com.jsp.HomeServeO.Repo.ServiceCostRepo;

@Repository
public class ServiceCostDao {

	@Autowired
	private ServiceCostRepo repo;

	public ServiceCost saveCost(ServiceCost cost) {
		return repo.save(cost);
	}

	/*-------------------------------------------------------------------------------------------------------*/

	public ServiceCost payServiceCost(ServiceCost cost) {

		ServiceCost cost2 = repo.findById(cost.getId()).get();

		if (cost2 != null) {
			System.out.println(cost.getMode());
			cost2.setMode(cost.getMode());

			return repo.save(cost2);
		} else
			return null;
	}
	/*-------------------------------------------------------------------------------------------------------*/

	public ServiceCost getServiceCost(int id) {
		ServiceCost cost2 = repo.findById(id).get();
		if (cost2 != null) {
			return cost2;
		} else
			return null;
	}

}
