package com.nexscend.employee.management.utils;

public enum Status {
	
	ACTIVE("Active"),
	INACTIVE("InActive");
	
	private String status; 
	
	Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
