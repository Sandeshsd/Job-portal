package com.job_portal_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job_portal_application.entity.JobApplication;

public interface JobApplicationRepo extends JpaRepository<JobApplication, Long> {

}
