package com.cognizant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.model.QuestionsEntity;
import com.cognizant.repository.QuestionsDao;

/**
 *
 * 			This class is used to load the questions from database and
 *          checking whether the question resides in database or not based on the
 *          given id.
 *
 */

@Service
public class QuestionsService{
	
	@Autowired
	private QuestionsDao questionsDao;
	
	public List<QuestionsEntity> getQuestions(String auditType) throws IndexOutOfBoundsException {
		List<QuestionsEntity> questions= null;
		if(questionsDao.findByAuditType(auditType).isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		questions=questionsDao.findByAuditType(auditType);
		return questions;
	}
	
	public List<QuestionsEntity> saveResponses(List<QuestionsEntity> questionsResponse) {
		return questionsDao.saveAll(questionsResponse);
	}

	
}
