package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class RequestDispatcher {

	private static EmployeeService eserv = new EmployeeService(new EmployeeDao());
	private static ObjectMapper om = new ObjectMapper();
	
	public static void processLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// extract the parameters of the request
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// call confirmLogin() from the EmployeeSerivce and store the returned Employee
		Employee e = eserv.confirmLogin(username, password);
		
		// if the user exists, add them to the session
		if (e.getId() > 0) {
			HttpSession session = request.getSession();
			
			session.setAttribute("user", e);
			
			// TODO: forward request to employee homepage
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<h3>No user found.</h3>");
			out.println("<a href=\"index.html\">Back</a>");
		}
	}
	
}
