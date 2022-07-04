package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDaoImpl implements IEmployeeDao {
	
	// C.R.U.D. Methods

	// Create
	
	@Override
	public int insert(Employee e) {
		//session and transaction come from HIBERNATE
		//grab session object 
		Session ses = HibernateUtil.getSession();
		
		//begin tx
		Transaction tx = ses.beginTransaction();
		//capture pk returned when the session method save() is called
		int pk = (int) ses.save(e);//typecast to int 
		
		//finally, commit transaction and return pk 
		tx.commit();
		
		return pk;
	}
	
	// Read
	
	@Override
	public List<Employee> findAll(){
		Session ses = HibernateUtil.getSession();
		
		//make an HQL statement 
		List<Employee> employees = ses.createQuery("from Employee e", Employee.class).list(); 
		
		return employees; 
	}
	
	// Update
	
	@Override
	public boolean update(Employee e) {
		//TODO: implement this method 
		return false;
	}
	
	// Delete
}
