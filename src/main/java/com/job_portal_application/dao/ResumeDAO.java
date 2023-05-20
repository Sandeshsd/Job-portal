package com.job_portal_application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.job_portal_application.entity.Resume;
import com.job_portal_application.repository.ResumeRepo;

@Repository
public class ResumeDAO {

	@Autowired
	private ResumeRepo resumeRepo;
	
	public Resume saveResume(Resume resume) {
		return resumeRepo.save(resume);
	}
	public void deleteResume(Resume resume) {
		resumeRepo.delete(resume);
	}
	
}
