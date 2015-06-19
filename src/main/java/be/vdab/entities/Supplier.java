package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import be.vdab.valueobjects.Adres;


@Entity
@Table(name = "suppliers")
@NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s")
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String contactName;

	private String contactTitle;

	private String fax;

	private String name;

	private String phone;
	
	@Embedded
	private Adres adres;

	public Supplier() {
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

	// relatie naar countries
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "countryid")
	private Country country;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		if (this.country != null && country.getSuppliers().contains(this)) {
			this.country.removeSupplier(this);
		}
		this.country = country;
		if (country != null && !country.getSuppliers().contains(this)) {
			country.addSupplier(this);
		}
	}

	// bi-directionele many-to-one relatie naar Product
	@OneToMany(mappedBy = "supplier")
	private Set<Product> products;

	public Set<Product> getProducts() {
		return Collections.unmodifiableSet(products);
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		if (product.getSupplier() != null) {
			product.setSupplier(this);
		}
		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		if (product.getSupplier() == null) {
			product.setSupplier(null);
		}
		return product;
	}
}