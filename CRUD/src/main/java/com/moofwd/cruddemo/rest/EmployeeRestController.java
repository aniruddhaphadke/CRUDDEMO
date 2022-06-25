package com.moofwd.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moofwd.cruddemo.dao.EmployeeDAO;
import com.moofwd.cruddemo.entity.Employee;
import com.moofwd.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController 
{
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService)
	{
		employeeService = theEmployeeService;
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll()
	{
		return employeeService.findAll();
	}
	
	//GET the employee with ID
	@GetMapping("/employees/{employeeID}")
	public Employee getEmployee(@PathVariable int employeeID)
	{
		Employee theEmployee = employeeService.findById(employeeID);
		if(theEmployee == null)
		{
			throw new RuntimeException("Employee id Not found");
		}
		return theEmployee;
		
	}
	
	//Add new employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee)
	{
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;
	}
	
	//@Put mapping to update the employee
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee)
	{
		employeeService.save(theEmployee);
		return theEmployee;
		
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId)
	{
		Employee tempEmployee = employeeService.findById(employeeId);
		
		if(tempEmployee == null)
		{
			throw new RuntimeException("Employee not found"+employeeId);
			
		}
		employeeService.deleteById(employeeId);
		return "Employee deleted successfully"+employeeId;
	}
	

}
