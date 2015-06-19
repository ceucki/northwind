package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import be.vdab.valueobjects.Adres;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Temporal(TemporalType.DATE)
	private Date birth;	

	private String extension;

	private String firstName;

	@Temporal(TemporalType.DATE)
	private Date hired;

	private String homePhone;

	private String lastName;

	private BigDecimal salary;

	private String title;

	private String titleOfCourtesy;

	@Embedded
	private Adres adres;

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "reportsTo")
	private Employee chef;
	
	public Employee getChef() {
		return chef;
	}
	

	public Set<Employee> getMinors() {
		return Collections.unmodifiableSet(minors);
	}

	public void setChef(Employee chef) {
		this.chef = chef;
	}

	public void setMinors(Set<Employee> minors) {
		this.minors = minors;
	}

	@OneToMany(mappedBy = "chef")
	private Set<Employee> minors;

	
	
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="countryid")
	private Country country;
	
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		if (this.country !=null && this.country.getEmployees().contains(this)){
			this.country.removeEmployee(this);
		}
		this.country = country;
		if (country!= null && country.getEmployees().contains(this)){
			country.addEmployee(this);
		}
	}

	public Employee() {
	}

	public int getId() {
		return this.id;
	}

	public Date getBirth() {
		return this.birth;
	}

	
	public String getExtension() {
		return this.extension;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public Date getHired() {
		return this.hired;
	}

	public String getHomePhone() {
		return this.homePhone;
	}

	public String getLastName() {
		return this.lastName;
	}

	public BigDecimal getSalary() {
		return this.salary;
	}

	public String getTitle() {
		return this.title;
	}

	public String getTitleOfCourtesy() {
		return this.titleOfCourtesy;
	}

	

	public Employee addMinor(Employee minor) {
		getMinors().add(minor);
		minor.setChef(this);

		return minor;
	}

	public Employee removeMinor(Employee minor) {
		getMinors().remove(minor);
		minor.setChef(null);

		return minor;
	}
	
	//Orders relatie
	@OneToMany(mappedBy = "employee")
	private Set<Order> orders;
	
	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		if(order.getEmployee() != this){
			order.setEmployee(this);
		}	
		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		if(order.getEmployee() ==this){
			order.setEmployee(null);
		}		
		return order;
	}
	
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Employee)){
			return false;
		}
		return ((Employee)obj).hired.equals(hired) && ((Employee)obj).adres.equals(adres) && ((Employee)obj).firstName.equals(firstName) && ((Employee)obj).lastName.equals(lastName); 
	}
	
	@Override
	public int hashCode(){
		return hired.hashCode() + adres.hashCode() + firstName.hashCode() + lastName.hashCode();
	}

}