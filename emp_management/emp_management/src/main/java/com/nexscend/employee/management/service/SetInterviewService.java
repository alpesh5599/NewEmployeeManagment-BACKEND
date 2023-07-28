package com.nexscend.employee.management.service;

import java.util.List;
import java.util.Map;

import com.nexscend.employee.management.model.InterviewwithCandidate;
import com.nexscend.employee.management.model.SetInterviewModel;

public interface SetInterviewService {	
	Map<String, String> scheduleInterview(SetInterviewModel interviewModel);
	
	List<SetInterviewModel> getAllInterviewDetails();
	
	List<InterviewwithCandidate> getInterviewwithCandidate();
}