package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import be.vdab.valueobjects.Orderdetail;

@Entity
@Table(name = "orders")
//@NamedEntityGraph(name = "Order.metOrderdetailEnProduct", attributeNodes = @NamedAttributeNode(value = "orderdetails", subgraph = "MetProduct"), subgraphs = @NamedSubgraph(name = "MetManager", attributeNodes = @NamedAttributeNode("product")))
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Temporal(TemporalType.DATE)
	private Date ordered;

	@Temporal(TemporalType.DATE)
	private Date required;

	@Temporal(TemporalType.DATE)
	private Date shipped;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customerid")
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "employeeId")
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "shipperId")
	private Shipper shipper;

	public Shipper getShipper() {
		return shipper;
	}

	public void setShipper(Shipper shipper) {
		if (this.shipper != null && this.shipper.getOrders().contains(this)) {
			this.shipper.removeOrder(this);
		}
		this.shipper = shipper;
		if (shipper != null && !shipper.getOrders().contains(this)) {
			shipper.addOrder(this);
		}
	}

	public Order() {
	}

	public int getId() {
		return this.id;
	}

	public Date getOrdered() {
		return this.ordered;
	}

	public Date getRequired() {
		return this.required;
	}

	public Date getShipped() {
		return this.shipped;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		if (this.customer != null && this.customer.getOrders().contains(this)) {
			this.customer.removeOrder(this);
		}
		this.customer = customer;
		if (customer != null && customer.getOrders().contains(this)) {
			customer.addOrder(this);
		}
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		if (this.employee != null && this.employee.getOrders().contains(this)) {
			this.employee.removeOrder(this);
		}
		this.employee = employee;
		if (employee != null && !employee.getOrders().contains(this)) {
			employee.addOrder(this);
		}

	}

	// Value object orderdetail in order
	@ElementCollection
	@CollectionTable(name = "orderdetails", joinColumns = @JoinColumn(name = "orderId"))
	private Set<Orderdetail> orderdetails;

	public Set<Orderdetail> getOrderdetails() {
		return Collections.unmodifiableSet(orderdetails);
	}

	public void addOrderdetail(Orderdetail orderdetail) {
		orderdetails.add(orderdetail);
	}

	public void removeOrderdetail(Orderdetail orderdetail) {
		orderdetails.remove(orderdetail);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Order)) {
			return false;
		}
		return ((Order) obj).ordered == ordered
				&& ((Order) obj).employee == employee; // TODO equals
	}

	@Override
	public int hashCode() {
		return ordered.hashCode() + employee.hashCode();
	}
	
	public BigDecimal getTotal(){
		BigDecimal total = BigDecimal.ZERO;
		for (Orderdetail orderdetail : orderdetails){
			total = total.add(orderdetail.getValue());
		}
		return total;
	}

}