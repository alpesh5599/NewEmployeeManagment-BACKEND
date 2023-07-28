package com.nexscend.employee.management.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.nexscend.employee.management.entity.Employee;

public interface EmployeeDocumentsService {

	Map<String, String> saveDocument(MultipartFile file, Employee request);

	Map<String, String> editDocument(MultipartFile each, Employee save, Integer id);

}