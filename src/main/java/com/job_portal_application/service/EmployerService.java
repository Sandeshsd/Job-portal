package com.job_portal_application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.job_portal_application.dao.EmployerDAO;
import com.job_portal_application.entity.Employer;
import com.job_portal_application.util.responseStructure;

@Service
public class EmployerService {

	@Autowired
	private EmployerDAO employerDAO;
	
	public ResponseEntity<responseStructure<Employer>> addEmployer(Employer employer){
		Employer employer2= employerDAO.addEmployer(employer);
		
		responseStructure<Employer> responseStructure=new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Employer added successfully");
		responseStructure.setData(employer2);
		return new ResponseEntity<responseStructure<Employer>>(responseStructure,HttpStatus.CREATED);
		
		
	}
}
