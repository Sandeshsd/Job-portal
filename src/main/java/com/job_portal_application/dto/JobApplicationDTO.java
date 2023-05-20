package com.job_portal_application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobApplicationDTO {

	@JsonIgnore
	private long jobApplicationId;
}
