package com.employee.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.dao.EmployeeMapper;
import com.employee.model.Employee;
@Service
@Transactional
public class EmployeeServImpl implements EmployeeService {
	private final  Logger logger = LoggerFactory.getLogger(EmployeeServImpl.class);

	@Autowired
	EmployeeMapper emplDao;
	
	@Override
	public int saveEmployee(Employee employee) {
		emplDao.saveEmployee(employee);
		return 0;
	}

	@Override
	public List<Employee> getAllEmployees() {
		logger.info("Get Employees from service");
		List<Employee> lis = emplDao.getAllEmployees();
		for (Employee  employee : lis) {
			logger.info("/n values"+employee.getEmpName());
		}
		return lis;
	}


	@Override
	public Employee getEmployee(int empId) {
		Employee emp = emplDao.getEmployee(empId);
		return emp;
	}
	
	@Override
	public int update(Employee employee) {
		
		emplDao.updateEmployee(employee);
		return 0;
	}


	@Override
	public int deleteEmployee(int empId) {
		emplDao.deleteEmployee(empId);
		return 0;
	}

	

}
