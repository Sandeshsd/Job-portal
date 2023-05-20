package com.job_portal_application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.job_portal_application.entity.JobApplication;
import com.job_portal_application.repository.JobApplicationRepo;

@Repository
public class JobApplicationDAO {
	
	@Autowired
	private JobApplicationRepo jobApplicationRepo;
	
	public JobApplication createJobApplication(JobApplication jobApplication) {
		return jobApplicationRepo.save(jobApplication);
	}

}
