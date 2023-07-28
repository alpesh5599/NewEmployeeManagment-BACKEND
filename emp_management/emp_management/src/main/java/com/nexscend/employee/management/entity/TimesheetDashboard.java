package com.nexscend.employee.management.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "timesheet_dashboard")
public class TimesheetDashboard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String projectName;
	private String taskName;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	
	private Integer totalHours;

	@OneToMany(mappedBy = "sheetId", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<HoursOfWeek> hours;
	
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

	public Integer getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}

	@Override
	public String toString() {
		return "TimesheetDashboard [id=" + id + ", projectName=" + projectName + ", taskName=" + taskName + ", hours="
				+ hours + ", totalHours=" + totalHours + "]";
	}

	public TimesheetDashboard() {
		super();
	}

}