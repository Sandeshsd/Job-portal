package com.job_portal_application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
public class ResumeNotFoundByIdException extends RuntimeException {

	
	private String message;
}
