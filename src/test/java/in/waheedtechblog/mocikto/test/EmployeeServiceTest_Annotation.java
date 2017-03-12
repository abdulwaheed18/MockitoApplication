/**
 * 
 */
package in.waheedtechblog.mocikto.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import in.waheedtechblog.mockito.Employee;
import in.waheedtechblog.mockito.EmployeeDao;
import in.waheedtechblog.mockito.EmployeeService;
import in.waheedtechblog.mockito.EmployeeServiceImpl;

/**
 * @author GS-0409
 *
 */
// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration({ "beans.xml" })
public class EmployeeServiceTest_Annotation {

	// @InjectMocks annotation is used to create and inject the mock object
	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;

	// @Mock annotation is used to create the mock object to be injected
	@Mock
	private EmployeeDao employeeDao;

	@Mock
	private Employee employee;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testMockCreation() {
		assertNotNull(employeeDao);
		assertNotNull(employee);
	}

	@Test
	public void createEmployeeTest_when() {
		// Check how the Mockito adds a functionality to a mock object using the
		// method when()

		// It add the behavior of employee dao
		when(employeeDao.addEmployee(any(Employee.class))).thenReturn(1L);

		// test the add functionality
		Assert.assertEquals(employeeServiceImpl.addEmployee(employee), 1L);
		verify(employeeDao, times(1)).addEmployee(any(Employee.class));
	}

	@Test
	public void createEmployeeTest_verify() {
		// Mockito can ensure whether a mock method is being called with
		// reequired arguments or not. It is done using the verify() method

		// It add the behavior of employee dao
		when(employeeDao.addEmployee(any(Employee.class))).thenReturn(1L);

		// test the add functionality
		Assert.assertEquals(employeeServiceImpl.addEmployee(employee), 1L);

		// verify call to employeeDao to make sure it get called and
		// limit the method call to 1, no less and no more calls are allowed
		verify(employeeDao, times(1)).addEmployee(any(Employee.class));
	}

	@Test
	@Ignore
	public void createEmployeeTest_verify_failure() {
		// Mockito can ensure whether a mock method is being called with
		// reequired arguments or not. It is done using the verify() method
		// Will fail as employeeDao does not get called at all

		// It add the behavior of employee dao
		when(employeeDao.addEmployee(any(Employee.class))).thenReturn(1L);

		// test the add functionality
		// Assert.assertEquals(employeeServiceImpl.addEmployee(employee), 1L);

		// verify call to employeeDao to make sure it get called and
		// limit the method call to 1, no less and no more calls are allowed
		verify(employeeDao, times(1)).addEmployee(any(Employee.class));
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

	@Test(expected = Exception.class)
	public void createEmployeeTest_Exception() throws Exception {
		// Mockito provides the capability to a mock to throw exceptions, so
		// exception handling can be tested

		// add the behavior to throw exception
		doThrow(new Exception("Employee not found")).when(employeeDao).getEmployee(2);

		// test the add functionality
		Assert.assertEquals(employeeServiceImpl.getEmployee(2), employee);
	}

	@Test
	public void createEmployeeTest_order() throws Exception {
		// Mockito provides Inorder class which takes care of the order of
		// method calls that the mock is going to make in due course of its
		// action.

		// It add the behavior of employee dao
		when(employeeDao.addEmployee(any(Employee.class))).thenReturn(1L);
		when(employeeDao.getEmployee(1)).thenReturn(employee);

		Assert.assertEquals(employeeServiceImpl.addEmployee(employee), 1L);
		Assert.assertEquals(employeeServiceImpl.getEmployee(1), employee);

		// create an inOrder verifier for a single mock
		InOrder inOrder = inOrder(employeeDao);

		// following will make sure that add is first called then subtract is
		// called.
		inOrder.verify(employeeDao).addEmployee(any(Employee.class));
		inOrder.verify(employeeDao).getEmployee(1);

	}

	@Test
	@Ignore
	public void createEmployeeTest_order_mismacth() throws Exception {
		// Mockito provides Inorder class which takes care of the order of
		// method calls that the mock is going to make in due course of its
		// action.

		// It add the behavior of employee dao
		when(employeeDao.addEmployee(any(Employee.class))).thenReturn(1L);
		when(employeeDao.getEmployee(1)).thenReturn(employee);

		Assert.assertEquals(employeeServiceImpl.addEmployee(employee), 1L);
		Assert.assertEquals(employeeServiceImpl.getEmployee(1), employee);

		// create an inOrder verifier for a single mock
		InOrder inOrder = inOrder(employeeDao);

		// following will make sure that add is first called then subtract is
		// called.
		inOrder.verify(employeeDao).getEmployee(1);
		inOrder.verify(employeeDao).addEmployee(any(Employee.class));
	}

	@Test
	public void createEmployeeTest_reset() {
		// Mockito provides the capability to reset a mock, so that it can be
		// reused later.

		// It add the behavior of employee dao
		when(employeeDao.addEmployee(any(Employee.class))).thenReturn(1L);

		// test the add functionality
		Assert.assertEquals(employeeServiceImpl.addEmployee(employee), 1L);

		verify(employeeDao, times(1)).addEmployee(any(Employee.class));

		// reset the mock so that can be used later
		reset(employeeDao);

		// Comment out this statement to check failure behaviour
		when(employeeDao.addEmployee(any(Employee.class))).thenReturn(1L);

		// throw error as employeeDao behavious is reset
		Assert.assertEquals(employeeServiceImpl.addEmployee(employee), 1L);

	}

	@Test
	public void createEmployeeTest_timeout() {
		// Mockito provides a special Timeout option to test if a method is
		// called within its stipulated time frame.

		// It add the behavior of employee dao
		when(employeeDao.addEmployee(any(Employee.class))).thenReturn(1L);

		// test the add functionality
		Assert.assertEquals(employeeServiceImpl.addEmployee(employee), 1L);

		// verify call to add employee method to be completed within 100 ms
		verify(employeeDao, timeout(100)).addEmployee(any(Employee.class));

		reset(employeeDao);

		// It add the behavior of employee dao
		when(employeeDao.addEmployee(any(Employee.class))).thenReturn(1L);

		// test the add functionality
		Assert.assertEquals(employeeServiceImpl.addEmployee(employee), 1L);

		// invocation count can be added to ensure addEmployee invocations
		// can be checked within given timeframe
		verify(employeeDao, timeout(100).times(1)).addEmployee(any(Employee.class));
	}
}
