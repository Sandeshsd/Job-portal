package com.job_portal_application.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.job_portal_application.entity.Applicant;
import com.job_portal_application.repository.ApplicantRepo;

@Repository
public class ApplicantDAO {

	@Autowired
	private ApplicantRepo applicantRepo;
	
	public Applicant saveApplicant(Applicant applicant) {
		return applicantRepo.save(applicant);
	}

	public Applicant getApplicantById(long applicantId) {
		Optional<Applicant> optional=applicantRepo.findById(applicantId);
		if(optional.isEmpty()) {
			return null;
		}else {
		return optional.get();
		}
	}

	public void deleteApplicant(Applicant applicant) {
		applicantRepo.delete(applicant);
	}
}
