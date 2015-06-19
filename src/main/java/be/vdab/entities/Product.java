package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private byte discontinued;

	private int inStock;

	private String name;

	private int onOrder;

	private BigDecimal price;

	private String quantityPerUnit;

	private int reorderLevel;

	public int getId() {
		return this.id;
	}

	public byte getDiscontinued() {
		return this.discontinued;
	}

	public int getInStock() {
		return this.inStock;
	}

	public String getName() {
		return this.name;
	}

	public int getOnOrder() {
		return this.onOrder;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public String getQuantityPerUnit() {
		return this.quantityPerUnit;
	}

	public int getReorderLevel() {
		return this.reorderLevel;
	}

	// bi-directionele many-to-one associatie naar Supplier
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "supplierId")
	private Supplier supplier;

	public Supplier getSupplier() {
		return this.supplier;
	}

	public void setSupplier(Supplier supplier) {
		if (this.supplier != null && supplier.getProducts().contains(this)) {
			supplier.removeProduct(this);
		}
		this.supplier = supplier;
		if (supplier != null && !supplier.getProducts().contains(this)) {
			supplier.addProduct(this);
		}
	}

	// bi-directionele many-to-one associatie naar Category
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "categoryId")
	private Category category;

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		if (this.category != null && category.getProducts().contains(this)) {
			category.removeProduct(this);
		}
		this.category = category;
		if (category != null && category.getProducts().contains(this)) {
			category.addProduct(this);
		}
	}

	@Override
	public boolean equals(Object obj){
		if (! (obj instanceof Product)){
			return false;
		}
		return ((Product) obj).supplier.equals(supplier) && ((Product) obj).name.equals(name); 
	}

	@Override
	public int hashCode(){
		return supplier.hashCode() + name.hashCode();
	}

}