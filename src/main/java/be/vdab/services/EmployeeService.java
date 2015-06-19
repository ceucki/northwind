package be.vdab.services;

import java.util.List;

import be.vdab.dao.EmployeeDAO;
import be.vdab.entities.Employee;

public class EmployeeService {
private final EmployeeDAO employeeDAO = new EmployeeDAO();
	
	public List<Employee> findAll(){
		return employeeDAO.findAll();
	}
}
