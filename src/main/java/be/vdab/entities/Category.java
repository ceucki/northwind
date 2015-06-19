package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	private String description;
	private String name;

	@OneToMany(mappedBy = "category")
	private Set<Product> products;

	public Category() {
	}

	public int getId() {
		return this.id;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public Set<Product> getProducts() {
		return Collections.unmodifiableSet(products);
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		if (product.getCategory() != this) {
			product.setCategory(this);
		}
		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		if (product.getCategory() == this) {
			product.setCategory(null);
		}
		return product;
	}

}