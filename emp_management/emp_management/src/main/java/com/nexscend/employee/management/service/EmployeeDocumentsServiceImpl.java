package com.nexscend.employee.management.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import com.nexscend.employee.management.entity.Employee;
import com.nexscend.employee.management.entity.EmployeeDocuments;
import com.nexscend.employee.management.repository.EmployeeDocumentsRepository;
import com.nexscend.employee.management.repository.EmployeeRepository;

@Service
public class EmployeeDocumentsServiceImpl implements EmployeeDocumentsService {

	Logger logger = LoggerFactory.getLogger(EmployeeDocumentsServiceImpl.class);

	private final Path fileLocation;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeDocumentsRepository documentsRepository;

	@Autowired
	public EmployeeDocumentsServiceImpl() {
		this.fileLocation = Paths.get("C:\\EmployeeDocuments").toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileLocation);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public Map<String, String> saveDocument(MultipartFile file, Employee id) {

		EmployeeDocuments entity = new EmployeeDocuments();

		if (file == null) {
			logger.error("File not Found...");
		}

		entity.setEmpId(id);
		entity.setName(file.getOriginalFilename());
		try {
			entity.setFileData(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		entity.setType(file.getContentType());
		entity.setSize((double) (DataSize.ofBytes(file.getSize()).toMegabytes()));

		try {
			entity.setHash();
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		}

		// StoreDocument
		try {
			storeDocument(file, entity.getHash());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		// Save in DB
		Map<String, String> response = new HashMap<>();
		EmployeeDocuments save = documentsRepository.save(entity);

		if (save.getId() != null) {
			response.put("response", "Employee is Registered...");
		} else {
			response.put("response", "Please select the valid file type");
		}

		return response;
	}

	private void storeDocument(MultipartFile file, String hash) throws IOException {

		if (this.fileLocation != null) {
			logger.info("File save at Location " + this.fileLocation);

			Path targetLocation = this.fileLocation.resolve(hash);
			Files.copy(file.getInputStream(), targetLocation);
		} else {
			logger.info("File is not saved in local system, because not provided the path in property file");
		}
	}

	@Override
	public Map<String, String> editDocument(MultipartFile file, Employee emp, Integer id) {
		EmployeeDocuments entity = documentsRepository.findById(id).get();

		if (file == null) {
			logger.error("File not Found...");
		}

		entity.setEmpId(emp);
		entity.setName(file.getOriginalFilename());
		try {
			entity.setFileData(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		entity.setType(file.getContentType());
		entity.setSize((double) (DataSize.ofBytes(file.getSize()).toMegabytes()));

		try {
			entity.setHash();
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		}

		// StoreDocument
		try {
			storeDocument(file, entity.getHash());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		// Save in DB
		Map<String, String> response = new HashMap<>();
		EmployeeDocuments save = documentsRepository.save(entity);

		if (save.getId() != null) {
			response.put("response", "Employee is Updated...");
		} else {
			response.put("response", "Please select the valid file type");
		}

		return response;
	}

}