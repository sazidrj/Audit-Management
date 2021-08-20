package com.cognizant.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuditBenchmarkTest {

	@Mock
	private AuditBenchmarkEntity benchmark;
	
	@BeforeEach
	public void setup() {
		benchmark = new AuditBenchmarkEntity("Internal", 3);
	}
	
	@Test
	public void testGetSetAuditType() {
		assertEquals("Internal", benchmark.getAuditType());
		benchmark.setAuditType("SOX");
		assertEquals("SOX", benchmark.getAuditType());
	}
	@Test
	public void testGetSetAccNoAnswers() {
		assertEquals(3, benchmark.getAccNoAnswers());
		benchmark.setAccNoAnswers(1);
		assertEquals(1, benchmark.getAccNoAnswers());
	}
}
