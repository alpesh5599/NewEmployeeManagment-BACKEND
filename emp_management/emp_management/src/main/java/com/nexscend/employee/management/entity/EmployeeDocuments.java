package com.nexscend.employee.management.entity;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employeeDocuments")
public class EmployeeDocuments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String type;
	private Double size;
	private String hash;
//	private Documents documents;

	@Lob
	private byte[] fileData;

	@ManyToOne
	@JoinColumn(name = "emp_id")
	private Employee empId;

	public static final int RADIX = 16;

	public EmployeeDocuments() {
		super();
	}

	public Employee getEmpId() {
		return empId;
	}

	public void setEmpId(Employee empId) {
		this.empId = empId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public Documents getDocuments() {
//		return documents;
//	}
//
//	public void setDocuments(Documents documents) {
//		this.documents = documents;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public static int getRadix() {
		return RADIX;
	}

	public void setHash() throws NoSuchAlgorithmException {
		String trasformedName = new StringBuilder().append(this.name).append(this.type).append(this.size)
				.append(new Date().getTime()).toString();

		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(trasformedName.getBytes(StandardCharsets.UTF_8));

		this.hash = new BigInteger(1, messageDigest.digest()).toString(RADIX);
	}

	@Override
	public String toString() {
		return "EmployeeDocuments [id=" + id + ", name=" + name + ", type=" + type + ", size=" + size + ", hash=" + hash
				+ ", fileData=" + Arrays.toString(fileData) + "]";
	}

}