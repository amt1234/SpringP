package com.bridgeit.springrest.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bridgeit.springrest.model.Employee;
@Component
public class EmployeeDaoImpl implements EmployeeDao {

	// Dummy database. Initialize with some dummy values.
	private static List<Employee> empploeelist;
	{
		empploeelist = new ArrayList();
		empploeelist.add(new Employee(101, "poonam", "09-10-1995"));
		empploeelist.add(new Employee(102, "priya", "343-545-2345"));
		empploeelist.add(new Employee(103, "rohan", "876-237-2987"));
	}

	/*
	 * return you employee list
	 */
	public List list() {
		return empploeelist;
	}

	// return employee id for object
	@Override
	public Employee getEmployeeid(int id) {
		for (Employee employee : empploeelist) {
			if (employee.getId() == id) {
				return employee;
			}
		}
		return null;
	}

	// To create new employee
	@Override
	public Employee createNewEmployee(Employee employee) {
		empploeelist.add(employee);
		return employee;
	}

	// To delete employee by id
	@Override
	public Employee deleteEmployee(int id) {
		for(Employee employee:empploeelist)
		{
			if(employee.getId()==id)
			{
				 empploeelist.remove(employee);
				return employee;
			}
		}
		return null;
	}
}
