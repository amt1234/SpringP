package com.bridgeit.springrest.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.springrest.dao.EmployeeDao;
import com.bridgeit.springrest.model.Employee;

@RestController
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeDao employeedao;
	
	@GetMapping("/")
	public String getMethod() {
		return "home";
	}

	/*
	 * Insted of writing this you write @getmapping so it also provide same feature
	 * in shortcut
	 * 
	 * @RequestMapping(value="/rest", method=RequestMethod.GET)
	 */
	/*@GetMapping("/rest")
	public @ResponseBody List<Employee> getAllEmployee() {
		LOGGER.info("Start getAllEmployees.");
		Employee employee = new Employee();
		employee.setId(101);
		employee.setName("poonam");
		employee.setCreateDate(new Date());
		empData.put(1, employee);

		Employee employee2 = new Employee();
		employee2.setId(102);
		employee2.setName("priya");
		employee2.setCreateDate(new Date());
		empData.put(2, employee2);

		List<Employee> emps = new ArrayList<Employee>();
		Set<Integer> set = empData.keySet();
		for (Integer integer : set) {
			emps.add(empData.get(integer));
		}
		System.out.println("empData :" + empData);
		System.out.println("emps :" + emps.toString());

		return emps;
		
	return employeedao.list();

	}
	*/
	
	//To get the Employee list
	@GetMapping("/listOfEmployee")
	public  List<Employee> getEmployeeList()
	{
		LOGGER.info("LIST OF EMPLOYEE");
		return employeedao.list();	
	}
	
	//To get the Employee object based on the id
	@GetMapping("/employee/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable("id") int empId)
	{
		LOGGER.info("Start getEmployee. ID="+empId);
		
		Employee employee=employeedao.getEmployeeid(empId);
		if(employee==null)
		{
			return new ResponseEntity<String>("No Customer found for ID " + empId, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);	
	}
	
	
	//To create the Employee object and store it
	@PostMapping(value="/employee/create")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)
	{
		LOGGER.info("START CREATE NEW EMPLOYEE");
		employeedao.createNewEmployee(employee);

		return new ResponseEntity<>(employee,HttpStatus.OK);	
	}
	
	//To delete employee by id
	@PutMapping("/delete/{id}")
	public ResponseEntity<String>  deleteEmployeeById(@PathVariable("id")int empId)
	{
		LOGGER.info("start delete employee");
		 employeedao.deleteEmployee(empId);	
		 return new ResponseEntity<String>("employee deleted",HttpStatus.OK);
	}
	
}
