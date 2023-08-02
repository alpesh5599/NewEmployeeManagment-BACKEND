package com.nexscend.employee.management.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.nexscend.employee.management.entity.Candidate;
import com.nexscend.employee.management.utils.ResponseBean;

public interface DocumentService {

	ResponseBean saveDocument(MultipartFile file, Candidate request);
	
	Map<String, String> saveDocument(MultipartFile file);
	
	ResponseBean editDocument(MultipartFile file, Integer id);
	
	Object saveFile(MultipartFile file);
	
}