package com.job_portal_application.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.job_portal_application.dao.ApplicantDAO;
import com.job_portal_application.dao.ProjectDAO;
import com.job_portal_application.dao.ResumeDAO;
import com.job_portal_application.dto.ProjectDTO;
import com.job_portal_application.entity.Applicant;
import com.job_portal_application.entity.Project;
import com.job_portal_application.entity.Resume;
import com.job_portal_application.exception.ApplicantNotfoundByIdException;
import com.job_portal_application.exception.ResumeNotFoundByIdException;
import com.job_portal_application.util.responseStructure;

@Service
public class ProjectService {

	@Autowired
	private ProjectDAO projectDao;
	@Autowired
	private ApplicantDAO applicantDao;
	@Autowired
	private ResumeDAO resumeDao;
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<responseStructure<Resume>> saveProject(long applicantId, ProjectDTO projectDto){
		Applicant applicant = applicantDao.getApplicantById(applicantId);
		if(applicant!=null) {
			Resume resume = applicant.getResume();
			if(resume!=null) {
				List<Project> exProjects = resume.getProjects();
				Project project = this.modelMapper.map(projectDto, Project.class);
				project = projectDao.saveProject(project);
				exProjects.add(project);
				resumeDao.saveResume(resume);
				responseStructure<Resume> responseStructure = new responseStructure<>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Project added successfully!!");
				responseStructure.setData(resume);
				return new ResponseEntity<responseStructure<Resume>>(responseStructure, HttpStatus.CREATED);
			}else {
				throw new ResumeNotFoundByIdException("Failed to add Projects!!");
			}
		}else {
			throw new ApplicantNotfoundByIdException("Failed to add Projects!!");
		}
}
}
	
	
	
	/*
	 * create methods for updating, deleting and fetching the project object.
	 * */
