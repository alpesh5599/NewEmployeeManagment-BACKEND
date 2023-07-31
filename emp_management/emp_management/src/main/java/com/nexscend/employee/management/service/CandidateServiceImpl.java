package com.nexscend.employee.management.service;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Object> saveCandidate(CandidateModel request, MultipartFile file) {

		// For DB Save
		Candidate candidate = new Candidate();

		if (request == null) {
			logger.error("Requested Model Are Not found...");
			return ResponseEntity.badRequest().body("Request cannot be empty");
		}

		if (request.getFirstName() != null && !request.getFirstName().trim().isEmpty()) {
			candidate.setFirstName(request.getFirstName());
		} else {
			return ResponseEntity.badRequest().body("First Name cannot be empty");
		}

		if (request.getLastName() != null && !request.getLastName().trim().isEmpty()) {
			candidate.setLastName(request.getLastName());
		} else {
			return ResponseEntity.badRequest().body("Last Name cannot be empty");
		}

		if (request.getEmail() != null && !request.getEmail().trim().isEmpty()) {
			candidate.setEmail(request.getEmail());
		} else {
			return ResponseEntity.badRequest().body("Email cannot be empty");
		}

		if (request.getContact() != null && isValidMobileNumber(request.getContact().toString())) {
			candidate.setContect(request.getContact());
		} else {
			return ResponseEntity.badRequest().body("Contect cannot be empty");
		}

		
		if (request.getJoining() != null && !request.getJoining().trim().isEmpty()) {
			candidate.setJoining(request.getJoining());
		} else {
			return ResponseEntity.badRequest().body("Joining Avability cannot be empty");
		}
		
		if (request.getPosition() != null && !request.getPosition().trim().isEmpty()) {
			candidate.setPosition(request.getPosition());
		} else {
			return ResponseEntity.badRequest().body("Position cannot be empty");
		}
		
		if (request.getSkills() != null && !request.getSkills().trim().isEmpty()) {
			candidate.setSkills(request.getSkills());
		} else {
			return ResponseEntity.badRequest().body("Skills cannot be empty");
		}
		
		candidate.setCandidateStatus(CandidateStatus.PENDING);

		candidate.setComments(request.getComments());
		candidate.setStatus(Status.ACTIVE);

		Candidate save = candidateRepository.save(candidate);

		if (file != null) {
			return documentServices.saveDocument(file, save);
		}
		return ResponseEntity.accepted().body("Thank You For Applying to Nexscend Technologies");
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

		// Update object in DB
		candidateRepository.save(candidateEntity);

		Map<String, String> response = null;
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

}