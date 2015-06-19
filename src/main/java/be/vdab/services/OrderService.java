package be.vdab.services;

import be.vdab.dao.OrderDAO;
import be.vdab.entities.Order;

public class OrderService {
	private final OrderDAO orderDAO = new OrderDAO(); 
	
	public Order read(int id){
		return orderDAO.read(id);
	}	
	
}
