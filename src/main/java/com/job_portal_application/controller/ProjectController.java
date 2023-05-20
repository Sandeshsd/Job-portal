package com.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.job_portal_application.dto.ProjectDTO;
import com.job_portal_application.entity.Resume;
import com.job_portal_application.service.ProjectService;
import com.job_portal_application.util.responseStructure;
@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@PostMapping
	public ResponseEntity<responseStructure<Resume>> saveProject(@RequestParam long applicantId, @RequestBody ProjectDTO projectDto){
		return projectService.saveProject(applicantId, projectDto);
	}
}
