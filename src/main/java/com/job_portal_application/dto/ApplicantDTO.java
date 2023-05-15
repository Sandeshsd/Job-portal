package com.job_portal_application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantDTO {

	private long applicantId;
	private String applicantName;
	private String applicantEmail;
	private String applicantPassword;
	private long applicantPhoneNumber;
}
