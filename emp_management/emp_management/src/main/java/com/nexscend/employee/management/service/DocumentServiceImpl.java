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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import com.nexscend.employee.management.entity.Candidate;
import com.nexscend.employee.management.entity.DocumentDetails;
import com.nexscend.employee.management.property.DocumentStorageProperty;
import com.nexscend.employee.management.repository.DocumentRepository;
import com.nexscend.employee.management.utils.Status;

@Service
public class DocumentServiceImpl implements DocumentService {
	
	Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

	private final Path fileLocation;
	
	@Autowired
	DocumentRepository repository;
	
	@Autowired
	public DocumentServiceImpl(DocumentStorageProperty documentStorageProperty) {
		this.fileLocation = Paths.get(documentStorageProperty.getUploadDirectory()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileLocation);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public Map<String, String> saveDocument(MultipartFile file, Candidate id) {
		DocumentDetails entity = new DocumentDetails();

		if (file == null) {
			logger.error("File not Found...");
		}

		entity.setCandidateId(id);
		entity.setName(file.getOriginalFilename());
		try {
			entity.setFileData(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		entity.setType(file.getContentType());
		entity.setSize((double)(DataSize.ofBytes(file.getSize()).toMegabytes()));
		entity.setStatus(Status.ACTIVE);
		
		try {
			entity.setHash();
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		}
		
		//StoreDocument
		try {
			storeDocument(file, entity.getHash());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		// Save in DB
		Map<String, String> response = new HashMap<>();
		DocumentDetails save = repository.save(entity);
		if(save.getId() != null) {
			response.put("response", "Thank You For Applying to Nexscend Technologies");
		}else {
			response.put("response", "Please select the valid file type");
		}
	    
		return response;
	}
	
	@Override
	public Map<String, String> editDocument(MultipartFile file, Integer id){
		DocumentDetails entity = repository.findById(id).get();

		if (file == null) {
			logger.error("File not Found...");
		}

//		entity.setCandidateId(id);
		entity.setName(file.getOriginalFilename());
		try {
			entity.setFileData(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		entity.setType(file.getContentType());
		entity.setSize((double)(DataSize.ofBytes(file.getSize()).toMegabytes()));
		entity.setStatus(Status.ACTIVE);
		
		try {
			entity.setHash();
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		}
		
		//StoreDocument
		try {
			storeDocument(file, entity.getHash());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		// Update in DB
		Map<String, String> response = new HashMap<>();
		DocumentDetails save = repository.save(entity);
		if(save.getId() != null) {
			response.put("response", "Candidate details are updated...");
		}else {
			response.put("response", "Please select the valid file type");
		}
	    
		return response;
	}
	
	@Override
	public Map<String, String> saveDocument(MultipartFile file) {
		DocumentDetails entity = new DocumentDetails();

		if (file == null) {
			logger.error("File not Found...");
		}

		entity.setName(file.getOriginalFilename());
		try {
			entity.setFileData(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		entity.setType(file.getContentType());
		entity.setSize((double)(DataSize.ofBytes(file.getSize()).toMegabytes()));
		entity.setStatus(Status.ACTIVE);
		
		try {
			entity.setHash();
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
		}
		
		//StoreDocument
		try {
			storeDocument(file, entity.getHash());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		// Save in DB
		Map<String, String> response = new HashMap<>();
		DocumentDetails save = repository.save(entity);
		if(save.getId() != null) {
			response.put("response", "Thank You For Applying to Nexscend Technologies");
		}else {
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
	public Object saveFile(MultipartFile uplodedFile) {

		DocumentDetails file = new DocumentDetails();

		file.setName(uplodedFile.getOriginalFilename());
		
		try {
			file.setFileData(uplodedFile.getBytes());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		file.setType(uplodedFile.getContentType());

		// Save in DB
		Map<String, String> response = new HashMap<>();
		DocumentDetails save = repository.save(file);
		
		if(save.getId() != null) {
			response.put("response", "File Uploded Succesfully");
		}else {
			response.put("response", "Data base  connections issue found");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	    
		return response;
	}


}
