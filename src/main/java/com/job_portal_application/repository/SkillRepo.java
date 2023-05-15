package com.job_portal_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.job_portal_application.entity.Skill;

public interface SkillRepo extends JpaRepository<Skill, Long>{

}
