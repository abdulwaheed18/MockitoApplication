/**
 * 
 */
package in.waheedtechblog.mockito;

import java.util.Iterator;

import org.springframework.stereotype.Repository;

/**
 * Data access object for {@link Employee}s.
 * 
 * @author abdul
 * 
 */
@Repository
public class EmployeeDaoImpl extends DaoImpl implements EmployeeDao {

	public long addEmployee(Employee employee) {
		return createObject(employee);
	}

	public void deleteEmployee(Employee employee) {
		deleteObject(employee);
	}

	public Employee getEmployee(long id) {
		return getObject(Employee.class, id);
	}

	public Employee updateEmployee(Employee employee) {
		return updateObject(employee);
	}

	public Iterator<Employee> getEmployees() {
		return getObjects(Employee.class);
	}
}
