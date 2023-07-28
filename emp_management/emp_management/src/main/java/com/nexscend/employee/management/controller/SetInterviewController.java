package com.nexscend.employee.management.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexscend.employee.management.model.InterviewwithCandidate;
import com.nexscend.employee.management.model.SetInterviewModel;
import com.nexscend.employee.management.service.SetInterviewService;

@RestController
@RequestMapping("api/v1/interview")
@CrossOrigin("*")
public class SetInterviewController {

	@Autowired
	SetInterviewService interviewService;

	@PostMapping("/schedule/interview")
	public ResponseEntity<Object> setInterview(@RequestBody SetInterviewModel interview) {
		Map<String, String> scheduleInterview = interviewService.scheduleInterview(interview);

		return ResponseEntity.status(HttpStatus.CREATED).body(scheduleInterview);
	}

	@GetMapping("/getAll/interviewDetails")
	public List<SetInterviewModel> showScheduledInterview() {
		return interviewService.getAllInterviewDetails();
	}
	
	@GetMapping("/getAll/interviews")
	public List<InterviewwithCandidate> showInterview() {
		return interviewService.getInterviewwithCandidate();
	}

//	@PutMapping("/edit/interview/{id}")
//	public SetInterview updateInterview(@RequestBody SetInterview interview, @PathVariable Integer id) {
//		return interviewService.editInterviewDetails(interview, id);
//	}

}