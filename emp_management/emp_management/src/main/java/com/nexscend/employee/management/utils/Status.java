package com.nexscend.employee.management.utils;

public enum Status {
	
	ACTIVE(1,"Active"),
	INACTIVE(-1,"In Active");
	
	private Integer statusValue;
	private String status; 
	
	Status(Integer i, String status) {
		this.statusValue = i;
		this.status = status;
	}

	public Integer getStatusValue() {
		return statusValue;
	}


	public void setStatusValue(Integer statusValue) {
		this.statusValue = statusValue;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
