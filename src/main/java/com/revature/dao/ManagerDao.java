package com.revature.dao;

import java.util.List;

import org.hibernate.Session;

import com.revature.models.Manager;
import com.revature.util.HibernateUtil;

public class ManagerDao {

	// CRUD Methods
	
	// creating Managers is handled internally and thus is not a
	// necessary to include here
	
	// READ
	/**
	 * @return List of all managers in the DB
	 */
	public List<Manager> findAll() {
		Session ses = HibernateUtil.getSession();
		
		return ses.createQuery("from Manager", Manager.class).list();
	}
	
}
