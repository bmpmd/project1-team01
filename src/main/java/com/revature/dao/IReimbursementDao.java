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
	/**
	 * Retrieve a list of all Reimbursements in the DB
	 * with the associated author id
	 * @param id -- the author id of Reimbursements to be retrieved
	 * @return -- a list of all corresponding Reimbursements
	 */
	public List<Reimbursement> findByAuthorId(int id);
	/**
	 * Retrieve a list of all Reimbursements in the DB
	 * with status = 'APPROVED' or status = 'DENIED'
	 * @return -- a list of all resolved reimbursements
	 */
	public List<Reimbursement> findAllResolved();
	
	// Update
	
	/**
	 * Approve a reimbursement in the DB
	 * @param managerId -- the id of the approving manager
	 * @param reimbursementId -- the id of the reimbursement to be approved
	 * @return -- true if successfully approved, false otherwise
	 */
	public boolean approve(int managerId, int reimbursementId);
	/**
	 * Deny a reimbursement in the DB
	 * @param managerId -- the id of the denying manager
	 * @param reimbursementId -- the id of the reimbursement to be denied
	 * @return -- true if successfully denied, false otherwise
	 */
	public boolean deny(int managerId, int reimbursementId);
	
}
