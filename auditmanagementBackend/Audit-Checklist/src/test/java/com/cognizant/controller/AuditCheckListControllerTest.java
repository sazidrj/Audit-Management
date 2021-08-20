package com.cognizant.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.feignclient.AuthClient;
import com.cognizant.model.AuditType;
import com.cognizant.model.QuestionsEntity;
import com.cognizant.repository.QuestionsDao;
import com.cognizant.service.QuestionsService;
import com.cognizant.service.TokenService;



@SpringBootTest
public class AuditCheckListControllerTest {

	@Mock
	AuthClient authClient;
	
	@Mock
	TokenService tokenService;
	
	@Mock
	AuditType auditType;
	
	@Mock
	QuestionsService questionsService;
	
	@Mock
	Environment env;
	
	@InjectMocks
	AuditChecklistController auditCheckListController;
	
	@Mock
	QuestionsDao questionsRepository;
	
	@Test
	public void contextLoads() {
		assertNotNull(auditCheckListController);
	}

	@Test
	public void testGetChecklist() {
		ResponseEntity<?> responseEntity = null;
		List<QuestionsEntity> questionsList = new ArrayList<QuestionsEntity>();
		questionsList.add(new QuestionsEntity(1,"Internal","How are you","Yes"));
		when(tokenService.checkTokenValidity("token")).thenReturn(true);
		when(questionsService.getQuestions("Internal")).thenReturn(questionsList);
		responseEntity = auditCheckListController.getChecklist("token", auditType);
		assertNotNull(responseEntity);
			
	}
	
	@Test
	public void testGetChecklistTokenInvalid() {
		ResponseEntity<?> responseEntity = null;
		when(tokenService.checkTokenValidity("token")).thenReturn(false);
		responseEntity = auditCheckListController.getChecklist("token", auditType);
		assertEquals(HttpStatus.FORBIDDEN,responseEntity.getStatusCode());
	}
	
	
	
	@Test
	public void testSaveResponses() {
		List<QuestionsEntity> questionsList = new ArrayList<QuestionsEntity>();
		questionsList.add(new QuestionsEntity(1,"Internal","How are you","Yes"));
		when(tokenService.checkTokenValidity("token")).thenReturn(true);
		assertEquals(HttpStatus.OK,auditCheckListController.saveResponses("token", questionsList).getStatusCode());
	}
	
	@Test
	public void testSaveResponseTokenInvalid() {
		ResponseEntity<?> responseEntity = null;
		List<QuestionsEntity> questionsList = new ArrayList<QuestionsEntity>();
		questionsList.add(new QuestionsEntity(1,"Internal","How are you","Yes"));
		when(tokenService.checkTokenValidity("token")).thenReturn(false);
		responseEntity = auditCheckListController.saveResponses("token",questionsList);
		assertEquals(HttpStatus.FORBIDDEN,responseEntity.getStatusCode());
	}
	
	
	
	
}
