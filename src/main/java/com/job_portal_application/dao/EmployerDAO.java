package com.job_portal_application.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.job_portal_application.entity.Employer;
import com.job_portal_application.repository.EmployerRepo;

@Repository
public class EmployerDAO {

	
	@Autowired
	private EmployerRepo employerRepo;
	
	public Employer addEmployer(Employer employer) {
		return employerRepo.save(employer);
	}

	public Employer getEmployerById(long employerId) {
		Optional<Employer> optional= employerRepo.findById(employerId);
		if(optional.isEmpty()) {
			return null;
		}else {
			return optional.get();
		}
		
	}
}
