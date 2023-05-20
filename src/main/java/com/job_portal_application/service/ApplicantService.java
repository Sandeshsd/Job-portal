package com.job_portal_application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.job_portal_application.dao.ApplicantDAO;
import com.job_portal_application.dao.JobApplicationDAO;
import com.job_portal_application.dao.ProjectDAO;
import com.job_portal_application.dao.ResumeDAO;
import com.job_portal_application.dto.ApplicantDTO;
import com.job_portal_application.entity.Applicant;
import com.job_portal_application.entity.JobApplication;
import com.job_portal_application.entity.Project;
import com.job_portal_application.entity.Resume;
import com.job_portal_application.exception.ApplicantNotfoundByIdException;
import com.job_portal_application.util.responseStructure;

@Service
public class ApplicantService {
	
	@Autowired
	private ApplicantDAO  applicantDAO;

	@Autowired
	private ModelMapper modelMapper; 
	@Autowired
	private ResumeDAO resumeDao;
	@Autowired
	private JobApplicationDAO jobApplicationDao;
	@Autowired
	private ProjectDAO projectDao;
	
	public ResponseEntity<responseStructure<ApplicantDTO>> saveApplicant(Applicant applicant){
	    
		applicant=applicantDAO.saveApplicant(applicant);
		ApplicantDTO applicantDTO=	this.modelMapper.map(applicant, ApplicantDTO.class);
	
	   responseStructure<ApplicantDTO> responseStructure=new responseStructure<>();
	   responseStructure.setStatusCode(HttpStatus.CREATED.value());
	   responseStructure.setMessage("Applicant Added successfully");
	   responseStructure.setData(applicantDTO);
	   return new ResponseEntity<responseStructure<ApplicantDTO>>(responseStructure,HttpStatus.CREATED);
	}

	public ResponseEntity<responseStructure<ApplicantDTO>> getApplicantById(int applicantId) {
		Applicant applicant = applicantDAO.getApplicantById(applicantId);
		if (applicant != null) {
			ApplicantDTO applicantDto = this.modelMapper.map(applicant, ApplicantDTO.class);
			responseStructure<ApplicantDTO> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Applicant Found.");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<responseStructure<ApplicantDTO>>(responseStructure, HttpStatus.FOUND);
		}else {
		throw new ApplicantNotfoundByIdException("Failed to find Applicant!!");
	}
	}

	public ResponseEntity<responseStructure<ApplicantDTO>> updateApplicant(int applicantId, Applicant applicant) {
		Applicant exApplicant = applicantDAO.getApplicantById(applicantId);
		if (exApplicant != null) {
			applicant.setApplicantId(exApplicant.getApplicantId());
			applicant.setJobApplications(exApplicant.getJobApplications());
			applicant.setResume(exApplicant.getResume());
			applicantDAO.saveApplicant(applicant);
			ApplicantDTO applicantDto = this.modelMapper.map(applicant, ApplicantDTO.class);
			responseStructure<ApplicantDTO> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Applicant updated successfully.");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<responseStructure<ApplicantDTO>>(responseStructure, HttpStatus.OK);
		}else {
		throw new ApplicantNotfoundByIdException("Failed to update Applicant!!");

		}	
	}

	public ResponseEntity<responseStructure<ApplicantDTO>> deleteApplicant(int applicantId) {
	
Applicant applicant = applicantDAO.getApplicantById(applicantId);
		
		if (applicant != null) {
			/*Before deleting the applicant the applicant is to null in all the
			  jobApplications later the applicant is delete the applicant */
			for (JobApplication jobApplication : applicant.getJobApplications()) {
				jobApplication.setApplicant(null);
				// createJobApplication() method used to update
				jobApplicationDao.createJobApplication(jobApplication);
			}
			applicantDAO.deleteApplicant(applicant);
			Resume resume = applicant.getResume();
			/*After the applicant is deleted the the resume linked to the applicant
			 * should be deleted*/
			if(resume!=null) {
				/*Before deleting the resume the skills should be set to null
				 * in the resume.*/
				resume.setSkills(null);
				// saveResume() method used to update
				resumeDao.saveResume(resume);
				for (Project project : applicant.getResume().getProjects()) {
					projectDao.deleteProject(project);
				}
					resumeDao.deleteResume(resume);
			}


			ApplicantDTO applicantDto = this.modelMapper.map(applicant, ApplicantDTO.class);
			responseStructure<ApplicantDTO> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Applicant deleted successfully.");
			responseStructure.setData(applicantDto);
			return new ResponseEntity<responseStructure<ApplicantDTO>>(responseStructure, HttpStatus.OK);
		}
		throw new ApplicantNotfoundByIdException("Failed to delete Applicant!!");

	}
}
