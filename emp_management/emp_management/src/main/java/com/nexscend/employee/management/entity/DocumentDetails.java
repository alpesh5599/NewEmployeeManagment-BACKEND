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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DocumentDetails")
public class DocumentDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String type;

	private Double size;

	private String hash;

	@Lob
	private byte[] fileData;

	@OneToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidateId;
	
//	private String candidateId;
	
	private Integer status;
	
	public static final int RADIX = 16;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public Candidate getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Candidate candidateId) {
		this.candidateId = candidateId;
	}

	public String getHash() {
		return hash;
	}
	
	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public void setHash() throws NoSuchAlgorithmException {
		String trasformedName = new StringBuilder().append(this.name).append(this.type).append(this.size)
				.append(new Date().getTime()).toString();
		
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(trasformedName.getBytes(StandardCharsets.UTF_8));
		
		this.hash = new BigInteger(1, messageDigest.digest()).toString(RADIX);
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DocumentDetails [id=" + id + ", name=" + name + ", type=" + type + ", size=" + size + ", hash=" + hash
				+ ", fileData=" + Arrays.toString(fileData) + ", candidateId=" + candidateId + ", status=" + status
				+ "]";
	}
	
}