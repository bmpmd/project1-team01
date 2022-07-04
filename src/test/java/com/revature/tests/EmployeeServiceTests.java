package com.revature.tests;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.service.EmployeeService;

public class EmployeeServiceTests {
	private EmployeeService eserv;
	private EmployeeDaoImpl mockdao;

	@Before
	public void setup() {
		mockdao = mock(EmployeeDaoImpl.class);
		eserv = new EmployeeService(mockdao);
	}

	@After
	public void teardown() {
		mockdao = null;
		eserv = null;
	}

	/**
	 * happy/fail path for confirmLogin
	 */
	@Test
	public void testConfirmLogin_Success() {
		// 1 create a fake list of emps
		// this is the dummy data we feed to Mockito
		List<Reimbursement> emptyList = new ArrayList<Reimbursement>();
		Employee e1 = new Employee(20, "user1", "password1", "firstname1", "lastname1", "user1@mail.com", emptyList);
		
		Employee e2 = new Employee(21, "user2", "password2", "firstname2", "lastname2", "user2@mail.com", emptyList);

		List<Employee> emps = new LinkedList<Employee>();
		emps.add(e1);
		emps.add(e2);

		// 2 setup the mockdao's behavior
		// findall() method's data to provide fake data
		when(mockdao.findAll()).thenReturn(emps);

		// 3 capture actual/expected output of the method
		//login as user1 with password password1
		Employee actual = eserv.confirmLogin("user1", "password1");
		Employee expected = e1;

		// assert that they're equal
		assertEquals(expected, actual);

	}

	@Test
	public void testConfirmLogin_Fail() {
		// returns an empty employee obj if fails

		// 1 create a fake list of emps
		// this is the dummy data we feed to Mockito
		List<Reimbursement> emptyList = new ArrayList<Reimbursement>();
		Employee e1 = new Employee(20, "user1", "password1", "firstname1", "lastname1", "user1@mail.com", emptyList);
		
		Employee e2 = new Employee(21, "user2", "password2", "firstname2", "lastname2", "user2@mail.com", emptyList);
		List<Employee> emps = new LinkedList<Employee>();
		emps.add(e1);
		emps.add(e2);

		// 2 setup the mockdao's behavior
		// findAll() method's data to provide fake data
		when(mockdao.findAll()).thenReturn(emps);
		
		// 3 capture acutal output of the method
		// capture expected ouytput of the methods

		// login as user1, mockdao will mock the behavior of findall
		Employee actual = eserv.confirmLogin("user1", "password2");//bad password, so login fails 
		Employee expected = new Employee();//expected to be empty 
		
		assertEquals(actual, expected);//we expect these to be equal to an empty employee obj
				

	}

}

