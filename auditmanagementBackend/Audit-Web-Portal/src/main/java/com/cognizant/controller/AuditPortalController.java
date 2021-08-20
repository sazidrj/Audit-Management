package com.cognizant.controller;

import com.cognizant.feignclients.AuditCheckListProxy;
import com.cognizant.feignclients.AuditSeverityProxy;
import com.cognizant.feignclients.AuthClient;
import com.cognizant.model.AuditDetails;
import com.cognizant.model.AuditRequest;
import com.cognizant.model.AuditResponse;
import com.cognizant.model.AuditType;
import com.cognizant.model.ProjectDetails;
import com.cognizant.model.ProjectManager;
import com.cognizant.model.Questions;
import com.cognizant.model.QuestionsEntity;
import com.cognizant.model.User;
import com.cognizant.model.tempClass;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
public class AuditPortalController {

	@Autowired
	AuthClient authClient;

	@Autowired
	AuditCheckListProxy auditCheckListProxy;

	@Autowired
	AuditRequest auditRequest;

	@Autowired
	AuditSeverityProxy auditSeverityProxy;

	@Autowired
	Environment env;

	@PostMapping(path = "/home")
	public ResponseEntity<String> getHome(@RequestBody User userCredentials, HttpServletRequest request)
			throws Exception {

		ResponseEntity<ProjectManager> token = null;
		ProjectManager projectManager = null;
		ResponseEntity<String> authenticationToken = null;
		String validToken = null;

		log.info(env.getProperty("portalapp.home") + userCredentials.toString());
		try {
			token = (ResponseEntity<ProjectManager>) authClient.login(userCredentials);
			projectManager = token.getBody();
			validToken = "Bearer " + projectManager.getAuthToken();
			log.info(env.getProperty("portalapp.home") + validToken);
			authenticationToken = new ResponseEntity<String>(validToken, HttpStatus.OK);
			return authenticationToken;
		} catch (Exception e) {
			log.error(env.getProperty("portalapp.home") + env.getProperty("string.invalid.cred"));
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping(path = "/auditCheckListQuestions")
	public ResponseEntity<Questions> getResponses(@RequestBody tempClass temp1, HttpServletRequest request)
			throws Exception {

		AuditType auditType = temp1.getAuditType();
		ProjectDetails projectDetails = temp1.getProjectDetails();
		String temp = request.getHeader("Authorization");
		String header = null;
		List<QuestionsEntity> questions = new ArrayList<>();
		Questions questionslist = new Questions();
		ResponseEntity<Questions> ques = null;

		log.info(env.getProperty("portalapp.checklist") + projectDetails.toString());
		log.info(env.getProperty("portalapp.checklist") + auditType.toString());

		auditRequest.setProjectName(projectDetails.getProjectName());
		auditRequest.setProjectManagerName(projectDetails.getProjectManagerName());
		auditRequest.setApplicationOwnerName(projectDetails.getApplicationOwnerName());

		try {
			header = temp.substring(1, temp.length() - 1);
			questions = auditCheckListProxy.getCheckList(header, auditType).getBody();
		} catch (IndexOutOfBoundsException e) {
			if (e.getMessage().contains(env.getProperty("string.null.exception"))) {
				log.error(env.getProperty("portalapp.checklist") + env.getProperty("string.null.exception"));
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			if (e.getMessage().contains(env.getProperty("string.token.exp"))) {
				log.error(env.getProperty("portalapp.checklist") + env.getProperty("string.token.exp"));
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
			log.error(env.getProperty("portalapp.checklist") + env.getProperty("string.null"));
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		for (QuestionsEntity question : questions) {
			if (question.getResponse() != null) {
				question.setResponse(null);
			}
		}
		questionslist.setQuestionsEntity(questions);

		log.info(env.getProperty("portalapp.checklist") + questionslist.toString());

		ques = new ResponseEntity<Questions>(questionslist, HttpStatus.OK);
		return ques;
	}

	@PostMapping("/questions")
	public ResponseEntity<Questions> getResponses(@RequestBody Questions questions, HttpServletRequest request) {

		ResponseEntity<?> authResponse = null;
		List<QuestionsEntity> responseEntity = null;
		List<QuestionsEntity> questionsEntity = questions.getQuestionsEntity();
		AuditDetails auditDetails = null;
		String header = null;
		ResponseEntity<Questions> response = null;
		String temp = request.getHeader("Authorization");

		log.info(env.getProperty("portalapp.questions") + questions);

		try {
			header = temp.substring(1, temp.length() - 1);
			authResponse = authClient.getValidity(header);
			responseEntity = auditCheckListProxy.saveResponses(header, questionsEntity).getBody();
		} catch (Exception e) {
			if (e.getMessage().contains(env.getProperty("token.expired"))) {
				log.error(env.getProperty("portalapp.questions") + env.getProperty("token.expired"));
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
//			log.error(env.getProperty("portalapp.questions") + env.getProperty("string.null"));
			return new ResponseEntity<Questions>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (authResponse == null || responseEntity == null) {
			log.error(env.getProperty("portalapp.questions") + env.getProperty("token.expired"));
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}

		try {
			auditDetails = new AuditDetails(questions.getQuestionsEntity().get(0).getAuditType(), new Date());
		} catch (Exception e) {
			log.error(env.getProperty("portalapp.questions") + env.getProperty("string.null"));
			return new ResponseEntity<Questions>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		auditRequest.setAuditDetails(auditDetails);
		response = new ResponseEntity<Questions>(questions, HttpStatus.OK);
		log.info(env.getProperty("portalapp.questions") + auditRequest);
		return response;

	}

	@GetMapping("/status")
	public ResponseEntity<?> getProjectExecutionStatus(HttpServletRequest request) {

		AuditResponse auditResponse = null;
		String header = null;
		String temp = request.getHeader("Authorization");
		ResponseEntity<?> status;
		
		log.info(env.getProperty("portalapp.status") + temp);

		try {
			header = temp.substring(1, temp.length() - 1);
			auditResponse = auditSeverityProxy.auditSeverity(header, auditRequest).getBody();
		} catch (IndexOutOfBoundsException e) {
			if (e.getMessage().contains(env.getProperty("string.null.exception"))) {
				log.error(env.getProperty("portalapp.status") + env.getProperty("string.null.exception"));
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			if (e.getMessage().contains(env.getProperty("string.token.exp"))) {
				log.error(env.getProperty("portalapp.status") + env.getProperty("string.token.exp"));
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
			log.error(env.getProperty("portalapp.status") + env.getProperty("string.null"));
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		status = new ResponseEntity<AuditResponse>(auditResponse, HttpStatus.OK);
		log.info(env.getProperty("portalapp.status") + auditResponse);
		return status;

	}

}
