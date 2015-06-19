package be.vdab.dao;

import be.vdab.entities.Customer;

public class CustomerDAO extends AbstractDAO{

	public Customer read(int id){
		return getEntityManager().find(Customer.class, id);
	}
}
