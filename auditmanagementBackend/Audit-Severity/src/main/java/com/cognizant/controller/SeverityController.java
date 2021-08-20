package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.feignclient.AuditBenchmarkFeignclient;
import com.cognizant.feignclient.AuditCheckListProxy;
import com.cognizant.feignclient.AuthClient;
import com.cognizant.model.AuditBenchmark;
import com.cognizant.model.AuditRequest;
import com.cognizant.model.AuditResponse;
import com.cognizant.model.AuditType;
import com.cognizant.model.QuestionsEntity;
import com.cognizant.service.AuditRequestResponse;
import com.cognizant.service.TokenService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class is handling all the end points for Audit Checklist microservice.
 * This controller has mappings as- postmapping-auditSeverity()
 *
 */

@RestController
@Slf4j
public class SeverityController {

	@Autowired
	private AuditRequestResponse service;
	@Autowired
	AuthClient authClient;
	@Autowired
	AuditCheckListProxy checklistProxy;

	@Autowired
	TokenService tokenService;

	@Autowired
	Environment env;

	@Autowired
	AuditBenchmarkFeignclient auditBenchmarkFeignclient;

	/**
	 * 
	 * @param token
	 * @param auditRequest
	 * @return ResponseEntity<Response>
	 */
	@PostMapping("/ProjectExecutionStatus")
	public ResponseEntity<?> auditSeverity(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody AuditRequest auditRequest) {

		int accNoAnswers = 0;
		int actualNoAnswers = 0;
		ResponseEntity<?> responseEntity = null;
		List<QuestionsEntity> questionsList = null;
		List<AuditBenchmark> benchmarkList = null;
		AuditType auditType = new AuditType(auditRequest.getAuditDetails().getAuditType());
		AuditResponse response = null;

		log.info(env.getProperty("severityapp.projectstatus") + token);
		log.info(env.getProperty("severityapp.projectstatus") + auditRequest);

		if (tokenService.checkTokenValidity(token)) {

			benchmarkList = auditBenchmarkFeignclient.getBenchmarkMap(token).getBody();
			log.info(env.getProperty("severityapp.projectstatus") + benchmarkList);

			for (AuditBenchmark benchmark : benchmarkList) {
				if (benchmark.getAuditType().equalsIgnoreCase(auditRequest.getAuditDetails().getAuditType())) {
					accNoAnswers = benchmark.getAccNoAnswers();
				}
			}

			try {
				questionsList = checklistProxy.getChecklist(token, auditType).getBody();
			} catch (IndexOutOfBoundsException e) {
				if (e.getMessage().contains(env.getProperty("string.null.exception"))) {
					log.error(env.getProperty("severityapp.projectstatus") + env.getProperty("string.null.exception"));

					return new ResponseEntity<String>(env.getProperty("string.null.exception"),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				if (e.getMessage().contains(env.getProperty("string.token.exp"))) {
					log.error(env.getProperty("severityapp.projectstatus") + env.getProperty("string.token.exp"));

					return new ResponseEntity<String>(env.getProperty("string.token.exp"), HttpStatus.FORBIDDEN);
				}
				log.error(env.getProperty("severityapp.projectstatus") + env.getProperty("string.null"));
				return new ResponseEntity<String>(env.getProperty("string.null"), HttpStatus.UNAUTHORIZED);
			}

			for (QuestionsEntity answer : questionsList) {
				if (answer.getResponse().equalsIgnoreCase("No")) {
					actualNoAnswers++;
				}
			}

			if (actualNoAnswers <= accNoAnswers) {
				responseEntity = new ResponseEntity<AuditResponse>(new AuditResponse(
						env.getProperty("project.status.green"), env.getProperty("remedial.action.no")), HttpStatus.OK);
				response = (AuditResponse) responseEntity.getBody();
				service.saveResponse(response);
			} else if (auditRequest.getAuditDetails().getAuditType().equalsIgnoreCase("Internal")) {
				responseEntity = new ResponseEntity<AuditResponse>(
						new AuditResponse(env.getProperty("project.status.red"),
								env.getProperty("remedial.action.yes.one")),
						HttpStatus.OK);
				response = (AuditResponse) responseEntity.getBody();
				service.saveResponse(response);
			} else if (auditRequest.getAuditDetails().getAuditType().equalsIgnoreCase("SOX")) {
				responseEntity = new ResponseEntity<AuditResponse>(
						new AuditResponse(env.getProperty("project.status.red"),
								env.getProperty("remedial.action.yes.two")),
						HttpStatus.OK);
				response = (AuditResponse) responseEntity.getBody();
				service.saveResponse(response);
			}
			service.saveRequest(auditRequest);
			log.info(env.getProperty("severityapp.projectstatus")+ responseEntity);
			return responseEntity;
		} else {
			log.error(env.getProperty("severityapp.projectstatus") + env.getProperty("string.token.exp"));
			responseEntity = new ResponseEntity<String>(env.getProperty("string.token.exp"), HttpStatus.FORBIDDEN);
			return responseEntity;
		}
	}
}
