package com.bridgeit.springrest.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.springrest.dao.EmployeeDao;
import com.bridgeit.springrest.model.Employee;

@RestController
public class EmployeeController {

	Map<Integer, Employee> empData = new HashMap<Integer, Employee>();

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

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
	@GetMapping("/employee/{id}")
	public @ResponseBody Employee getEmployee(@PathVariable("id") int id) {
		LOGGER.info("Start getEmployee. ID="+id);
		
		if(employeedao.equals(id))
		{
			System.out.println("Employee id");
		}
		return new Employee();
	
	}
	
}
