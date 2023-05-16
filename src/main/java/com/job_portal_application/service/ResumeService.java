package com.job_portal_application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.job_portal_application.dao.ApplicantDAO;
import com.job_portal_application.dao.ResumeDAO;
import com.job_portal_application.dto.ResumeDTO;
import com.job_portal_application.entity.Applicant;
import com.job_portal_application.entity.Resume;
import com.job_portal_application.util.responseStructure;


@Service
public class ResumeService {

	@Autowired
	private ResumeDAO resumeDAO;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ApplicantDAO applicantDAO;
	
	public ResponseEntity<responseStructure<Resume>> saveResume(long applicantId, ResumeDTO resumeDTO){
		Applicant applicant=applicantDAO.getApplicantById(applicantId);
		if(applicant!=null) {
		
		Resume resume= this.modelMapper.map(resumeDTO, Resume.class);
		resume.setApplicant(applicant);
		resume=resumeDAO.saveResume(resume);
		applicant.setResume(resume);
		applicantDAO.saveApplicant(applicant);
		
		responseStructure<Resume> responseStructure=new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Resume is added");
		responseStructure.setData(resume);
		return new ResponseEntity<responseStructure<Resume>>(responseStructure,HttpStatus.CREATED);
		}else {
//			return new ApplicationNotFoundByIdException("failed to add Resume!!");
			return null;
		}
	}
}
