package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import be.vdab.valueobjects.Adres;


@Entity
@Table(name="customers")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	
	private String contactName;

	private String contactTitle;	

	private String fax;

	private String name;

	private String phone;

	@Embedded
	private Adres adres;


	@OneToMany(mappedBy="customer")
	private Set<Order> orders;
	
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="countryid")
	private Country country;
	
		
	public Customer() {
	}

	public void setCountry(Country country) {
		if (this.country!= null && this.country.getCustomers().contains(this)){
			this.country.removeCustomer(this);
		}
		this.country = country;
		if(country != null && country.getCustomers().contains(this)){
			country.addCustomer(this);
		}
	}

	public Adres getAdres() {
		return adres;
	}

	public Country getCountry() {
		return country;
	}	

	public int getId() {
		return this.id;
	}
	

	public String getContactName() {
		return this.contactName;
	}

	public String getContactTitle() {
		return this.contactTitle;
	}
	

	public String getFax() {
		return this.fax;
	}

	
	public String getName() {
		return this.name;
	}

	
	public String getPhone() {
		return this.phone;
	}

	public Set<Order> getOrders() {
		return Collections.unmodifiableSet(orders);
	}
	

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setCustomer(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setCustomer(null);

		return order;
	}

	@Override
	public boolean equals(Object obj){
		if (! (obj instanceof Customer)){
			return false;
		}
		return ((Customer)obj).name.equals(name)  && ((Customer)obj).phone.equals(phone);  
	}
	
	@Override
	public int hashCode(){
		return name.hashCode() + phone.hashCode();
	}
}