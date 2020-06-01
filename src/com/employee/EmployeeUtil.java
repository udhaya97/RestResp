package com.employee;
import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EmployeeUtil {
	
	private  SqlSessionFactory sessionFactory;
	private final  Logger logger = LoggerFactory.getLogger(EmployeeUtil.class);
	
	
	public void resourc() {
		logger.info("Entered resourc method");
		Reader reader;
		try
		{
		 reader=Resources.getResourceAsReader("com/employee/resources/mybatis-config.xml");	
		 logger.info("found file config xml");
		sessionFactory =  new SqlSessionFactoryBuilder().build(reader);
		
		}catch(IOException ie)
		{
			logger.info("A Stack Trace problem",ie);
			
		}
	}
	public  SqlSessionFactory getSqlSessionFactory()
	{
		resourc();
		return sessionFactory;
	}
	
	



}
