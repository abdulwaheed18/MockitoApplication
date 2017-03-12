package in.waheedtechblog.mocikto.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import in.waheedtechblog.mockito.Employee;
import in.waheedtechblog.mockito.EmployeeDao;
import in.waheedtechblog.mockito.EmployeeService;
import in.waheedtechblog.mockito.EmployeeServiceImpl;

public class EmployeeServiceTest_Spy {

	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;

	private EmployeeDao employeeDao;

	@Mock
	private Employee employee;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		SpyEmployeeDaoImpl spyEmployeeDaoImpl = new SpyEmployeeDaoImpl();

		// create a spy on actual object
		employeeDao = spy(spyEmployeeDaoImpl);
		employeeServiceImpl.setEmployeeDao(employeeDao);
	}

	@Test
	public void testMockCreation() {
		assertNotNull(employeeDao);
		assertNotNull(employee);
	}

	@Test
	public void spyTest() {
		Assert.assertEquals(employeeServiceImpl.addEmployee(employee), 1L);
	}

	// Implementation of spy object
	class SpyEmployeeDaoImpl implements EmployeeDao {

		public long addEmployee(Employee employee) {
			return 1;
		}

		public void deleteEmployee(Employee employee) {
			// TODO Auto-generated method stub

		}

		public Employee getEmployee(long id) {
			// TODO Auto-generated method stub
			return null;
		}

		public Iterator<Employee> getEmployees() {
			// TODO Auto-generated method stub
			return null;
		}

		public Employee updateEmployee(Employee employee) {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
