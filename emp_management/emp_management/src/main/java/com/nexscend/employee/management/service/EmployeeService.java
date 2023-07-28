package com.nexscend.employee.management.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.nexscend.employee.management.entity.Employee;
import com.nexscend.employee.management.model.EmployeeModel;

public interface EmployeeService {

	Map<String, String> saveEmployee(EmployeeModel employee, MultipartFile[] file);
	
	List<EmployeeModel> getAllEmployee();
	
	Optional<Employee> findByAadharNo(Long aadharNo);

	Map<String, String> editEmployee(EmployeeModel data, MultipartFile[] files, Integer id);

}