package com.nexscend.employee.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexscend.employee.management.model.CandidateModel;
import com.nexscend.employee.management.model.CandidatewithFileModel;
import com.nexscend.employee.management.service.CandidateService;
import com.nexscend.employee.management.utils.ResponseBean;

@RestController
@RequestMapping("candidate")
@CrossOrigin(origins = "http://localhost:4200")
public class CandidateController {

	Logger logger = LoggerFactory.getLogger(CandidateController.class);

	@Autowired
	CandidateService candidateService;

	@Autowired
	ObjectMapper objectMapper;

	@PostMapping(value = "add")
	public ResponseBean saveCandidateData(@RequestParam("candidate") String candidate,
			@RequestParam("file") MultipartFile file) throws MissingServletRequestPartException {
		// Calling the service
		CandidateModel readValue = null;
		try {
			readValue = objectMapper.readValue(candidate, CandidateModel.class);
			if (readValue.getId() != null) {
				return candidateService.editCandidateDetails(file, readValue, readValue.getId());
			} else {
				return candidateService.saveCandidate(readValue, file);
			}
		} catch (JsonProcessingException e) {
			return ResponseBean.generateResponse(HttpStatus.BAD_REQUEST, "Json Parse error", readValue);
		}

	}

	@GetMapping("getAll")
	public List<CandidatewithFileModel> getAllCandidateData() {
		logger.info("Fetching All Candidate Details...");
		return candidateService.getFile();
	}
	
	@GetMapping("getAll1")
	public ResponseBean getAllCandidates() {
		logger.info("Fetching All Candidate Details...");
		return candidateService.getCandidates();
	}

	@GetMapping("/getFile")
	public List<CandidatewithFileModel> getFiles() {
		return candidateService.getFile();
	}

	@GetMapping("/getById/{id}")
	public ResponseBean getCandidateById(@PathVariable("id") Integer id) {
		logger.info("Candidate by Id.");
		return candidateService.getCandidateById(id);
	}
}