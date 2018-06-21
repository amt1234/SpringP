package com.bridgeit.springrest.dao;

import java.util.ArrayList;
import java.util.List;

import com.bridgeit.springrest.model.Employee;

public class EmployeeDao {

	// Dummy database. Initialize with some dummy values.
		private static List<Employee> empploeelist;
		{
			empploeelist = new ArrayList();
			empploeelist.add(new Employee (101, "poonam", "09-10-1995"));
			empploeelist.add(new Employee(102, "priya", "343-545-2345"));
			empploeelist.add(new Employee(103, "rohan",  "876-237-2987"));
		}
		
		/*return you employee list
		 * */
	public List list() {
		return empploeelist;
	}
	
	//return employee id for object
	public Employee getEmployeeid(int id)
	{
		for(Employee employee:empploeelist)
		{
			if(employee.getId()==id)
			{
				return employee;
			}
		}
		return null;
		
	}
}
