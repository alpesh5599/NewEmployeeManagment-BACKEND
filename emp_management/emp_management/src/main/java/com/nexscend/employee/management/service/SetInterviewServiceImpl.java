package com.nexscend.employee.management.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexscend.employee.management.entity.SetInterview;
import com.nexscend.employee.management.model.InterviewwithCandidate;
import com.nexscend.employee.management.model.SetInterviewModel;
import com.nexscend.employee.management.repository.SetInterviewRepository;

@Service
public class SetInterviewServiceImpl implements SetInterviewService {

	@Autowired
	SetInterviewRepository interviewRepository;
	
	@Override
	public Map<String, String> scheduleInterview(SetInterviewModel interviewModel) {
		
		SetInterview entity = new SetInterview();
		
		entity.setInterviewerName(interviewModel.getInterviewerName());
		entity.setDateTime(interviewModel.getInterviewDateTime());
		entity.setOutCome(interviewModel.getInterviewOutCome());
		entity.setFeedback(interviewModel.getFeedback());
		entity.setCandidateId(interviewModel.getCandidateId());
		
		SetInterview save = interviewRepository.save(entity);
		
		Map<String, String> response = new HashMap<>();

		if(save.getId() != null) {
			response.put("response", "Interview is Scheduled...!!");
		}else {
			response.put("response", "Something went Wrong, Please Check Details..");
		}
		
		return response;
	}

	@Override
	public List<SetInterviewModel> getAllInterviewDetails() {
		List<SetInterview> findAll = interviewRepository.findAll();
		
		return SetInterviewModel.mapEntity(findAll);
	}

	@Override
	public List<InterviewwithCandidate> getInterviewwithCandidate() {
		List<InterviewwithCandidate> all =interviewRepository.getAllcandidateInterview();
		
		return all;
	}
	
	
	
		
//	public SetInterview editInterviewDetails(SetInterview interview, Integer id) {
//		SetInterview save=interviewRepository.findById(id).get();
//		
//		save.setName(interview.getName());
//		save.setDateTime(interview.getDateTime());
//		save.setOutCome(interview.getOutCome());
//		save.setFeedback(interview.getFeedback());
//		
//		return interviewRepository.save(save);
//	}
//	
//	public void deleteInterview(Integer id) {
//		interviewRepository.deleteById(id);
//	}

}