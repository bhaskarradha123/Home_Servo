package com.jsp.HomeServeO.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.HomeServeO.Dto.Work;
import com.jsp.HomeServeO.Repo.WorkRepo;

@Repository
public class WorkDao {

	@Autowired
	WorkRepo repo;

	// save() ,getbyid(),listOfWorks(),update(),start date(),enddate()

	/*-------------------------------------------------------------------------------------------------------*/

	public Work saveWork(Work work) {
		return repo.save(work);

	}
	/*-------------------------------------------------------------------------------------------------------*/

	public Work getWorkById(int id) {
		if (repo.findById(id).isPresent()) {
			Work work = repo.findById(id).get();

			return work;
		} else {
			return null;
		}
	}

	/*-------------------------------------------------------------------------------------------------------*/
	// for UnMapped Works-----
	public List<Work> listOfWork() {
		List<Work> list = repo.listWorks();

		if (list != null) {
			return list;
		} else
			return null;
	}

	/*-------------------------------------------------------------------------------------------------------*/
	// list of all works maped as well as unmapped
	public List<Work> listOfAllWork() {
		List<Work> list = repo.findAll();

		return list;
	}

	/*-------------------------------------------------------------------------------------------------------*/

	public Work updateWork(Work work) {
		Work db = repo.findById(work.getId()).get();

		if (db != null) {

			if (work.getType() == null) {
				work.setType(db.getType());
			}
			if (work.getAddress() == null) {
				work.setAddress(db.getAddress());
			}
			if (work.getCost() == null) {
				work.setCost(db.getCost());
			}
			if (work.getCustomer() == null) {
				work.setCustomer(db.getCustomer());
			}
			if (work.getEndDate() == null) {
				work.setEndDate(db.getEndDate());
			}
			if (work.getStartDate() == null) {
				work.setStartDate(db.getStartDate());
			}
			if (work.getVendor() == null) {
				work.setVendor(db.getVendor());
			}

			repo.save(work);
			return work;

		} else

			return null;

	}
	/*-------------------------------------------------------------------------------------------------------*/

	public List<Work> ongoinWork() {
		return repo.ongoinWorks();
	}

	/*-------------------------------------------------------------------------------------------------------*/

	public List<Work> completedWorks() {
		return repo.completedWorks();
	}

	/*-------------------------------------------------------------------------------------------------------*/

}
