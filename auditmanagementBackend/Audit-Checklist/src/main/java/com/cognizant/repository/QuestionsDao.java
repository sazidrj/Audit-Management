package com.cognizant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.model.QuestionsEntity;

/**
 * 
 *    This interface communicates with database to fetch the questions from 
 *    auditquestions table on the basis of audit type.
 *
 */
@Repository
public interface QuestionsDao extends JpaRepository<QuestionsEntity, Integer>{
	
	List<QuestionsEntity> findByAuditType(String i);
	
}
