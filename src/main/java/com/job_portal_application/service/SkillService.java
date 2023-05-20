package com.job_portal_application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.job_portal_application.dao.ApplicantDAO;
import com.job_portal_application.dao.ResumeDAO;
import com.job_portal_application.dao.SkillDAO;
import com.job_portal_application.entity.Applicant;
import com.job_portal_application.entity.Resume;
import com.job_portal_application.entity.Skill;
import com.job_portal_application.exception.ApplicantNotfoundByIdException;
import com.job_portal_application.exception.ResumeNotFoundByIdException;
import com.job_portal_application.util.responseStructure;

@Service
public class SkillService {

	@Autowired
	private SkillDAO skillDao;
	@Autowired
	private ApplicantDAO applicantDao;
	@Autowired
	private ResumeDAO resumeDao;

	public ResponseEntity<responseStructure<Resume>> saveSkill(long applicantId, String[] skills) {
		Applicant applicant = applicantDao.getApplicantById(applicantId);
		if (applicant != null) {
			Resume resume = applicant.getResume();
			if (resume != null) {
				/*
				 * - iterate over the String arrays skills that is received check if the skill
				 * is present with matching name with the user, if present do not add to the
				 * user again, or else create an new skill
				 */

				for (String skill : skills) {
					Skill existingskill = skillDao.getSkillByName(skill);
					List<Skill> exSkills = resume.getSkills();
					if (existingskill != null) {
						if (!resume.getSkills().contains(existingskill)) {
							exSkills.add(existingskill);
						}
						} else {
							Skill newSkill = new Skill();
							newSkill.setSkillName(skill);
							skillDao.saveSkill(newSkill);
							exSkills.add(newSkill);
					}

				}

				resume = resumeDao.saveResume(resume);
				responseStructure<Resume> responseStructure = new responseStructure<>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Resume added successfully!!");
				responseStructure.setData(resume);
				return new ResponseEntity<responseStructure<Resume>>(responseStructure, HttpStatus.CREATED);

			} else {
				throw new ResumeNotFoundByIdException("Failed to add skills!!");
			}
		} else {
			throw new ApplicantNotfoundByIdException("Failed to add Resume!!");
		}
	}
}