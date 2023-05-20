package com.job_portal_application.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.job_portal_application.dao.ApplicantDAO;
import com.job_portal_application.dao.JobApplicationDAO;
import com.job_portal_application.dao.JobDAO;
import com.job_portal_application.entity.Applicant;
import com.job_portal_application.entity.Job;
import com.job_portal_application.entity.JobApplication;
import com.job_portal_application.exception.ApplicantNotfoundByIdException;
import com.job_portal_application.exception.jobNotFoundByIdException;
import com.job_portal_application.util.responseStructure;
@Service
public class JobApplicationService {
	@Autowired
	private JobApplicationDAO jobApplicationDao;
	@Autowired
	private ApplicantDAO applicantDao;
	@Autowired
	private JobDAO jobDao;
	
	
	public ResponseEntity<responseStructure<JobApplication>> createJobApplication(
			long applicantId, long jobId){
		Applicant applicant = applicantDao.getApplicantById(applicantId);
		if(applicant!=null) {
			
			Job job = jobDao.getJob(jobId);
			if(job!=null) {
				JobApplication application = new  JobApplication();
				application.setJobApplicationDateTime(LocalDateTime.now());
				application.setJob(job);
				application.setApplicant(applicant);
				
				// saving the jobApplication object
				application = jobApplicationDao.createJobApplication(application);
				
				// setting and updating jobApplication for the job
				job.getJobApplications().add(application);
				jobDao.addJob(job);
				
				//  setting and updating jobApplication for the Applicant
				applicant.getJobApplications().add(application);
				applicantDao.saveApplicant(applicant);
				
				responseStructure<JobApplication> responseStructure = new responseStructure<>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Job Application added Successfully!!");
				responseStructure.setData(application);
				return new ResponseEntity<responseStructure<JobApplication >> (responseStructure, HttpStatus.CREATED);
				
			}else {
				throw new jobNotFoundByIdException("Failed to create job Application!!");
		
			}
			
		}else {
			throw new ApplicantNotfoundByIdException("Failed to create Job Application!!");
		}
}
}
