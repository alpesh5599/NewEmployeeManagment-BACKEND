package com.nexscend.employee.management.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.nexscend.employee.management.entity.Candidate;
import com.nexscend.employee.management.utils.CandidateStatus;

@Component
@Configuration
public class CandidateModel {

	private Integer id;
	private String position;
	private String firstName;
	private String lastName;
	private String email;
	private Long contact;
	private String skills;
	private String joining;
	private String comments;
	private CandidateStatus candidateStatus;

	private String fileName;

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

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

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
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

	@Override
	public String toString() {
		return "CandidateModel [id=" + id + ", position=" + position + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", contact=" + contact + ", skills=" + skills + ", joining=" + joining
				+ ", comments=" + comments + ", fileName=" + fileName + "]";
	}

	public static List<CandidateModel> map(List<Candidate> candidateList) {

		List<CandidateModel> response = new ArrayList<CandidateModel>();
		
		candidateList.forEach(ref -> {
			CandidateModel c_Model = new CandidateModel();

			c_Model.setId(ref.getId());
			c_Model.setPosition(ref.getPosition());
			c_Model.setFirstName(ref.getFirstName());
			c_Model.setLastName(ref.getLastName());
			c_Model.setEmail(ref.getEmail());
			c_Model.setContact(ref.getContect());
			c_Model.setSkills(ref.getSkills());
			c_Model.setJoining(ref.getJoining());
			c_Model.setCandidateStatus(ref.getCandidateStatus());

			response.add(c_Model);
		});
		return response;
	}

}
