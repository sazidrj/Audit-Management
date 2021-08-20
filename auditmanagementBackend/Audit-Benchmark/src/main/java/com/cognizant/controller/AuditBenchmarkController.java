package com.cognizant.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.feignclient.AuthClient;

import com.cognizant.model.AuditBenchmarkEntity;
import com.cognizant.service.TokenService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuditBenchmarkController {
	@Autowired
	AuthClient authClient;

	@Autowired
	TokenService tokenService;

	@Autowired
	Environment env;

	/**
	 * 
	 * @param token - used to verfiy the token with auth service
	 * @return response entity which is either List of questions or error caused in
	 *         application
	 */
	@GetMapping("/AuditBenchmark")
	public ResponseEntity<?> getBenchmarkMap(@RequestHeader("Authorization") String token) {
		
		List<AuditBenchmarkEntity> auditBenchmarkList = new ArrayList<AuditBenchmarkEntity>();
		ResponseEntity<?> responseEntity = null;
		
		log.info(env.getProperty("benchmarkapp.getbenchmark")+token);
		
		auditBenchmarkList.add(new AuditBenchmarkEntity("Internal", 3));
		auditBenchmarkList.add(new AuditBenchmarkEntity("SOX", 1));
		
		if (tokenService.checkTokenValidity(token)) {
			responseEntity = new ResponseEntity<List<AuditBenchmarkEntity>>(auditBenchmarkList, HttpStatus.OK);
		} else {
			log.error(env.getProperty("benchmarkapp.getbenchmark")+env.getProperty("string.token.exp"));
			responseEntity = new ResponseEntity<String>(env.getProperty("string.token.exp"), HttpStatus.FORBIDDEN);
			return responseEntity;
		}
		log.info(env.getProperty("benchmarkapp.getbenchmark")+responseEntity);
		return responseEntity;

	}
}