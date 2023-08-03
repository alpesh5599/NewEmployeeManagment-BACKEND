package com.nexscend.employee.management.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nexscend.employee.management.entity.Candidate;
import com.nexscend.employee.management.entity.DocumentDetails;
import com.nexscend.employee.management.entity.SetInterview;

@Component
public class SetInterviewModel {

	private Integer id;
	private String interviewerName;
	private LocalDateTime interviewDateTime;
	private String interviewOutCome;
	private String feedback;

	private Integer candidateId;

	public Integer getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public LocalDateTime getInterviewDateTime() {
		return interviewDateTime;
	}

	public void setInterviewDateTime(LocalDateTime interviewDateTime) {
		this.interviewDateTime = interviewDateTime;
	}

	public String getInterviewOutCome() {
		return interviewOutCome;
	}

	public void setInterviewOutCome(String interviewOutCome) {
		this.interviewOutCome = interviewOutCome;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "SetInterviewModel [id=" + id + ", interviewerName=" + interviewerName + ", interviewDateTime="
				+ interviewDateTime + ", interviewOutCome=" + interviewOutCome + ", feedback=" + feedback + "]";
	}

	public static List<SetInterviewModel> mapEntity(List<SetInterview> listInterview) {
		List<SetInterviewModel> list = new ArrayList<>();

		listInterview.forEach(i -> {
			SetInterviewModel model = new SetInterviewModel();

			model.setId(i.getId());
			model.setInterviewerName(i.getInterviewerName());
			model.setInterviewDateTime(i.getDateTime());
			model.setInterviewOutCome(i.getOutCome());
			model.setFeedback(i.getFeedback());

			list.add(model);

		});

		return list;
	}

}