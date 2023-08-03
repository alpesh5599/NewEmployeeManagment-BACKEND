package com.nexscend.employee.management.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.nexscend.employee.management.entity.Candidate;
import com.nexscend.employee.management.entity.DocumentDetails;
import com.nexscend.employee.management.utils.ResponseBean;

public interface DocumentService {

	ResponseBean saveDocument(MultipartFile file, Candidate request);
	
	Map<String, String> saveDocument(MultipartFile file);
	
	ResponseBean editDocument(MultipartFile file, Integer id);
	
	Object saveFile(MultipartFile file);

	ResponseEntity<Resource> getFile(String id) throws FileNotFoundException, IOException;

	DocumentDetails findDocumentByCandidateId(Integer id);
	
}