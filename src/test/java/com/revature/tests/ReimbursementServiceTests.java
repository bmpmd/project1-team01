package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.ReimbursementDao;
import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.service.ReimbursementService;

public class ReimbursementServiceTests {
	private ReimbursementService rserv;
	private ReimbursementDao mockdao;

	@Before
	public void setup() {
		mockdao = mock(ReimbursementDao.class);
		rserv = new ReimbursementService(mockdao);
	}

	@After
	public void teardown() {
		mockdao = null;
		rserv = null;
	}
	
	
	/**
	 * happy/fail path for reimburse insert 
	 */
	
	@Test 
	public void testInsertReimbursement_Success() {
		List<Reimbursement> emptyList = new ArrayList<Reimbursement>();
		Employee e1 = new Employee(20, "user1", "password1", "firstname1", "lastname1", "user1@mail.com", emptyList);
		Manager m1 = new Manager(21, "user1man", "password1man", "firstnameman", "lastnamemanager", "manager@mail.com", emptyList);
		LocalDateTime now = LocalDateTime.now();
		
 
        // Adding 1 year, 1 month, 1 week and 1 day
        LocalDateTime later = now.plusYears(1)
                                           .plusMonths(1)
                                           .plusWeeks(1)
                                           .plusDays(1);
		//create reimburse 
		Reimbursement r = new Reimbursement(0, 200, now, later, "this is the description",
				e1, m1, ReimbursementStatus.PENDING, ReimbursementType.TRAVEL);
		
		
		int actual = rserv.insert(r);
		int expected = r.getId();
		
		//assert equal
		assertEquals(expected, actual);
		
	}
	
	
			
			
}
