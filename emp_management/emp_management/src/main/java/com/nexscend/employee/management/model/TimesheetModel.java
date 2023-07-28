package com.nexscend.employee.management.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nexscend.employee.management.entity.HoursOfWeek;

@Component
public class TimesheetModel {

	private Integer id;
	private String projectName;
	private String taskName;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	private List<HoursOfWeek> hours;
	private Integer totalHours;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<HoursOfWeek> getHours() {
		return hours;
	}

	public void setHours(List<HoursOfWeek> hours) {
		this.hours = hours;
	}

	@Override
	public String toString() {
		return "TimesheetModel [id=" + id + ", projectName=" + projectName + ", taskName=" + taskName + ", date=" + date
				+ ", hours=" + hours + ", totalHours=" + totalHours + "]";
	}

}