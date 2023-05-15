package com.job_portal_application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResumeDTO {

	private long resumeId;
	private String summary;
	private String Qulification;
	
	private String socialProfile1;
	private String socialProfile2;
	private String socialProfile3;
	private String certification;
}
