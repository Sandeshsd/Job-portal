package com.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.job_portal_application.dto.JobDTO;
import com.job_portal_application.entity.Job;
import com.job_portal_application.service.JobService;
import com.job_portal_application.util.responseStructure;

@RestController
@RequestMapping("/job")
public class JobControlletr {
	
	@Autowired
	private JobService jobService;

	@PostMapping
	public ResponseEntity<responseStructure<Job>> addJob(@RequestBody JobDTO jobDTO,@RequestParam long employerId){
		return jobService.addJob(jobDTO, employerId);
	}
}
