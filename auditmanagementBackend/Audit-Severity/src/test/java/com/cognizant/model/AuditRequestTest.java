package com.cognizant.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuditRequestTest {

	private AuditRequest request;
	
	@BeforeEach
	public void setup() {
		request = new AuditRequest();
	}
	
	@Test
	public void testGetSetProjectName() {
		request.setProjectName("ProjectName");
		assertEquals("ProjectName", request.getProjectName());
	}
	@Test
	public void testGetSetProjectManagerName() {
		request.setProjectManagerName("ManagerName");
		assertEquals("ManagerName", request.getProjectManagerName());
	}
	@Test
	public void testGetSetOwnerName() {
		request.setApplicationOwnerName("OwnerName");
		assertEquals("OwnerName", request.getApplicationOwnerName());
	}
	@Test
	public void testGetSetAuditDetails() {
		AuditDetails details = new AuditDetails();
		request.setAuditDetails(details);
		assertEquals(details, request.getAuditDetails());
	}
}
