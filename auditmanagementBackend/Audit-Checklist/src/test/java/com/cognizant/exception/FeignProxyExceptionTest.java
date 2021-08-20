package com.cognizant.exception;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FeignProxyExceptionTest{
	
	
	
	@Mock
	FeignProxyException feignProxyException;
	
	@Test
	public void contextLoads() {
		assertNotNull(feignProxyException);
	}
	
	@Test 
	public void testConstructor() {
		
		assertNotNull(new FeignProxyException());
	}

}
