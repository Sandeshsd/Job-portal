package com.job_portal_application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobDTO {

	
	private long jobId;
	private String jobTitle;
	private String jobDescription;
	private String jobCompany;
	private double jobSalary;
}
