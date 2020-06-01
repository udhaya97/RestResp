package com.employee.dao;

import java.util.List;

import com.employee.model.Employee;

public interface EmployeeDao {
	public List<Employee> getAllEmployees();
	public int saveEmployee(Employee emp);
	public int deleteEmployee(int id);
	public int updateEmployee(Employee emp);

}
