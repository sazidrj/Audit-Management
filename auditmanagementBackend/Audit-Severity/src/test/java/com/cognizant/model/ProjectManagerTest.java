package com.cognizant.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProjectManagerTest {
	
	ProjectManager projectManager = new ProjectManager();

	@Test
	public void testProjectManagerAllConstructor()
	{
		ProjectManager manager=new ProjectManager("ab", "ab", null);
		assertEquals( "ab" , manager.getUserId());
	}

	@Test
	public void testUserid()
	{
		projectManager.setUserId("abc");
		assertEquals( "abc",  projectManager.getUserId());
	}

	@Test
	public void testUserPassword()
	{
		projectManager.setPassword("abc");
		assertEquals( "abc" , projectManager.getPassword());
	}

	@Test
	public void testAuthToken()
	{
		projectManager.setAuthToken("abc");
		assertEquals("abc", projectManager.getAuthToken());
	}

	@Test
	public void testoString() {
		String string = projectManager.toString();
		assertEquals(string , projectManager.toString());
	}

}
