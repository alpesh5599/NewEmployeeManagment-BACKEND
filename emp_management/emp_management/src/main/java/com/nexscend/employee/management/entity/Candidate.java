package com.nexscend.employee.management.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexscend.employee.management.utils.CandidateStatus;
import com.nexscend.employee.management.utils.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "candidate")
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String position;
	private String firstName;
	private String lastName;
	private String email;
	private Long contact;
	private String skills;

	@Enumerated(EnumType.STRING)
	private CandidateStatus candidateStatus;

	// Here is the Avaibility of Joining....
	private String joining;
	private String comments;

	@OneToOne(mappedBy = "candidateId", cascade = CascadeType.ALL)
	@JsonIgnore
	private DocumentDetails document;

//	@OneToOne(mappedBy = "candidateId", cascade = CascadeType.ALL)
//	private SetInterview interview;

	// Status for managing entity in Db.
	@Enumerated(EnumType.STRING)
	private Status status;

	private Date applicationDate;
	private Date createdDate;
	private String createdBy;
	private Date modifiedDate;
	private String modifiedBy;

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public DocumentDetails getDocument() {
		return document;
	}

	public void setDocument(DocumentDetails document) {
		this.document = document;
	}

//	public SetInterview getInterview() {
//		return interview;
//	}
//
//	public void setInterview(SetInterview interview) {
//		this.interview = interview;
//	}

	public CandidateStatus getCandidateStatus() {
		return candidateStatus;
	}

	public void setCandidateStatus(CandidateStatus candidateStatus) {
		this.candidateStatus = candidateStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String emailmail) {
		this.email = emailmail;
	}

	public Long getContect() {
		return contact;
	}

	public void setContect(Long contect) {
		this.contact = contect;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getJoining() {
		return joining;
	}

	public void setJoining(String joining) {
		this.joining = joining;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", position=" + position + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", contact=" + contact + ", skills=" + skills + ", candidateStatus="
				+ candidateStatus + ", joining=" + joining + ", comments=" + comments + ", document=" + document
				+ ", status=" + status + ", applicationDate=" + applicationDate + ", createdDate=" + createdDate
				+ ", createdBy=" + createdBy + ", modifiedDate=" + modifiedDate + ", modifiedBy=" + modifiedBy + "]";
	}
}