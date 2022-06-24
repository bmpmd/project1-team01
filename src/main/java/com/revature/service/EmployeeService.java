package com.revature.service;

import java.util.Optional;

import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;

public class EmployeeService {

	private EmployeeDao edao;
	
	/**
	 * Constructor Injection
	 * 
	 * An EmployeeService object can never be instantiated without
	 * having its EmployeeDao dependency fulfilled
	 */
	public EmployeeService(EmployeeDao edao) {
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
	
}
