package com.nexscend.employee.management.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexscend.employee.management.model.CandidateModel;
import com.nexscend.employee.management.model.CandidatewithFileModel;
import com.nexscend.employee.management.service.CandidateService;

@RestController
@RequestMapping("api/v1/candidate")
@CrossOrigin(origins = "http://localhost:4200")
public class CandidateController {

	Logger logger = LoggerFactory.getLogger(CandidateController.class);

	@Autowired
	CandidateService candidateService;

	@Autowired
	ObjectMapper mapper;

	@PostMapping(value = "/save/candidate")
	public ResponseEntity<Object> saveCandidateData(@RequestParam("candidate") String candidate,
			@RequestParam("file") MultipartFile file) {
		// Calling the service
		CandidateModel readValue = null;
		Map<String, String> saveCandidate = null;
		try {
			readValue = mapper.readValue(candidate, CandidateModel.class);
			saveCandidate = candidateService.saveCandidate(readValue, file);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(saveCandidate);
	}

	@GetMapping("/getAll/candidate")
	public List<CandidatewithFileModel> getAllCandidateData() {
		logger.info("Fetching All Candidate Details...");
		return candidateService.getFile();
	}
	
	@GetMapping("/getFile")
	public List<CandidatewithFileModel> getFiles(){
		
		return candidateService.getFile();
	}

	
	@PutMapping("/editCandidate/{id}")
	public ResponseEntity<Object> updateCandidateDetails(@RequestParam("file") MultipartFile file,@RequestParam("candidate") String candidate, @PathVariable("id") Integer id) {
		logger.info("updating details of candidate...");
		
		CandidateModel readValue = null;
		Map<String, String> editCandidate = null;
		try {
			readValue = mapper.readValue(candidate, CandidateModel.class);
			editCandidate = candidateService.editCandidateDetails(file, readValue, id);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.OK).body(editCandidate);
	}

	
}