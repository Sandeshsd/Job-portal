package com.job_portal_application.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Job {
 
	 @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobId;
	private String jobTitle;
	private String jobDescription;
	private String jobCompany;
	private double jobSalary;
	private LocalDateTime jobCreateDateTime;
	
	@OneToMany
	private List<JobApplication> jobApplications;
	
	@ManyToOne
	private Employer employer;
	
}