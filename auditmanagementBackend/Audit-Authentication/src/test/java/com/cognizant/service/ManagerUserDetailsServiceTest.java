package com.cognizant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.engine.execution.ExecutableInvoker;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cognizant.model.ProjectManager;
import com.cognizant.repository.ManagerRepository;


@SpringBootTest
public class ManagerUserDetailsServiceTest {


UserDetails userdetails;
	
	@InjectMocks
	ManagerDetailsService managerdetailservice;

	@Mock
	ManagerRepository userservice;

	@Test
	public void loadUserByUsernameTest() {
		
		ProjectManager user1=new ProjectManager("username","pass",null);
		Optional<ProjectManager> data =Optional.of(user1) ;
		when(userservice.findById("username")).thenReturn(data);
		UserDetails loadUserByUsername2 = managerdetailservice.loadUserByUsername("username");
		assertEquals(user1.getUserId(),loadUserByUsername2.getUsername());
	}
	
	@Test
	public void saveUserTest() {
		
		ProjectManager user1=new ProjectManager("username","pass",null);
		managerdetailservice.saveUser(user1);
		verify(userservice,times(1)).save(user1);
	}

}
