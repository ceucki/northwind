package be.vdab.dao;

import java.util.List;

import be.vdab.entities.Country;

public class CountryDAO extends AbstractDAO{

	public List<Country> findAll(){
		return getEntityManager().createNamedQuery("Country.findAll", Country.class).getResultList();
	}	

	public Country read(int id) {			
		return getEntityManager().find(Country.class, id);
	}
}
