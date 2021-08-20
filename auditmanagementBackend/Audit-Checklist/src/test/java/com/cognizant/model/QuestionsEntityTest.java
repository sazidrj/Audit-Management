package com.cognizant.model;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionsEntityTest {

	QuestionsEntity questions = new QuestionsEntity();

	@Test
	public void testQuestionsEntityConstructor() {
		assertNotNull(questions);
	}

	@Test
	public void testQuestionsEntityParameterizedConstructor() {
		QuestionsEntity parameterizedQuestions = new QuestionsEntity(1,"Internal","Is data deleted with permission of user?","Yes");
		assertEquals("Internal",parameterizedQuestions.getAuditType());
	}

	@Test
	public void testSetQUestionId() {
		questions.setQuestionId(2);
		assertEquals(2,questions.getQuestionId().intValue());
	}

	@Test
	public void testAuditType() {
		questions.setAuditType("SOX");
		assertEquals("SOX",questions.getAuditType());
	}

	@Test
	public void testQuestion() {
		questions.setQuestion("How are you");
		assertEquals("How are you",questions.getQuestion());
	}

	@Test
	public void testResponse() {
		questions.setResponse("No");
		assertEquals("No",questions.getResponse());
	}
	
	
}
