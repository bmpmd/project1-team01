package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// rewrite the URI to capture the relevant information
		// http://localhost:8080/project1-team01/login -- capture "login"
		final String URI = request.getRequestURI().replace("/project1-team01/", "");
		
		// set up a switch case statement to call the
		// appropriate functionality based on the URI parsed above
		switch (URI) {
		case "login":
			RequestDispatcher.processLogin(request, response);
			
			break;
		case "register":
			RequestDispatcher.processRegistration(request, response);
			
			break;
		case "form":
			RequestDispatcher.processNewReimbursement(request, response);
			
			break;
		case "employees":
			RequestDispatcher.getEmployeeTable(request, response);
			
			break;
		default:
			// TODO: send to a custom error page
			break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
