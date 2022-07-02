package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDao {


	//no interfaces? 
	
	
	//crud methods 
	//CREATE
	//since we are implementing a registration for new employees, we create those 
	/*
	 * @param Employee object to be inserted into DB 
	 * @return the primary key assigned by the DB to this Employee object
	 * we insert into the DB 
	 */
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
	
	//READ
	/*
	 * @return List of all employees in the DB 
	 */
	public List<Employee> findAll(){
		//TODO: check if this method returns badly since typecasting back into java might return errors
		//from the new SQL types we haven't been exposed to yet 
		//grab sess
		Session ses = HibernateUtil.getSession();
		
		//make an HQL statement 
		List<Employee> employees = ses.createQuery("from Employee e", Employee.class).list(); 
		
		return employees; 
	}
	
	
	//UPDATE
	public boolean update(Employee e) {
		//TODO: implement this method 
		return false;
	}
	
	//DELETE
	public boolean delete(int id) {
		//TODO: complete this method 
		return false; 
	}
}
