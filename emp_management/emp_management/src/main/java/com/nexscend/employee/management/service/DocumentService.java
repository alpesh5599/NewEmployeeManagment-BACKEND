package com.nexscend.employee.management.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.nexscend.employee.management.entity.Candidate;

public interface DocumentService {

	Map<String, String> saveDocument(MultipartFile file, Candidate request);
	
	Map<String, String> saveDocument(MultipartFile file);
	
	Map<String, String> editDocument(MultipartFile file, Integer id);
	
	Object saveFile(MultipartFile file);
	
}