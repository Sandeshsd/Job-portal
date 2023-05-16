package com.job_portal_application.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Resume {

	 @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long resumeId;
	private String summary;
	private String qulification;
	
	private String socialProfile1;
	private String socialProfile2;
	private String socialProfile3;
	private String certification;
	
	@OneToOne(mappedBy = "resume")
	private Applicant applicant;
	
	@OneToMany
	private List<Project> projects;
	
	@OneToMany
	private List<Skill> skills;
	
	
}
