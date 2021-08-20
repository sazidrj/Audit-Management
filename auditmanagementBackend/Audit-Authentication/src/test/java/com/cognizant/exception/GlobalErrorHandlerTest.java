package com.cognizant.exception;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@SpringBootTest
public class GlobalErrorHandlerTest {

	@InjectMocks
	GlobalErrorHandler handler;

	@Mock
	Environment env;
	
	@Test
	public void contextLoads() {
		assertNotNull(handler);
	}
	
	@Test
	public void testhandelWrongDateFormatException() {
		assertNotNull(handler.handleIdNotFoundexception(new LoginFailedException()));
		assertNotNull(handler.handleUsernameNotFoundexception(new InvalidUsernameException()));
	}
	
	@Test
	public void testhandelWrongDateFormatExceptionThrowable() {
		assertNotNull(handler.handleIdNotFoundexception(new LoginFailedException(new Throwable())));
		assertNotNull(handler.handleUsernameNotFoundexception(new InvalidUsernameException(new Throwable())));
	}
	
	@Test
	public void testhandelWrongDateFormatExceptionmsg() {
		assertNotNull(handler.handleIdNotFoundexception(new LoginFailedException("msg")));
		assertNotNull(handler.handleTokenNotFoundException(new TokenExpiredException("msg")));
		assertNotNull(handler.handleUserNotFoundexception(new UsernameNotFoundException("msg")));	
		assertNotNull(handler.handleUsernameNotFoundexception(new InvalidUsernameException("msg")));
	}
	
	@Test
	public void testhandelWrongDateFormatExceptionmsgThrowable() {
		assertNotNull(handler.handleIdNotFoundexception(new LoginFailedException("msg",new Throwable())));
		assertNotNull(handler.handleUserNotFoundexception(new UsernameNotFoundException("msg",new Throwable())));	
		assertNotNull(handler.handleUsernameNotFoundexception(new InvalidUsernameException("msg",new Throwable())));	

	}
	
	@Test
	public void testhandelWrongDateFormatExceptionmsgThrowtrue() {
		assertNotNull(handler.handleIdNotFoundexception(new LoginFailedException("msg",new Throwable(),true,true)));
		assertNotNull(handler.handleUsernameNotFoundexception(new InvalidUsernameException("msg",new Throwable(),true,true)));
	}
}
