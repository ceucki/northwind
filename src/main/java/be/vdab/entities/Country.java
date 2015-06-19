package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;



@Entity
@Table(name="countries")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@OneToMany(mappedBy="country")
	@OrderBy("name")
	private Set<Customer> customers;
	
	@OneToMany(mappedBy="country")
	private Set<Employee> employees;
	
	
	public Set<Employee> getEmployees() {
		return Collections.unmodifiableSet(employees);
	}

	
	
	public void addEmployee(Employee employee){
		employees.add(employee);
		if (employee.getCountry() != this){
			employee.setCountry(this);
		}
	}
	
	public void removeEmployee(Employee employee){
		employees.remove(employee);
		if (employee.getCountry() ==this){
			employee.setCountry(null);
		}
	}

	public Country() {
	}

	public int getId() {
		return this.id;
	}	

	public String getName() {
		return this.name;
	}

	public Set<Customer> getCustomers() {
		return Collections.unmodifiableSet(customers);
	}	
	
	public void addCustomer(Customer customer){
		customers.add(customer);
		if (customer.getCountry() !=this){
			customer.setCountry(this);
		}
	}
	
	public void removeCustomer(Customer customer){
		customers.remove(customer);
		if (customer.getCountry() == null){
		customer.setCountry(null);	
		}
	}
	
	//Suppliers relatie
	@OneToMany(mappedBy="country")
	private Set<Supplier> suppliers;


	public Set<Supplier> getSuppliers() {
		return Collections.unmodifiableSet(suppliers);
	}	
	
	public void addSupplier(Supplier supplier){
		suppliers.add(supplier);
		if (supplier.getCountry() != this){
			supplier.setCountry(this);
		}
	}
	
	public void removeSupplier(Supplier supplier){
		suppliers.remove(supplier);
		if (supplier.getCountry() == this){
			supplier.setCountry(this);
		}
	}
	
	
}