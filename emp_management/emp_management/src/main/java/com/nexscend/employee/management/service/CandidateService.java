package com.nexscend.employee.management.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.nexscend.employee.management.model.CandidateModel;
import com.nexscend.employee.management.model.CandidatewithFileModel;

public interface CandidateService {
	
	ResponseEntity<Object> saveCandidate(CandidateModel candidate,MultipartFile file);
	
	List<CandidateModel> getAllCandidateDetails();
	
	Map<String, String> editCandidateDetails(MultipartFile file, CandidateModel model, Integer id);
	
	List<CandidatewithFileModel> getFile();
}