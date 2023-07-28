package com.nexscend.employee.management.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nexscend.employee.management.entity.Candidate;
import com.nexscend.employee.management.model.CandidateModel;
import com.nexscend.employee.management.model.CandidatewithFileModel;
import com.nexscend.employee.management.repository.CandidateRepository;
import com.nexscend.employee.management.utils.CandidateStatus;
import com.nexscend.employee.management.utils.Status;

@Service
public class CandidateServiceImpl implements CandidateService {

	Logger logger = LoggerFactory.getLogger(CandidateServiceImpl.class);

	@Autowired
	CandidateRepository candidateRepository;

	@Autowired
	DocumentService documentServices;

	@Override
	public Map<String, String> saveCandidate(CandidateModel request, MultipartFile file) {

		// For DB Save
		Candidate candidate = new Candidate();

		Map<String, String> response = null;

		if (request == null) {
			logger.error("Requested Model Are Not found...");
		}

		candidate.setPosition(request.getPosition());
		candidate.setFirstName(request.getFirstName());
		candidate.setLastName(request.getLastName());
		candidate.setEmail(request.getEmail());
		candidate.setContect(request.getContact());
		candidate.setSkills(request.getSkills());
		candidate.setJoining(request.getJoining());
		candidate.setCandidateStatus(CandidateStatus.PENDING);

		candidate.setComments(request.getComments());
		candidate.setStatus(Status.ACTIVE.getStatusValue());

		Candidate save = candidateRepository.save(candidate);

		if (file != null) {
			response = documentServices.saveDocument(file, save);
		}

		return response;
	}

	@Override
	public List<CandidateModel> getAllCandidateDetails() {
		List<Candidate> findAll = candidateRepository.findAll();

		return CandidateModel.map(findAll);
	}

	@Override
	public List<CandidatewithFileModel> getFile() {
		List<CandidatewithFileModel> list = candidateRepository.findAllCandidatesWithDocuments();

//		for (CandidatewithFileModel candidatewithFileModel : list) {
//			System.err.println(candidatewithFileModel);
//		}
		return list;
	}

//	public Candidate getCandidateById(Integer id) {
//		return candidateRepository.findById(id).get();
//	}

	@Override
	public Map<String, String> editCandidateDetails(MultipartFile file, CandidateModel candidateModel, Integer id) {
		Candidate candidateEntity = candidateRepository.findById(id).get();

		candidateEntity.setPosition(candidateModel.getPosition());
		candidateEntity.setFirstName(candidateModel.getFirstName());
		candidateEntity.setLastName(candidateModel.getLastName());
		candidateEntity.setEmail(candidateModel.getEmail());
		candidateEntity.setContect(candidateModel.getContact());
		candidateEntity.setSkills(candidateModel.getSkills());
		candidateEntity.setJoining(candidateModel.getJoining());
		candidateEntity.setComments(candidateModel.getComments());
		candidateEntity.setCandidateStatus(candidateModel.getCandidateStatus());
		
		//Update object in DB
		candidateRepository.save(candidateEntity);

		Map<String, String> response = null;
		if (file != null) {
			response = documentServices.editDocument(file, id);
		}

		return response;
	}

}