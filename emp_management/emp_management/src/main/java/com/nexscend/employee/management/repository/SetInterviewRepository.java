package com.nexscend.employee.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nexscend.employee.management.entity.SetInterview;
import com.nexscend.employee.management.model.InterviewwithCandidate;

@Repository
public interface SetInterviewRepository extends JpaRepository<SetInterview, Integer> {

	@Query(nativeQuery = true, value = "SELECT c.position as position, c.first_name as firstName, c.last_name as lastName, "
			+ "c.email as email, c.contact as contact, d.file_data as fileData, d.name as name, d.type as type, i.id as id, i.date_time as dateTime, i.interviewer_name as interviewerName "
			+ "FROM emp_management.candidate c LEFT JOIN emp_management.document_details d ON c.id = d.candidate_id "
			 + "LEFT JOIN set_interview i ON c.id=i.candidate_id where candidate_status in ('PENDING') and DATE(date_time) = CURDATE();")
	public List<InterviewwithCandidate> getAllcandidateInterview();

}