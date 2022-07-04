package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface IReimbursementDao {

	// C.R.U.D. Methods
	
	// Create
	
	/**
	 * Create a new Reimbursement in the DB
	 * @param r -- the new Reimbursement to be inserted
	 * @return -- the primary key created by the DB
	 */
	public int insert(Reimbursement r);
	
	// Read
	
	/**
	 * Retrieve a list of all Reimbursements in the DB
	 * with status = 'PENDING'
	 * @return -- a list of all pending reimbursements
	 */
	public List<Reimbursement> findAllPending();
	
}
