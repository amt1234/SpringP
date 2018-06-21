package com.bridgeit.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bridgeit.spring.model.Employee;

@Controller
public class EmployeeController {

	Map<Integer, Employee> empData = new HashMap<Integer, Employee>();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	 @RequestMapping(value="/rest", method=RequestMethod.GET)
	public @ResponseBody List<Employee> getAllEmployee() {
		LOGGER.info("Start getAllEmployees.");
		Employee employee=new Employee();
		employee.setId(101);
		employee.setName("poonam");
		employee.setCreateDate(new Date());
		empData.put(1, employee);
		
		Employee employee2=new Employee();
		employee2.setId(102);
		employee2.setName("priya");
		employee2.setCreateDate(new Date());
		empData.put(2, employee2);
		
		List<Employee> emps = new ArrayList<Employee>();
		Set<Integer> set = empData.keySet();
		for (Integer integer : set) {
			emps.add(empData.get(integer));
		}
		System.out.println("empData :"+empData);
		System.out.println("emps :"+emps.toString());
		
		return emps;

	}
}
