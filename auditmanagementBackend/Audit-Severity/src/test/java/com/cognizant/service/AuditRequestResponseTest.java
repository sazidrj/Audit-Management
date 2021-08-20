package com.cognizant.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import com.cognizant.model.AuditResponse;
import com.cognizant.model.AuditResponseModel;
import com.cognizant.repository.RequestRepository;
import com.cognizant.repository.ResponseRepository;


@SpringBootTest
public class AuditRequestResponseTest {

	@Mock
	RequestRepository requestRepository;
	@Mock
	ResponseRepository responseRepository;
	
	@InjectMocks
	AuditRequestResponse requestResponse;
	
	@Mock
	Environment env;

	@Test
	public void saveResponseTest() {
		AuditResponse response = new AuditResponse("GREEN","No action required");
		AuditResponseModel detailModel = new AuditResponseModel(response.getProjectExecutionStatus(),response.getRemedialActionDuration());
		when(responseRepository.save(detailModel)).thenReturn(detailModel);
		assertEquals(detailModel, requestResponse.saveResponse(response));
	}
	
}
