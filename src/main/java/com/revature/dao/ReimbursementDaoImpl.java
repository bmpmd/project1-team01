package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.util.HibernateUtil;

public class ReimbursementDaoImpl implements IReimbursementDao {

	// C.R.U.D. Methods
	
	// Create
	
	@Override
	public int insert(Reimbursement r) {
		// grab session object
		Session ses = HibernateUtil.getSession();
		
		// begin a new transaction
		Transaction tx = ses.beginTransaction();
		
		// capture primary key returned when the session method save() is called
		int pk = (int) ses.save(r);
		
		
		// commit transaction and return primary key
		tx.commit();
		
		return pk;
	}
	
	// Read
	
	@Override
	public List<Reimbursement> findAllPending() {
		// grab the session object from the utility class
		Session ses = HibernateUtil.getSession();
		
		// select all reimbursements where status = PENDING
		List<Reimbursement> tickets = ses.createQuery("from Reimbursement R WHERE R.status = 'PENDING'", Reimbursement.class).list();
		
		return tickets;
	}

	@Override
	public List<Reimbursement> findByAuthorId(int id) {
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> tickets = ses.createQuery("from Reimbursement R WHERE R.author = " + id, Reimbursement.class).list();
		
		return tickets;
	}
	
}
