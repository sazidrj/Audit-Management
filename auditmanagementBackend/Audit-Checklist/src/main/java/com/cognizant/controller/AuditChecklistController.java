package com.cognizant.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.feignclient.AuthClient;
import com.cognizant.model.AuditType;
import com.cognizant.model.QuestionsEntity;
import com.cognizant.service.QuestionsService;
import com.cognizant.service.TokenService;

import lombok.extern.slf4j.Slf4j;

/*
* This class is handling all the end points for Audit Checklist microservice. 
*/

@RestController
@CrossOrigin("*")
@Slf4j
public class AuditChecklistController {

	@Autowired
	AuthClient authClient;

	@Autowired
	QuestionsService questionService;

	@Autowired
	TokenService tokenService;

	@Autowired
	Environment env;

	/*
	 * 
	 * Returns the Questions according to the User's input whether he has chosen
	 * Internal or SOX audit type
	 *
	 */

	@PostMapping("/getChecklist")
	public ResponseEntity<?> getChecklist(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody AuditType auditType) {
		
		List<QuestionsEntity> questionsList = new ArrayList<>();
		ResponseEntity<?> responseEntity = null;

		log.info(env.getProperty("auditChecklistapp.getChecklist") + token);
		log.info(env.getProperty("auditChecklistapp.getChecklist") + auditType.getAuditType());

		if (tokenService.checkTokenValidity(token)) {
			try {
				questionsList = questionService.getQuestions(auditType.getAuditType());

			} catch (IndexOutOfBoundsException e) {
				log.error(env.getProperty("auditChecklistapp.getChecklist")+env.getProperty("string.null.exception"));
				responseEntity = new ResponseEntity<String>(env.getProperty("string.null.exception"),
						HttpStatus.INTERNAL_SERVER_ERROR);
				return responseEntity;
			}catch (Exception e) {
				log.error(env.getProperty("auditChecklistapp.getChecklist")+env.getProperty("string.null"));
				responseEntity = new ResponseEntity<String>(env.getProperty("string.null"),
						HttpStatus.UNAUTHORIZED);
				return responseEntity;	
			}
			
			responseEntity = new ResponseEntity<List<QuestionsEntity>>(questionsList, HttpStatus.OK);
			log.info(env.getProperty("auditChecklistapp.getChecklist") + responseEntity);
			return responseEntity;

		} else {
			log.error(env.getProperty("auditChecklistapp.getChecklist")+env.getProperty("valcheck.fail"));
			responseEntity = new ResponseEntity<String>(env.getProperty("string.token.exp"), HttpStatus.FORBIDDEN);
			return responseEntity;
		}

	}

	/*
	 * 
	 * This is storing the responses of the Internal/SOX Audit Questions into the
	 * database.
	 * 
	 */
	@PostMapping("/saveResponses")
	public ResponseEntity<?> saveResponses(@RequestHeader(name = "Authorization", required = true) String token,
			@RequestBody List<QuestionsEntity> questionsResponse) {

		List<QuestionsEntity> questionsList = new ArrayList<>();
		ResponseEntity<?> responseEntity = null;

		log.info(env.getProperty("auditChecklistapp.saveResponses")+ questionsResponse);

		if (tokenService.checkTokenValidity(token)) {
			try {
				questionsList = questionService.saveResponses(questionsResponse);
				responseEntity = new ResponseEntity<List<QuestionsEntity>>(questionsList, HttpStatus.OK);
				log.info(env.getProperty("auditChecklistapp.saveResponses")+"Questions Responses saved");
				return responseEntity;
			}catch (Exception e) {
				log.error(env.getProperty("auditChecklistapp.saveResponses")+env.getProperty("string.null"));
				responseEntity = new ResponseEntity<String>(env.getProperty("string.null"),
						HttpStatus.INTERNAL_SERVER_ERROR);
				return responseEntity;
			}
		} else {

			log.error(env.getProperty("auditChecklistapp.saveResponses")+env.getProperty("valcheck.fail"));
			responseEntity = new ResponseEntity<String>(env.getProperty("string.token.exp"), HttpStatus.FORBIDDEN);
			return responseEntity;
		}
	}

}
