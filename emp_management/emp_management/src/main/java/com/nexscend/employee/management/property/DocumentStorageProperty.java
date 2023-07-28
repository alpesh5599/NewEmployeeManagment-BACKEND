package com.nexscend.employee.management.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DocumentStorageProperty {
	
	@Value("${document.uploadDirectory}")
	private String uploadDirectory;

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