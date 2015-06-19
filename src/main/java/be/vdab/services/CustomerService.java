package be.vdab.services;

import be.vdab.dao.CustomerDAO;
import be.vdab.entities.Customer;

public class CustomerService {

	private final CustomerDAO customerDAO = new CustomerDAO();
	
	public Customer read(int id){
		return customerDAO.read(id);
	}
}
