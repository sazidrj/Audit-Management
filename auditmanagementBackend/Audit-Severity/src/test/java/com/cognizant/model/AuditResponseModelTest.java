package com.cognizant.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuditResponseModelTest {

	private AuditResponseModel response;
	
	@BeforeEach
	public void setup() {
		response = new AuditResponseModel();
	}
	
	
	@Test
	public void testGetSetProjectExecutionStatus() {
		response.setExecutionStatus("RED");
		assertEquals("RED", response.getExecutionStatus());
	}
	@Test
	public void testGetSetRemedialActionDuration() {
		response.setActionDuration("None");
		assertEquals("None", response.getActionDuration());
	}
	@Test
	public void testGetResponseId() {
		assertEquals(0, response.getResponseId());
	}
}
