package com.job_portal_application.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.job_portal_application.dao.EmployerDAO;
import com.job_portal_application.dao.JobDAO;
import com.job_portal_application.dto.JobDTO;
import com.job_portal_application.entity.Employer;
import com.job_portal_application.entity.Job;
import com.job_portal_application.util.responseStructure;

@Service
public class JobService {

	
	@Autowired
	private JobDAO jobDAO;
	
	@Autowired
	private EmployerDAO employerDAO;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public ResponseEntity<responseStructure<Job>> addJob(JobDTO jobDto,long employerId){
	Employer employer=	employerDAO.getEmployerById(employerId);
	if(employer!=null) {
		Job job=this.modelMapper.map(jobDto, Job.class);
		job.setJobCreateDateTime(LocalDateTime.now());
		job.setEmployer(employer);
		job=jobDAO.addJob(job);
		employer.getJob().add(job);
		employerDAO.addEmployer(employer);
		
		jobDto.setJobId(job.getJobId());
		responseStructure<Job> responseStructure=new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Job added successfully");
		responseStructure.setData(jobDto);
		
		return new ResponseEntity<responseStructure<Job>>(responseStructure,HttpStatus.CREATED);
	}else {
		return null;
	}
		
		
	}
}
/*
 * 1) write a method to fetch the Job bu Id,
 * ----- fetch the job from database based on Id,
  		 check if the returned object is null or not
  		 if not null - 
  		 return responseEntity,
  		 else - 
  		 throw new exception;
  		 
  		 
  *2) write a method to update Job, 
     fetch the existing Job by id,
      if present set the existing jobId to the updated Job(the object received in the
      method parameter)
      set the exiting jobApplication list to the updated Job entity,
      set the existing employer to the updated job object.
      then,
      update the job object and return response entity.
      
  
  *3) write a method to delete JOb,
  * before deleting the job object,
  * set job as null to all the job applications and
  * set job as null to employer 
  * then 
  * delete the job object.*/