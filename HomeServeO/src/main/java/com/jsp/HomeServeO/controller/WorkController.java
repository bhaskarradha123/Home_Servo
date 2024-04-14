package com.jsp.HomeServeO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.HomeServeO.Dto.Work;
import com.jsp.HomeServeO.service.WorkService;
import com.jsp.HomeServeO.util.ResponseStructure;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE,
		RequestMethod.PUT })
public class WorkController {
	@Autowired
	private WorkService service;

	// her is json body we need to give type and address object (after login
	// customer is uploading the work)
	@PostMapping("/work")
	public ResponseEntity<ResponseStructure<Work>> saveWork(@RequestBody Work work, @RequestParam int cus_id) {
		return service.saveWork(work, cus_id);

	}
	/*-------------------------------------------------------------------------------------------------------*/

	// here vendor is starting his work so we only need to pass the ids in url no
	// need of jasonbody
	@PutMapping("/start")
	public ResponseEntity<ResponseStructure<Work>> startWork(@RequestParam int w_id, @RequestParam int ven_id) {
		return service.startWork(w_id, ven_id);
	}
	/*-------------------------------------------------------------------------------------------------------*/

	@PutMapping("/end")
	public ResponseEntity<ResponseStructure<Work>> endWork(@RequestParam int w_id, @RequestParam int ven_id) {
		return service.endWork(w_id, ven_id);

	}
	/*-------------------------------------------------------------------------------------------------------*/

	@GetMapping("/byid") // fetch work by work(id)
	public ResponseEntity<ResponseStructure<Work>> getWorkById(@RequestParam int id) {
		return service.getWorkById(id);

	}
	/*-------------------------------------------------------------------------------------------------------*/

	@GetMapping("/allwork") // pass vendor id;
	public ResponseEntity<ResponseStructure<List<Work>>> listOfWork(@RequestParam int id) {
		return service.listOfWork(id);
	}
	/*-------------------------------------------------------------------------------------------------------*/

	@GetMapping("/work/ongoingworks") //url+/work/ongoingworks?id=25
	public ResponseEntity<ResponseStructure<List<Work>>> ongoindWorks(@RequestParam int id) {
		return service.ongoingWork(id);
	}

	/*-------------------------------------------------------------------------------------------------------*/

	@GetMapping("/work/completedworks")
	public ResponseEntity<ResponseStructure<List<Work>>> completedWorks(@RequestParam int id) {
		return service.completedWorks(id);
	}

}
