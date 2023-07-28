package com.nexscend.employee.management.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nexscend.employee.management.entity.Address;
import com.nexscend.employee.management.entity.Employee;
import com.nexscend.employee.management.model.EmployeeModel;
import com.nexscend.employee.management.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository repository) {
		this.employeeRepository = repository;
	}

	@Autowired
	EmployeeDocumentsService documentsService;

	@Override
	public Map<String, String> saveEmployee(EmployeeModel employee, MultipartFile[] files) {
		Employee e = new Employee();

		e.setEmployeeName(employee.getName());
		e.setEmployeeEmail(employee.getEmail());
		e.setEmployeeDOB(employee.getDOB());
		e.setEmployeePhoneNo(employee.getPhoneNo());
		e.setJoiningStatus(employee.getJoiningStatus());
		e.setJoiningDate(employee.getJoiningDate());
		e.setAadharCard(employee.getAadharCard());
		e.setAddress(employee.getAddress());
		
//		Address ad=new Address();
//		ad.setEmployee(e);

		Map<String, String> response = new HashMap<>();

		if (employee.getAadharCard() != null) {
			Optional<Employee> cardNo = employeeRepository.findByAadharCard(employee.getAadharCard());
			if (!cardNo.isPresent()) {
				Employee save = employeeRepository.save(e);

				Arrays.asList(files).stream().forEach(each -> {
					if (files != null) {
						documentsService.saveDocument(each, save);
					}
				});

			} else {
				response.put("response", "Aadhar Card is Already Registered...");
			}
		} else {
			response.put("response", "Aadhar is null");
		}

		EmployeeModel em = new EmployeeModel(e);
		em.setId(e.getId());
		return response;
	}

	@Override
	public List<EmployeeModel> getAllEmployee() {
		List<Employee> list = employeeRepository.findAll();

		return EmployeeModel.data(list);
	}

	@Override
	public Optional<Employee> findByAadharNo(Long aadharNo) {
		Optional<Employee> cardNo = employeeRepository.findByAadharCard(aadharNo);
		if (cardNo.isPresent()) {
			return cardNo;
		}
		return null;
	}

	@Override
	public Map<String, String> editEmployee(EmployeeModel data, MultipartFile[] files,Integer id) {

		Employee e = employeeRepository.findById(id).get();

		e.setEmployeeName(data.getName());
		e.setEmployeeEmail(data.getEmail());
		e.setEmployeeDOB(data.getDOB());
		e.setEmployeePhoneNo(data.getPhoneNo());
		e.setJoiningStatus(data.getJoiningStatus());
		e.setJoiningDate(data.getJoiningDate());
		e.setAadharCard(data.getAadharCard());
		e.setAddress(data.getAddress());

		Map<String, String> response = new HashMap<>();

		if (data.getAadharCard() != null) {
//			Optional<Employee> cardNo = employeeRepository.findByAadharCard(data.getAadharCard());
			Employee save = employeeRepository.save(e);

			Arrays.asList(files).stream().forEach(each -> {
				if (files != null) {
					documentsService.editDocument(each, save, id);
				}
			});
		} else {
			response.put("response", "Aadhar is null");
		}

		EmployeeModel em = new EmployeeModel(e);
		em.setId(e.getId());
		return response;
	}

}