package com.employee.controller;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

@RestController
public class EmployeeController {
	
	private final  Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired

	private EmployeeService empServ;
	
	
	@RequestMapping("/")
	public ModelAndView he()
	{
		return new ModelAndView("index");
	}
	
	//Create Employee
	
		@PostMapping(value="/addemployee")
			public ResponseEntity<?> addEmpl(@RequestBody Employee emp )
		{
			try
			{
				if(emp !=null)
				{
					logger.info("Employee created");
			empServ.saveEmployee(emp);
			return new ResponseEntity<>("Created successfull",HttpStatus.CREATED);
				}else
				{
					logger.error("Bad request for Employee creation");
					return new ResponseEntity<>("Enter values in Body field",HttpStatus.BAD_REQUEST);
				}
			}
		catch(Exception ex)
			{
			logger.error("Employee Id already exists!");
			return new ResponseEntity<>("id already exists",HttpStatus.EXPECTATION_FAILED);
			}
		}

		
	//Read an Employee
	
	@GetMapping("/employee/{empId")
	public ResponseEntity<Employee> getEmpl(@PathVariable ("empId") int empId)
	{
		Employee emplo = empServ.getEmployee(empId);
		
		if(emplo != null)
		{
			logger.info("Reading an Employee details");
			return new ResponseEntity<>(emplo,HttpStatus.OK);
		}
		else
		{
			logger.error("Not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Read All Employee details (List Employees in JSON Format)
	
		@GetMapping("/listemployees")
		@Produces(MediaType.APPLICATION_JSON)
		
		public ResponseEntity<List<Employee>> lisEmp()
		{
			try
			{
			List<Employee> lis = empServ.getAllEmployees();
			if(lis.isEmpty())
			{
				logger.error("Data not found");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else
			{
			
			logger.info("Reading All Employee details");
			return new ResponseEntity<>(lis,HttpStatus.OK);
			}
			}catch(Exception ex)
			{
				return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

			//Update Employee
	
	@PutMapping("/editemployee/{empId}")
	public ResponseEntity<?> updateEmp(@PathVariable ("empId") int empId,@RequestBody Employee empl)
	{
		Employee emn = empServ.getEmployee(empId);
		
		System.out.println("old data name "+emn.getEmpName());
		
		if(emn.getEmpId()==empId)
		{
			logger.info("Employee degtails updated successfully");
			empl.setEmpId(empId);
			empServ.update(empl);
		return new	ResponseEntity<>("Updated Employee Successfully",HttpStatus.OK);
		}
	else
	{
		logger.error("Data not found for employee update");
		return new	ResponseEntity<>("Unable to update the employee value",HttpStatus.NOT_FOUND);
	}
	}
	
	// Delete Employee
	
		@DeleteMapping("/deleteemployee/{empId}")
		public ResponseEntity<?> delEmp(@PathVariable ("empId") int empId)
		{
			logger.info("Employee deleted successfully");
			empServ.deleteEmployee(empId);
			return new ResponseEntity<>("Deleted Employee Successfully",HttpStatus.OK);
		}
	
}
