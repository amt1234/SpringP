package com.bridgeit.springrest.service;

import java.util.List;

import com.bridgeit.springrest.model.Employee;


public interface EmployeeService {
	List<Employee> list();

	public Employee getEmployeeid(int id);

	public Employee createNewEmployee(Employee employee);

	public Employee deleteEmployee(int id);
}
