package com.cognizant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * This POJO class is used to handle audit type and acceptable no of NO allowed in particular benchmark
 */

@Getter
@Setter
@AllArgsConstructor
public class AuditBenchmarkEntity {

	private String auditType;
	private Integer accNoAnswers;
	
}
