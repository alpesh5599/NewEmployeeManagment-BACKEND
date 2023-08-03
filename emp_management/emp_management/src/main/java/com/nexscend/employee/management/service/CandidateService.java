package com.nexscend.employee.management.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nexscend.employee.management.model.CandidateModel;
import com.nexscend.employee.management.model.CandidatewithFileModel;
import com.nexscend.employee.management.utils.ResponseBean;

public interface CandidateService {
	
	ResponseBean saveCandidate(CandidateModel candidate,MultipartFile file);
	
	List<CandidateModel> getAllCandidateDetails();
	
	ResponseBean editCandidateDetails(MultipartFile file, CandidateModel model, Integer id);
	
	List<CandidatewithFileModel> getFile();
	
	ResponseBean getCandidateById(Integer id);
	
	ResponseBean getCandidates();
}