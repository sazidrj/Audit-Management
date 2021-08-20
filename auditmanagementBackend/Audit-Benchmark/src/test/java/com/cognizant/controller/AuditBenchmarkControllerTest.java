package com.cognizant.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import com.cognizant.feignclient.AuthClient;
import com.cognizant.model.AuditBenchmarkEntity;

import com.cognizant.service.TokenService;

@SpringBootTest
public class AuditBenchmarkControllerTest {
	
	@InjectMocks
	AuditBenchmarkController controller;

	@Mock
	AuthClient authClient;
		
	@Mock
	TokenService tokenService;
	
	@Mock
	Environment env;
	
	@Mock
	AuditBenchmarkEntity auditBenchmark;
	
	
	

	@Test
	public void testGetBenchmarkMap() {
		List<AuditBenchmarkEntity> auditBenchmarkList = new ArrayList<>();
		auditBenchmarkList.add(new AuditBenchmarkEntity("Internal", 3));
		auditBenchmarkList.add(new AuditBenchmarkEntity("SOX", 1));
		when(tokenService.checkTokenValidity("token")).thenReturn(true);
		assertEquals(auditBenchmarkList,controller.getBenchmarkMap("token").getBody());
	}
	
	@Test
	public void testTokenInvalid() {
		when(tokenService.checkTokenValidity("token")).thenReturn(false);
		assertEquals(HttpStatus.FORBIDDEN,controller.getBenchmarkMap("token").getStatusCode());
	}
}
