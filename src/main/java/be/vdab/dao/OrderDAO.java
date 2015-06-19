package be.vdab.dao;

import be.vdab.entities.Order;

public class OrderDAO extends AbstractDAO {

	public Order read(int id) {
		return getEntityManager().find(Order.class, id);
	}	

}
