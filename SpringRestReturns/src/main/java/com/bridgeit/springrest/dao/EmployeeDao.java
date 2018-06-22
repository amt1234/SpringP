package com.bridgeit.springrest.dao;

import java.util.List;

import com.bridgeit.springrest.model.Employee;

public interface EmployeeDao {
	List<Employee> list();

	public Employee getEmployeeid(int id);

	public Employee createNewEmployee(Employee employee);

	public Employee deleteEmployee(int id);
}
