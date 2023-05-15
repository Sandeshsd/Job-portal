package com.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job_portal_application.entity.Employer;
import com.job_portal_application.service.EmployerService;
import com.job_portal_application.util.responseStructure;

@RestController
@RequestMapping("/employer")
public class EmployerControler {

	@Autowired
	private EmployerService employerService;
	
	@PostMapping
	public ResponseEntity<responseStructure<Employer>> addEmployer(@RequestBody Employer employer){
		return employerService.addEmployer(employer);
	}
}
