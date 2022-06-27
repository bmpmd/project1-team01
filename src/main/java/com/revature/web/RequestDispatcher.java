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
			
			request.getRequestDispatcher("employee.html").forward(request, response);
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<h3>No user found.</h3>");
			out.println("<a href=\"index.html\">Back</a>");
		}
	}
	
	public static void processRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// extract the parameters of the request
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		// construct a new Employee
		Employee e = new Employee(username, password, firstname, lastname, email, null);
		
		int pk = eserv.register(e);
		
		if (pk > 0) { // insertion was successful
			e.setId(pk);
			
			// add the user to the session and automatically sign them in
			HttpSession session = request.getSession();
			session.setAttribute("user", e);
			
			request.getRequestDispatcher("employee.html").forward(request, response);
		} else { // insertion failed
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			
			out.println("<h3>Registration failed.</h3>");
			out.println("<a href=\"index.html\">Back</a>");
		}
	}
	
}
