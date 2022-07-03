package com.revature.service;

import java.util.Optional;

import com.revature.dao.ManagerDaoImpl;
import com.revature.models.Manager;

public class ManagerService {

	private ManagerDaoImpl mdao;
	
	/**
	 * Constructor Injection
	 * 
	 * A ManagerService object can never be instantiated without
	 * having its ManagerDao dependency fulfilled
	 */
	public ManagerService(ManagerDaoImpl mdao) {
		this.mdao = mdao;
	}
	
	public Manager confirmLogin(String username, String password) {
		Optional<Manager> possibleManager = mdao.findAll().stream()
				.filter(m -> (m.getUsername().equals(username) && m.getPwd().equals(password)))
				.findFirst();
		
		return possibleManager.isPresent() ? possibleManager.get() : new Manager();
	}
	
}
