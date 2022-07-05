package com.revature.service;

import java.util.List;
import java.util.Optional;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.models.Employee;

public class EmployeeService {

	private EmployeeDaoImpl edao;
	
	/**
	 * Constructor Injection
	 * 
	 * An EmployeeService object can never be instantiated without
	 * having its EmployeeDao dependency fulfilled
	 */
	public EmployeeService(EmployeeDaoImpl edao) {
		this.edao = edao;
	}
	
	/**
	 * Method to confirm the validity of an attempted login
	 * @param username -- username to be checked for existence in the DB
	 * @param password -- password to be checked against the username
	 * @return -- the Employee with the matching credentials in the database, or
	 * 				an empty Employee if one does not exist
	 */
	public Employee confirmLogin(String username, String password) {
		Optional<Employee> possibleEmp = edao.findAll().stream()
				.filter(e -> (e.getUsername().equals(username) && e.getPwd().equals(password)))
				.findFirst();
		
		return possibleEmp.isPresent() ? possibleEmp.get() : new Employee();
	}
	
	public int register(Employee e) {
		return edao.insert(e);
	}
	
	public List<Employee> getAll() {
		return edao.findAll();
	}
	
	public boolean update(Employee e) {
		return edao.update(e);
	}
	
}
