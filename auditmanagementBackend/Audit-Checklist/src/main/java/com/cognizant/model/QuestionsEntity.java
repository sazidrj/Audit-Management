package com.cognizant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * This class is an entity which we will be storing in the database.
 *  
 **/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="AuditQuestions")
public class QuestionsEntity {

	//This is question id
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="questionId")
	private Integer questionId;

	//Type of audit i.e. Internal and SOX
	@Column(name="auditType")
	private String auditType;

	//Questions for audit
	@Column(name="questions")
	private String question;

	//Response for a question
	@Column(name="response")
	private String response;
}
