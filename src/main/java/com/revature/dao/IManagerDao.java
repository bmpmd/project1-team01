package com.revature.dao;

import java.util.List;

import com.revature.models.Manager;

public interface IManagerDao {

	// C.R.U.D. Methods
	
	// Read
	
	/**
	 * Reads all Managers from the DB and returns them in
	 * a list
	 * @return -- a list of all Manager objects in the DB
	 */
	public List<Manager> findAll();
	
}
