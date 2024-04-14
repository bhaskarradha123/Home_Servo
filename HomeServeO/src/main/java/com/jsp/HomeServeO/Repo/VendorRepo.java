package com.jsp.HomeServeO.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.HomeServeO.Dto.Vendors;

public interface VendorRepo extends JpaRepository<Vendors, Integer> {
	
	/*@Query("Select a from Vendors a where email=?1")
	 * this query is not required as of now if the method name is of universal type which can be 
	 * understandable by JPA hence he only will write the query for if method name changes to random type then 
	 * we only need to write the JPQL query for that.
	 */
	public Vendors findByEmail(String email);
	
	
	

}
