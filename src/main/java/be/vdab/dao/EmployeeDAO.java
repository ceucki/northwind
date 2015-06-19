package be.vdab.dao;

import java.util.List;

import be.vdab.entities.Employee;

public class EmployeeDAO extends AbstractDAO{

	public List<Employee> findAll(){
		return getEntityManager().createNamedQuery("Employee.findAll", Employee.class).getResultList();
	}
}
