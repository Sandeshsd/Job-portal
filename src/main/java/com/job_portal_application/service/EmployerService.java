package com.job_portal_application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.job_portal_application.dao.EmployerDAO;
import com.job_portal_application.dao.JobDAO;
import com.job_portal_application.dto.EmployerDTO;
import com.job_portal_application.entity.Employer;
import com.job_portal_application.entity.Job;
import com.job_portal_application.exception.EmployerNotFoundByIdException;
import com.job_portal_application.util.responseStructure;

@Service
public class EmployerService {

	@Autowired
	private EmployerDAO employerDAO;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private JobDAO jobDao;
	
	public ResponseEntity<responseStructure<Employer>> addEmployer(Employer employer){
		Employer employer2= employerDAO.addEmployer(employer);
		
		responseStructure<Employer> responseStructure=new responseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Employer added successfully");
		responseStructure.setData(employer2);
		return new ResponseEntity<responseStructure<Employer>>(responseStructure,HttpStatus.CREATED);	
	}
	
	public ResponseEntity<responseStructure<EmployerDTO>> getEmployerById(int employerId) {
		Employer employer = employerDAO.getEmployerById(employerId);
		if (employer != null) {
			EmployerDTO employerDto = this.modelMapper.map(employer, EmployerDTO.class);
			responseStructure<EmployerDTO> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Employer Found.");
			responseStructure.setData(employerDto);
			return new ResponseEntity<responseStructure<EmployerDTO>>(responseStructure, HttpStatus.FOUND);
		}else {
		throw new EmployerNotFoundByIdException("Failed to find Employer!!");
	}
	}

	public ResponseEntity<responseStructure<EmployerDTO>> updateEmployer(int employerId, Employer employer) {
		Employer exEmployer = employerDAO.getEmployerById(employerId);
		if (exEmployer != null) {
			employer.setEmployerId(exEmployer.getEmployerId());
			employer.setJob(exEmployer.getJob());
			// add employer method used to update
			employer = employerDAO.addEmployer(employer);
			EmployerDTO employerDto = this.modelMapper.map(employer, EmployerDTO.class);
			responseStructure<EmployerDTO> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Employer updated successfully.");
			responseStructure.setData(employerDto);
			return new ResponseEntity<responseStructure<EmployerDTO>>(responseStructure, HttpStatus.OK);
		}
		throw new EmployerNotFoundByIdException("Failed to update Employer!!");
	}

	public ResponseEntity<responseStructure<EmployerDTO>> deleteEmployer(int employerId) {
		Employer employer = employerDAO.getEmployerById(employerId);
		if (employer != null) {
			/* while deleting the employer, the jobs that he created are not
			   deleted rather the employer has to null in job.*/
			for(Job job : employer.getJob()) {
				job.setEmployer(null);
				jobDao.addJob(job);
			}
			// add employer method used to update
			employerDAO.deleteEmployer(employer);
			EmployerDTO employerDto = this.modelMapper.map(employer, EmployerDTO.class);
			responseStructure<EmployerDTO> responseStructure = new responseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Employer updated successfully.");
			responseStructure.setData(employerDto);
			return new ResponseEntity<responseStructure<EmployerDTO>>(responseStructure, HttpStatus.OK);
		}
		throw new EmployerNotFoundByIdException("Failed to update Employer!!");
	}
	
	
	
}
