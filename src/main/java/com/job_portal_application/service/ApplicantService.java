package com.job_portal_application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.job_portal_application.dao.ApplicantDAO;
import com.job_portal_application.dto.ApplicantDTO;
import com.job_portal_application.entity.Applicant;
import com.job_portal_application.util.responseStructure;

@Service
public class ApplicantService {
	
	@Autowired
	private ApplicantDAO  applicantDAO;

	@Autowired
	private ModelMapper modelMapper; 
	
	public ResponseEntity<responseStructure<ApplicantDTO>> saveApplicant(Applicant applicant){
	    
		applicant=applicantDAO.saveApplicant(applicant);
		ApplicantDTO applicantDTO=	this.modelMapper.map(applicant, ApplicantDTO.class);
	
	   responseStructure<ApplicantDTO> responseStructure=new responseStructure<>();
	   responseStructure.setStatusCode(HttpStatus.CREATED.value());
	   responseStructure.setMessage("Applicant Added successfully");
	   responseStructure.setData(applicantDTO);
	   return new ResponseEntity<responseStructure<ApplicantDTO>>(responseStructure,HttpStatus.CREATED);
	}
}
