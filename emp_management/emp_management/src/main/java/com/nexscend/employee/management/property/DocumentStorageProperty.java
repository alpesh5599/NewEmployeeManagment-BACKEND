package com.nexscend.employee.management.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DocumentStorageProperty {
	
	@Value("${document.uploadDirectory}")
	private String uploadDirectory;
	
	@Value("${document.saveDirectory}")
	private String saveDirectory;
	
	public String getSaveDirectory() {
		return saveDirectory;
	}

	public void setSaveDirectory(String saveDirectory) {
		this.saveDirectory = saveDirectory;
	}

	public String getUploadDirectory() {
		return uploadDirectory;
	}

	public void setUploadDirectory(String uploadDirectory) {
		this.uploadDirectory = uploadDirectory;
	}

	@Override
	public String toString() {
		return "DocumentStorageProperty [uploadDirectory=" + uploadDirectory + "]";
	}
}