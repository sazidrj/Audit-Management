package com.cognizant.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuditBenchmarkTest {

	private AuditBenchmark benchmark;
	
	@BeforeEach
	public void setup() {
		benchmark = new AuditBenchmark();
	}

	@Test
	public void testGetSetAuditType() {
		benchmark.setAuditType("Internal");
		assertEquals("Internal", benchmark.getAuditType());
	}

	@Test
	public void testGetSetAccNoAnswers() {
		benchmark.setAccNoAnswers(12);
		assertEquals(12, benchmark.getAccNoAnswers());
	}
}
