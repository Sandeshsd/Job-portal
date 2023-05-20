package com.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.job_portal_application.entity.Resume;
import com.job_portal_application.service.SkillService;
import com.job_portal_application.util.responseStructure;

@RestController
@RequestMapping("/skill")
public class SkillController {
	
	@Autowired
	private SkillService skillService;
	
	@PostMapping
	public ResponseEntity<responseStructure<Resume>> saveSkills(@RequestParam long applicantId, @RequestBody String[] skills){
		return skillService.saveSkill(applicantId, skills);
	}

}
