package com.cognizant.exception;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@SpringBootTest
public class InvalidUsernameExceptionTest {
	
	@Mock
	Environment env;
	
	@Test
	public void testInvalidUsernameException() {
		InvalidUsernameException usernameException = new InvalidUsernameException(env.getProperty("string.reason.loginfail"));
		assertNotNull(usernameException);
	}

}
