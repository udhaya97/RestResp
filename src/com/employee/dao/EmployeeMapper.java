package com.employee.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.employee.EmployeeUtil;
import com.employee.model.Employee;

@Repository

public class EmployeeMapper {
	private final  Logger logger = LoggerFactory.getLogger(EmployeeMapper.class);
	
	EmployeeUtil empUtil = new EmployeeUtil();

	public List<Employee> getAllEmployees()
	{
		SqlSession sqlSession = empUtil.getSqlSessionFactory().openSession();
		
		List<Employee> lis = new ArrayList<>();
		lis= sqlSession.selectList("getEmployeeDetail");
		logger.info("\n getting values for employee mapper");
		sqlSession.commit();
		sqlSession.close();
		return lis;
	}
	
	public int saveEmployee(Employee  employe)
	{
		SqlSession session = empUtil.getSqlSessionFactory().openSession();
		
		session.insert("insetValues", employe);
		
		session.commit();
		session.close();
		
		return 0;
	}
	public int deleteEmployee(int empId)
	{
		SqlSession session = empUtil.getSqlSessionFactory().openSession();
		
		session.delete("deleteEmployee",empId);	
		session.commit();
		session.close();
		
		return 0;
	}
	
	public Employee getEmployee(int empId)
	{
		SqlSession session = empUtil.getSqlSessionFactory().openSession();
		
		Employee sample =(Employee) session.selectOne("getEmployee",empId);	
		
		session.commit();
		session.close();
		
		return sample;
	}
	public int updateEmployee(Employee employee)
	{
		SqlSession session = empUtil.getSqlSessionFactory().openSession();
		
	 session.update("updateEmployee", employee);
		session.commit();
		session.close();
		
		return 0;
	}
}
