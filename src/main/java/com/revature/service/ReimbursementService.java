package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	
	private ReimbursementDao rdao;
	
	/**
	 * Constructor Injection
	 * 
	 * A ReimbursementService object can never be instantiated without
	 * having its ReimbursementDao dependency fulfilled
	 */
	public ReimbursementService(ReimbursementDao rdao) {
		this.rdao = rdao;
	}

	public int insert(Reimbursement r) {
		return rdao.insert(r);
	}
	
	public List<Reimbursement> getAllPending() {
		return rdao.findAllPending();
	}
	
}
