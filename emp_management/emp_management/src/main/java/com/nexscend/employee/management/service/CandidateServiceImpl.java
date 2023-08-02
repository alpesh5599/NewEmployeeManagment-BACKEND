package com.nexscend.employee.management.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nexscend.employee.management.entity.Candidate;
import com.nexscend.employee.management.model.CandidateModel;
import com.nexscend.employee.management.model.CandidatewithFileModel;
import com.nexscend.employee.management.repository.CandidateRepository;
import com.nexscend.employee.management.utils.CandidateStatus;
import com.nexscend.employee.management.utils.ResponseBean;
import com.nexscend.employee.management.utils.Status;

@Service
public class CandidateServiceImpl implements CandidateService {

	Logger logger = LoggerFactory.getLogger(CandidateServiceImpl.class);
	
	@Autowired
	CandidateRepository candidateRepository;

	@Autowired
	DocumentService documentServices;

	@Override
	public ResponseBean saveCandidate(CandidateModel request, MultipartFile file) {

		// For DB Save
		Candidate candidate = new Candidate();

		if (request == null) {
			logger.error("Requested Model Are Not found...");
			return ResponseBean.generateResponse(HttpStatus.BAD_REQUEST,"Request cannot be empty");
		}

		if (request.getFirstName() != null && !request.getFirstName().trim().isEmpty()) {
			candidate.setFirstName(request.getFirstName());
		} else {
			return ResponseBean.generateResponse(HttpStatus.BAD_REQUEST, "First Name cannot be empty");
		}

		if (request.getLastName() != null && !request.getLastName().trim().isEmpty()) {
			candidate.setLastName(request.getLastName());
		} else {
			return ResponseBean.generateResponse(HttpStatus.BAD_REQUEST,"Last Name cannot be empty");
		}

		if (request.getEmail() != null && !request.getEmail().trim().isEmpty()) {
			candidate.setEmail(request.getEmail());
		} else {
			return ResponseBean.generateResponse(HttpStatus.BAD_REQUEST,"Email cannot be empty");
		}

		if (request.getContact() != null && isValidMobileNumber(request.getContact().toString())) {
			candidate.setContect(request.getContact());
		} else {
			return ResponseBean.generateResponse(HttpStatus.BAD_REQUEST,"Contect cannot be empty");
		}

		
		if (request.getJoining() != null && !request.getJoining().trim().isEmpty()) {
			candidate.setJoining(request.getJoining());
		} else {
			return ResponseBean.generateResponse(HttpStatus.BAD_REQUEST,"Joining Avability cannot be empty");
		}
		
		if (request.getPosition() != null && !request.getPosition().trim().isEmpty()) {
			candidate.setPosition(request.getPosition());
		} else {
			return ResponseBean.generateResponse(HttpStatus.BAD_REQUEST,"Position cannot be empty");
		}
		
		if (request.getSkills() != null && !request.getSkills().trim().isEmpty()) {
			candidate.setSkills(request.getSkills());
		} else {
			return ResponseBean.generateResponse(HttpStatus.BAD_REQUEST,"Skills cannot be empty");
		}
		
		candidate.setCandidateStatus(CandidateStatus.PENDING);

		candidate.setApplicationDate(new Date());
		candidate.setCreatedDate(new Date());
		
		candidate.setComments(request.getComments());
		candidate.setStatus(Status.ACTIVE);

		Candidate save = candidateRepository.save(candidate);

		if (file != null) {
			return documentServices.saveDocument(file, save);
		}
		return ResponseBean.generateResponse(HttpStatus.ACCEPTED,"Thank You For Applying to Nexscend Technologies");
	}

	@Override
	public List<CandidateModel> getAllCandidateDetails() {
		List<Candidate> findAll = candidateRepository.findAll();

		return CandidateModel.map(findAll);
	}

	@Override
	public List<CandidatewithFileModel> getFile() {
		List<CandidatewithFileModel> list = candidateRepository.findAllCandidatesWithDocuments();
		return list;
	}

	@Override
	public ResponseBean editCandidateDetails(MultipartFile file, CandidateModel candidateModel, Integer id) {
		Candidate candidateEntity = candidateRepository.findById(id).get();
		
		if(candidateModel.getPosition() != null) {
			candidateEntity.setPosition(candidateModel.getPosition());
		}
		if(candidateModel.getFirstName() != null) {
			candidateEntity.setFirstName(candidateModel.getFirstName());
		}
		if(candidateModel.getLastName() != null) {
			candidateEntity.setLastName(candidateModel.getLastName());
		}
		if(candidateModel.getEmail() != null) {
			candidateEntity.setEmail(candidateModel.getEmail());
		}
		if(candidateModel.getContact() != null) {
			candidateEntity.setContect(candidateModel.getContact());
		}
		if(candidateModel.getSkills() != null) {
			candidateEntity.setSkills(candidateModel.getSkills());
		}
		if(candidateModel.getJoining() != null) {
			candidateEntity.setJoining(candidateModel.getJoining());
		}
		
		if(candidateModel.getCandidateStatus() != null) {
			candidateEntity.setCandidateStatus(candidateModel.getCandidateStatus());
		}
		
		candidateEntity.setComments(candidateModel.getComments());
		candidateEntity.setModifiedDate(new Date());
		
		// Update object in DB
		candidateRepository.save(candidateEntity);

		ResponseBean response = null;
		if (file != null) {
			response = documentServices.editDocument(file, id);
		}

		return response;
	}
	
	public static boolean isValidMobileNumber(String mobileNumber) {
		
		String patterns 
	    = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" 
	    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$" 
	    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
		
		
        Pattern pattern = Pattern.compile(patterns);
        Matcher matcher = pattern.matcher(mobileNumber);

        return matcher.matches();
    }

	@Override
	public ResponseBean getCandidateById(Integer id) {
		Optional<Candidate> candidate = candidateRepository.findById(id);
		if(!candidate.isPresent()) {
			return ResponseBean.generateResponse(HttpStatus.BAD_REQUEST, "candidate not found");
		}
		
		return ResponseBean.generateResponse(HttpStatus.OK, candidate);
	}

}