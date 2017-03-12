/**
 * 
 */
package in.waheedtechblog.mockito;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provide methods to work with Employees.
 * 
 * @author abdul
 * 
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;

	@Transactional
	public long addEmployee(Employee employee) {
		long id = employeeDao.addEmployee(employee);
		return id;
	}

	@Transactional
	public void deleteEmployee(Employee employee) {
		employeeDao.deleteEmployee(employee);

	}

	@Transactional
	public void display() {
		Iterator<Employee> employees = employeeDao.getEmployees();
		while (employees.hasNext()) {
			System.out.println(employees.next().toString());
		}
	}

	@Transactional
	public void display(long employeeId) {
		Employee employee = employeeDao.getEmployee(employeeId);
		System.out.println(employee);
	}

	@Transactional
	public Employee getEmployee(long employeeId) throws Exception {
		Employee employee = employeeDao.getEmployee(employeeId);
		if(employee == null) throw new Exception("Employee not found");
		return employee;
	}

	@Transactional
	public void updateEmployee(Employee employee) {
		employeeDao.updateEmployee(employee);
	}

	@Autowired
	@Required
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

}
