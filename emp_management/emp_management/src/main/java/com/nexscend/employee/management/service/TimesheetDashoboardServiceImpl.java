package com.nexscend.employee.management.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nexscend.employee.management.entity.TimesheetDashboard;
import com.nexscend.employee.management.model.TimesheetModel;
import com.nexscend.employee.management.repository.TimesheetDashboardRepository;

@Service
public class TimesheetDashoboardServiceImpl implements TimesheetDashoboardService {

	private TimesheetDashboardRepository timesheetRepository;

	public TimesheetDashoboardServiceImpl(TimesheetDashboardRepository repository) {
		this.timesheetRepository = repository;
	}

	@Override
	public Map<String, String> saveData(TimesheetModel model) {
		TimesheetDashboard entity = new TimesheetDashboard();

		entity.setProjectName(model.getProjectName());
		entity.setTaskName(model.getTaskName());
		entity.setHours(model.getHours());
		entity.setDate(model.getDate());
		entity.setTotalHours(model.getTotalHours());

		TimesheetDashboard save = timesheetRepository.save(entity);

		Map<String, String> map = new HashMap<>();
		if (save.getId() != null) {
			map.put("response", "Timesheet data is up to date..");
		} else {
			map.put("response", "something went wrong...");
		}
		return map;
	}

}