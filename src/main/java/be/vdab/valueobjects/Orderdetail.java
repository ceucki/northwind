package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;

import be.vdab.entities.Product;

@Embeddable
@NamedEntityGraph(name = "Orderdetail.metProduct", attributeNodes = @NamedAttributeNode("product"))
public class Orderdetail implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private BigDecimal unitPrice;
	private int quantity;
	private BigDecimal discount;

	protected Orderdetail() {
	};	

	public Orderdetail(int orderId, int productId, BigDecimal unitPrice,
			int quantity, BigDecimal discount) {		
		
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.discount = discount;
	}
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public BigDecimal getDiscount() {
		return discount;
	}
	
	
//relatie naar producten
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="productId")
	private Product product;

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}
	
	

	public BigDecimal getValue() {		
		return getUnitPrice().multiply(new BigDecimal(getQuantity()));
	}
	
	

		
	
}
