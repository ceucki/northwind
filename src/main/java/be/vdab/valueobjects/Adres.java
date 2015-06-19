package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Adres implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String address, city, postalcode;

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public Adres (String address, String city, String postalcode){
		this.address=address;
		this.city=city;
		this.postalcode=postalcode;
	}
	
	protected Adres(){};
}
