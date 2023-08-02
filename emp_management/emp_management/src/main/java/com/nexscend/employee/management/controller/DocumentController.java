package com.nexscend.employee.management.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nexscend.employee.management.entity.Candidate;
import com.nexscend.employee.management.service.DocumentService;
import com.nexscend.employee.management.utils.ResponseBean;

@RestController
@RequestMapping("api/document")
@CrossOrigin("*")
public class DocumentController {

	private static final long MAX_TOTAL_FILES_SIZE = DataSize.ofMegabytes(10).toBytes(); // Maximum total size allowed
																							// (in bytes)
	private static final long MAX_TOTAL_FILE_SIZE = DataSize.ofMegabytes(2).toBytes(); // Maximum total size allowed (in
																						// bytes)

	@Autowired
	DocumentService documentService;

	@PostMapping("file/upload")
	public ResponseBean upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Candidate id) {

		if (file.isEmpty()) {
			return ResponseBean.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "File is not found");
		}

		ResponseBean saveResume = documentService.saveDocument(file, id);

		return ResponseBean.generateResponse(HttpStatus.CREATED, saveResume);
	}

	@PostMapping("addFiles")
	public ResponseEntity<Object> upload(@RequestParam("files") List<MultipartFile> files) {

		long totalSize = 0;

		// Saved File Names saved in this list
		List<String> filesNames = new ArrayList<>();

		// The files which size is exceed the file size limit.
		List<String> exceedFilesNames = new ArrayList<>();

		if (files.isEmpty() || files == null || (files.size() == 1 && files.get(0).getSize() == 0)) {
			Map<String, String> responseMap = new HashMap<String, String>();
			responseMap.put("response", "No file hasbeen chosen or chosen file has no content");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
		}

		for (MultipartFile multipartFile : files) {
			if (multipartFile.getSize() <= MAX_TOTAL_FILE_SIZE) {
				totalSize += multipartFile.getSize();
				documentService.saveDocument(multipartFile);
				filesNames.add(multipartFile.getOriginalFilename());
			} else {
//				totalSize += multipartFile.getSize();
				exceedFilesNames.add(multipartFile.getOriginalFilename());
			}
		}

		if (totalSize > MAX_TOTAL_FILES_SIZE) {
			// Total size exceeds the maximum limit
			String message = "Total file size exceeds the allowed limit of "
					+ DataSize.ofBytes(MAX_TOTAL_FILES_SIZE).toMegabytes() + " MB.";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		} else if (!exceedFilesNames.isEmpty()) {
			// Individual file size exceeds the maximum limit
			String message = "The following files exceed the maximum size limit: "
					+ String.join(", ", exceedFilesNames);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		}

//		files.stream().forEach(file -> {
//			documentService.saveDocument(file);
//			fileNames.add(file.getOriginalFilename());
//		});

		return ResponseEntity.status(HttpStatus.CREATED).body("Uploaded the files successfully:" + filesNames);

	}

	@PostMapping("addFile")
	public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) {

		if (file.isEmpty()) {
			Map<String, String> responseMap = new HashMap<String, String>();
			responseMap.put("response", "No file hasbeen chosen or chosen file has no content");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
		}

		Object saveResume = documentService.saveDocument(file);

		return ResponseEntity.status(HttpStatus.CREATED).body(saveResume);

	}

//	@PostMapping("addMultipleFile")
//	public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file){
//		
//		if (file.isEmpty()) {
//			Map<String, String> responseMap= new HashMap<String,String>();
//			responseMap.put("response", "No file hasbeen chosen or chosen file has no content");
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMap);
//		}
//		
//		Object saveResume = fileService.saveFile(file);
//		
//		return ResponseEntity.status(HttpStatus.CREATED).body(saveResume);
//		
//	}
}