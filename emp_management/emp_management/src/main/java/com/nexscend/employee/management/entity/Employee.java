package com.nexscend.employee.management.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
@Component
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String employeeName;

	@Column(name = "dob")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private String employeeDOB;

	@Column(name = "email")
	private String employeeEmail;

	@Column(name = "phoneNo")
	private String employeePhoneNo;

	private String joiningStatus;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private String joiningDate;
	private Long aadharCard;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Address address;

	@OneToMany(mappedBy = "empId", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<EmployeeDocuments> employeeDocuments;

	public List<EmployeeDocuments> getEmployeeDocuments() {
		return employeeDocuments;
	}

	public void setEmployeeDocuments(List<EmployeeDocuments> employeeDocuments) {
		this.employeeDocuments = employeeDocuments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeDOB() {
		return employeeDOB;
	}

	public void setEmployeeDOB(String employeeDOB) {
		this.employeeDOB = employeeDOB;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeePhoneNo() {
		return employeePhoneNo;
	}

	public void setEmployeePhoneNo(String employeePhoneNo) {
		this.employeePhoneNo = employeePhoneNo;
	}

	public String getJoiningStatus() {
		return joiningStatus;
	}

	public void setJoiningStatus(String joiningStatus) {
		this.joiningStatus = joiningStatus;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public Long getAadharCard() {
		return aadharCard;
	}

	public void setAadharCard(Long aadharCard) {
		this.aadharCard = aadharCard;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeName=" + employeeName + ", employeeDOB=" + employeeDOB
				+ ", employeeEmail=" + employeeEmail + ", employeePhoneNo=" + employeePhoneNo + ", joiningStatus="
				+ joiningStatus + ", joiningDate=" + joiningDate + ", aadharCard=" + aadharCard + ", address=" + address
				+ ", employeeDocuments=" + employeeDocuments + "]";
	}

	public Employee() {
		super();
	}

}