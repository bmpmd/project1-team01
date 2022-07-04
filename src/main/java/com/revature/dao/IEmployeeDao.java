package com.revature.dao;

import java.util.List;

import com.revature.models.Employee;

public interface IEmployeeDao {

	// C.R.U.D. Methods
	
	// Create
	
	/**
	 * Inserts a new Employee into the DB
	 * @param e -- the Employee object to be inserted
	 * @return -- the primary key assigned by the DB
	 */
	public int insert(Employee e);
	
	// Read
	
	/**
	 * Reads all Employee objects in the DB and returns them
	 * as a List
	 * @return -- list of all Employee objects in the DB
	 */
	public List<Employee> findAll();
	
	// Update
	
	/**
	 * Update the Employee in the DB with the matching ID
	 * of the passed object to hold the new field information
	 * of that object
	 * @param e -- the Employee to be updated in the DB
	 * @return -- true if the Employee was successfully updated,
	 * 				false otherwise
	 */
	public boolean update(Employee e);
	
}
