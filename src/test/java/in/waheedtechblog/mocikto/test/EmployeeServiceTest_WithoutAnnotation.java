package in.waheedtechblog.mocikto.test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import in.waheedtechblog.mockito.Employee;
import in.waheedtechblog.mockito.EmployeeDao;
import in.waheedtechblog.mockito.EmployeeServiceImpl;

//@RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
//@ContextConfiguration({ "beans.xml" })
public class EmployeeServiceTest_WithoutAnnotation {

	private EmployeeServiceImpl employeeServiceImpl;
	private EmployeeDao employeeDao;
	private Employee employee;

	@Before
	public void setUp() throws Exception {
		// mock() creates mocks without using annotation
		employeeServiceImpl = new EmployeeServiceImpl();
		employeeDao = mock(EmployeeDao.class);
		employee = mock(Employee.class);
		employeeServiceImpl.setEmployeeDao(employeeDao);
	}
	
	@Test
	public void testMockCreation() {
		assertNotNull(employeeDao);
		assertNotNull(employee);
	}
	
	@Test
	public void createEmployeeTest_verify_multiple_calls() {
		// Mockito can ensure whether a mock method is being called with
		// reequired arguments or not. It is done using the verify() method
		// Will fail as employeeDao does not get called at all

		// It add the behavior of employee dao
		when(employeeDao.addEmployee(any(Employee.class))).thenReturn(1L);

		// test the add functionality
		Assert.assertEquals(employeeServiceImpl.addEmployee(employee), 1L);
		Assert.assertEquals(employeeServiceImpl.addEmployee(employee), 1L);
		Assert.assertEquals(employeeServiceImpl.addEmployee(employee), 1L);

		// verify call to employeeDao to make sure it get called and
		// limit the method call to 3, no less and no more calls are allowed
		verify(employeeDao, times(3)).addEmployee(any(Employee.class));

		// verify that method was never called on a mock
		verify(employeeDao, never()).deleteEmployee(any(Employee.class));

		// other verify Option

		// check a minimum 1 call count
		verify(employeeDao, atLeastOnce()).addEmployee(any(Employee.class));

		// check if add function is called minimum 2 times
		verify(employeeDao, atLeast(2)).addEmployee(any(Employee.class));

		// check if add function is called maximum 3 times
		verify(employeeDao, atMost(3)).addEmployee(any(Employee.class));
	}

}
