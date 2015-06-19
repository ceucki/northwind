package be.vdab.services;

import java.util.List;

import be.vdab.dao.CountryDAO;
import be.vdab.entities.Country;

public class CountryService {

	private final CountryDAO countryDAO = new CountryDAO();

	public List<Country> findAll() {
		return countryDAO.findAll();
	}

	public Country read(int id) {
		return countryDAO.read(id);
	}
}
