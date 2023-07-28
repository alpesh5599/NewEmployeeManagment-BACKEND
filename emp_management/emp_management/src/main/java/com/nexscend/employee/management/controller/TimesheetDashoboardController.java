package com.nexscend.employee.management.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexscend.employee.management.model.TimesheetModel;
import com.nexscend.employee.management.service.TimesheetDashoboardService;

@RestController
@RequestMapping("api/v1/timesheet")
@CrossOrigin("*")
public class TimesheetDashoboardController {

	@Autowired
	public TimesheetDashoboardService timesheetService;

	@Autowired
	ObjectMapper mapper;

	@PostMapping("/save")
	public ResponseEntity<Object> saveData(@RequestBody List<TimesheetModel> model) {
		TimesheetModel readValue = null;
		

//		Arrays.asList(model).stream().forEach(each -> {
//			readValue = mapper.readValue(model, TimesheetModel.class);
////				timesheetService.saveData(readValue);
//		});
		

		Map<String, String> response = timesheetService.saveData(readValue);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}