package com.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.job_portal_application.entity.JobApplication;
import com.job_portal_application.service.JobApplicationService;
import com.job_portal_application.util.responseStructure;

@RestController
@RequestMapping("/apply")
public class JobApplicationController {
	
	
	@Autowired
	private JobApplicationService applicationService;
	
	@PostMapping
	public ResponseEntity<responseStructure<JobApplication>> createJobApplication(
			@RequestParam long applicantId, @RequestParam long jobId){
		return applicationService.createJobApplication(applicantId, jobId);
	}
}
