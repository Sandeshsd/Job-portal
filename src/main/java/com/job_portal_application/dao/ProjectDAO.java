package com.job_portal_application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.job_portal_application.entity.Project;
import com.job_portal_application.repository.ProjectRepo;

@Repository
public class ProjectDAO {
	
	@Autowired
	private ProjectRepo projectRepo;
	
	public Project saveProject(Project project) {
		return projectRepo.save(project);
	}

	public void deleteProject(Project project) {
		projectRepo.delete(project);
	}

}
