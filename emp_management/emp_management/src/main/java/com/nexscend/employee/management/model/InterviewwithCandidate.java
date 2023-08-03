package com.nexscend.employee.management.model;

import java.time.LocalDateTime;

public interface InterviewwithCandidate {

	public String getPosition();

	public String getFirstName();

	public String getLastName();

	public String getEmail();
	
	public Long getContact();

	public byte[] getFileData();

	public String getName();
	
	public String getType();
	
	public Integer getId();
	
	public String getInterviewerName();
	
	public LocalDateTime getDateTime();
}