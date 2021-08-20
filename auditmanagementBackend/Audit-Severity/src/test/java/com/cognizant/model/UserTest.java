package com.cognizant.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserTest {

	User loginCredential = new User();

	@Test
	public void testUserLoginCredentialAllConstructor() {
		User credential = new User("abc", "abc");
		assertEquals(credential.getUserId(), "abc");
	}

	@Test
	public void testGetUid() {
		loginCredential.setUserId("abc");
		assertEquals(loginCredential.getUserId(), "abc");
	}

	@Test
	public void testUserPassword() {
		loginCredential.setPassword("abc");
		assertEquals(loginCredential.getPassword(), "abc");
	}

	@Test
	public void testoString() {
		String string = loginCredential.toString();
		assertEquals(loginCredential.toString(), string);
	}

}
