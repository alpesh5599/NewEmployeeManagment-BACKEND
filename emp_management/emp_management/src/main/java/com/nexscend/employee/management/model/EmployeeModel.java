package com.nexscend.employee.management.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nexscend.employee.management.entity.Address;
import com.nexscend.employee.management.entity.Employee;
import com.nexscend.employee.management.entity.EmployeeDocuments;

@Component
public class EmployeeModel {

	private Integer id;
	private String name;
	private String DOB;
	private String email;
	private String phoneNo;
	private String joiningStatus;
	private String joiningDate;
	private Long aadharCard;

	private Address address;

	private List<EmployeeDocuments> empdocs;

	public EmployeeModel(Integer id, String name, String dOB, String email, String phoneNo, String joiningStatus,
			String joiningDate, Long aadharCard, Address address, List<EmployeeDocuments> empdocs) {
		super();
		this.id = id;
		this.name = name;
		DOB = dOB;
		this.email = email;
		this.phoneNo = phoneNo;
		this.joiningStatus = joiningStatus;
		this.joiningDate = joiningDate;
		this.aadharCard = aadharCard;
		this.address = address;
		this.empdocs = empdocs;
	}

	public EmployeeModel(Employee e) {
		this.name = e.getEmployeeName();
		this.DOB = e.getEmployeeDOB();
		this.email = e.getEmployeeEmail();
		this.phoneNo = e.getEmployeePhoneNo();
		this.joiningStatus = e.getEmployeePhoneNo();
		this.joiningDate = e.getJoiningDate();
		this.aadharCard = e.getAadharCard();
		this.address = e.getAddress();
		this.empdocs = e.getEmployeeDocuments();

	}

	public EmployeeModel() {
		super();
	}

	public static List<EmployeeModel> data(List<Employee> employeeList) {
		List<EmployeeModel> listModel = new ArrayList<>();
		employeeList.forEach(res -> {
			EmployeeModel e1 = new EmployeeModel();

			e1.setId(res.getId());
			e1.setName(res.getEmployeeName());
			e1.setEmail(res.getEmployeeEmail());
			e1.setDOB(res.getEmployeeDOB());
			e1.setPhoneNo(res.getEmployeePhoneNo());
			e1.setAddress(res.getAddress());
			e1.setAadharCard(res.getAadharCard());
			e1.setJoiningStatus(res.getJoiningStatus());
			e1.setJoiningDate(res.getJoiningDate());
			e1.setEmpdocs(res.getEmployeeDocuments());

			listModel.add(e1);
		});
		return listModel;
	}

	public List<EmployeeDocuments> getEmpdocs() {
		return empdocs;
	}

	public void setEmpdocs(List<EmployeeDocuments> empdocs) {
		this.empdocs = empdocs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
		return "EmployeeModel [id=" + id + ", name=" + name + ", DOB=" + DOB + ", email=" + email + ", phoneNo="
				+ phoneNo + ", joiningStatus=" + joiningStatus + ", joiningDate=" + joiningDate + ", aadharCard="
				+ aadharCard + ", address=" + address + "]";
	}

}