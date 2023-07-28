package com.nexscend.employee.management.service;

import java.util.List;
import java.util.Map;

import com.nexscend.employee.management.model.TimesheetModel;

public interface TimesheetDashoboardService {

	Map<String, String> saveData(TimesheetModel model);

}