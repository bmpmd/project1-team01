package com.revature.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.ManagerDaoImpl;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.service.EmployeeService;
import com.revature.service.ManagerService;
import com.revature.service.ReimbursementService;

public class RequestDispatcher {

	private static EmployeeService eserv = new EmployeeService(new EmployeeDaoImpl());
	private static ManagerService mserv = new ManagerService(new ManagerDaoImpl());
	private static ReimbursementService rserv = new ReimbursementService(new ReimbursementDaoImpl());
	private static ObjectMapper om = new ObjectMapper();

	public static void processLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// extract the parameters of the request
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// call confirmLogin() from the EmployeeSerivce and store the returned Employee
		Employee e = eserv.confirmLogin(username, password);
		Manager m = mserv.confirmLogin(username, password);

		// if the user exists, add them to the session
		if (e.getId() > 0) {
			HttpSession session = request.getSession();

			session.setAttribute("user", e);

			request.getRequestDispatcher("employee.html").forward(request, response);
		} else if (m.getId() > 0) {
			HttpSession session = request.getSession();

			session.setAttribute("user", m);

			request.getRequestDispatcher("manager.html").forward(request, response);
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<h3>No user found.</h3>");
			out.println("<a href=\"index.html\">Back</a>");
		}
	}

	public static void processLoginJS(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// set the content type and header of the response
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");

		PrintWriter out = response.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();

		// parse the JSOn string which is the body of the request
		try {
			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) request.getInputStream()));
			JsonObject jsonobj = root.getAsJsonObject();

			String username = jsonobj.get("username").getAsString();
			String password = jsonobj.get("password").getAsString();

			// call confirmLogin() from the EmployeeSerivce and store the returned Employee
			Employee e = eserv.confirmLogin(username, password);
			Manager m = mserv.confirmLogin(username, password);

			if (e.getId() > 0) {
				HttpSession session = request.getSession();

				session.setAttribute("user", e);

				String json = e.toJson();

				out.write(json);

				// request.getRequestDispatcher("employee.html").forward(request, response);
			} else if (m.getId() > 0) {
				HttpSession session = request.getSession();

				session.setAttribute("user", m);

				String json = m.toJson();

				out.write(json);

				// request.getRequestDispatcher("manager.html").forward(request, response);
			} else {
				params.addProperty("status", "process failed");
				String json = gson.toJson(params);
				out.write(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
			params.addProperty("status", "process failed");
			String json = gson.toJson(params);
			out.write(json);
		}
	}

	public static void processRegistration(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
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

	public static void processNewReimbursement(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		double amount = Double.parseDouble(request.getParameter("amount"));
		String type = request.getParameter("type");
		String description = request.getParameter("description");

		Employee e = (Employee) request.getSession().getAttribute("user");

		Reimbursement r = new Reimbursement(amount, LocalDateTime.now(), null, description, e, null,
				ReimbursementStatus.PENDING, ReimbursementType.valueOf(type));

		int pk = rserv.insert(r);

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		if (pk > 0) { // insertion was successful
			r.setId(pk);

			request.getRequestDispatcher("success-page-emp.html").forward(request, response);
		} else { // insertion failed
			request.getRequestDispatcher("fail-page-emp.html").forward(request, response);
		}
	}

	public static void getEmployeeTable(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json");

		List<Employee> emps = eserv.getAll();

		String jsonString = om.writeValueAsString(emps);

		PrintWriter out = response.getWriter();
		out.write(jsonString);
	}

	public static void getPendingReimbursements(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json");

		List<Reimbursement> tickets = rserv.getAllPending();

		String jsonString = om.writeValueAsString(tickets);

		PrintWriter out = response.getWriter();
		out.write(jsonString);
	}

	public static void getReimbursementsForId(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");

		PrintWriter out = response.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();

		// parse the JSOn string which is the body of the request
		try {
			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) request.getInputStream()));
			JsonObject jsonobj = root.getAsJsonObject();

			int id = jsonobj.get("id").getAsInt();

			List<Reimbursement> tickets = rserv.getAllByAuthorId(id);

			if (tickets != null) {
				String json = om.writeValueAsString(tickets);
				out.write(json);
			} else {
				params.addProperty("status", "process failed");
				String json = gson.toJson(params);
				out.write(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
			params.addProperty("status", "process failed");
			String json = gson.toJson(params);
			out.write(json);
		}
	}

	public static void approveReimbursement(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");

		PrintWriter out = response.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();

		try {
			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) request.getInputStream()));
			JsonObject jsonobj = root.getAsJsonObject();

			int managerId = jsonobj.get("id").getAsInt();
			int reimbursementId = jsonobj.get("reimburseId").getAsInt();

			if (rserv.approve(managerId, reimbursementId)) {
				params.addProperty("status", "process completed");
				String json = gson.toJson(params);
				out.write(json);
			} else {
				params.addProperty("status", "process failed");
				String json = gson.toJson(params);
				out.write(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
			params.addProperty("status", "process failed");
			String json = gson.toJson(params);
			out.write(json);
		}
	}

	public static void denyReimbursement(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");

		PrintWriter out = response.getWriter();

		Gson gson = new Gson();
		gson = new GsonBuilder().create();
		JsonObject params = new JsonObject();

		try {
			JsonParser jsonParser = new JsonParser();
			JsonElement root = jsonParser.parse(new InputStreamReader((InputStream) request.getInputStream()));
			JsonObject jsonobj = root.getAsJsonObject();

			int managerId = jsonobj.get("id").getAsInt();
			int reimbursementId = jsonobj.get("reimburseId").getAsInt();

			if (rserv.deny(managerId, reimbursementId)) {
				params.addProperty("status", "process completed");
				String json = gson.toJson(params);
				out.write(json);
			} else {
				params.addProperty("status", "process failed");
				String json = gson.toJson(params);
				out.write(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
			params.addProperty("status", "process failed");
			String json = gson.toJson(params);
			out.write(json);
		}
	}

	public static void updateProfile(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// extract the parameters of the request
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		HttpSession session = request.getSession();
		Employee e = (Employee) session.getAttribute("user");
		
		// update fields of Employee object with new information
		e.setFirstName(firstName);
		e.setLastName(lastName);
		e.setUsername(username);
		e.setPwd(password);
		e.setEmail(email);
		
		if (eserv.update(e)) {
			session.setAttribute("user", e);
			
			request.getRequestDispatcher("success-page-emp.html").forward(request, response);
		} else {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<h3>Update not successful.</h3>");
			out.println("<a href=\"employee.html\">Employee Homepage</a>");
			request.getRequestDispatcher("fail-page-emp.html").forward(request, response);

		}
	}

}
