package com.cognizant.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuditDetailTest {

	private AuditDetails details;
	
	@BeforeEach
	public void setup() {
		details = new AuditDetails();
	}
	
	@Test
	public void testGetSetAuditType() {
		details.setAuditType("Internal");
		assertEquals("Internal", details.getAuditType());
	}
	
	@Test
	public void testGetSetAuditDate() {
		Date date = new Date();
		details.setAuditDate(date);
		assertEquals(date, details.getAuditDate());
	}
}
