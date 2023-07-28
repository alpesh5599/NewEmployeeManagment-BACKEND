package com.nexscend.employee.management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String areaAndStreet;
	private String city;
	private String state;
	private Integer pinCode;

	@OneToOne
	@JoinColumn(name = "emp_id")
	private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAreaAndStreet() {
		return areaAndStreet;
	}

	public void setAreaAndStreet(String areaAndStreet) {
		this.areaAndStreet = areaAndStreet;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public Address(Integer id, String areaAndStreet, String city, String state, Integer pinCode, Employee employee) {
		super();
		this.id = id;
		this.areaAndStreet = areaAndStreet;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.employee = employee;
	}

	public Address() {
		super();
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", areaAndStreet=" + areaAndStreet + ", city=" + city + ", state=" + state
				+ ", pinCode=" + pinCode + ", employee=" + employee + "]";
	}

}