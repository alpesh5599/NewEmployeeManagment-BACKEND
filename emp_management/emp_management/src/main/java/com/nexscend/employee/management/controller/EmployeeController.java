package com.nexscend.employee.management.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexscend.employee.management.entity.Employee;
import com.nexscend.employee.management.model.EmployeeModel;
import com.nexscend.employee.management.service.EmployeeService;

@RestController
@RequestMapping("api/v1/employee")
@CrossOrigin("*")
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	ObjectMapper mapper;
	
	
	Map<String, String> map;
	
	@PostMapping("save/employee")
	public ResponseEntity<Object> saveEmployee(@RequestParam("files") MultipartFile[] files,  @RequestParam("employee") String model){
		logger.info("Saving Details of Employee..");

//		EmployeeModel data = new EmployeeModel();
//		Map<String, String> employee = null;

		try {
			EmployeeModel data = mapper.readValue(model, EmployeeModel.class);
			map = employeeService.saveEmployee(data, files);
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(map);
	}
	
	@GetMapping("getAll/employee")
	public List<EmployeeModel> getAllEmployee(){
		logger.info("Fetching all Details of Employee..");

		return employeeService.getAllEmployee();
	}
	
	@GetMapping("getEmployee/{aadharCardNo}")
	public Optional<Employee> findByAadhar(@PathVariable("aadharCardNo") Long aadharNo){
		return employeeService.findByAadharNo(aadharNo);
	}
	
	
	@PutMapping("editEmployee/{id}")
	public ResponseEntity<Object> editEmployee(@RequestParam("files") MultipartFile[] files,  @RequestParam("employee") String model, @PathVariable("id") Integer id){
		logger.info("Updating Details of Employee..");
		
		try {
			EmployeeModel data = mapper.readValue(model, EmployeeModel.class);
			map = employeeService.editEmployee(data, files, id);

		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(map);
	}
	
	
}