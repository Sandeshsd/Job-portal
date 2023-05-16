package com.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.job_portal_application.dto.ResumeDTO;
import com.job_portal_application.entity.Resume;
import com.job_portal_application.service.ResumeService;
import com.job_portal_application.util.responseStructure;

@RestController
@RequestMapping("/resume")
public class ResumeController {
	
	@Autowired
	private ResumeService resumeService;
	
	@PostMapping
	public ResponseEntity<responseStructure<Resume>> saveResume(@RequestParam long applicantId,@RequestBody ResumeDTO resumeDto) {
		return resumeService.saveResume(applicantId, resumeDto);
	}

}
