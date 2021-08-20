package com.cognizant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.model.QuestionsEntity;
import com.cognizant.repository.QuestionsDao;

@SpringBootTest
public class QuestionsServiceTest {

	@Mock
	QuestionsDao questionsRespository;
	
	@InjectMocks
	QuestionsService questionsService;

	@Test
	public void testGetQuestionsList() throws IndexOutOfBoundsException{
		List<QuestionsEntity> questions = new ArrayList<>();
		questions.add(new QuestionsEntity(1,"Internal","Have all Change requests followed SDLC before PROD move?","Yes"));
		when(questionsRespository.findByAuditType("Internal")).thenReturn(questions);
		assertEquals(questions,questionsService.getQuestions("Internal"));
	}

	@Test
	public void testQuestionsListthrowsIndexOutOfBoundsException() {
		when(questionsRespository.findByAuditType("Internal")).thenThrow(IndexOutOfBoundsException.class);
		assertThrows(IndexOutOfBoundsException.class,() -> questionsService.getQuestions("Internal"));
	}

	@Test
	public void testSaveResponses() {
		List<QuestionsEntity> questions = new ArrayList<>();
		questions.add(new QuestionsEntity(1,"Internal","Have all Change requests followed SDLC before PROD move?","Yes"));
		when(questionsRespository.saveAll(questions)).thenReturn(questions);
		assertEquals(questions,questionsService.saveResponses(questions));
	}
}
