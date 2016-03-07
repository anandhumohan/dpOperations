package com.chaipoint.dppojos;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "cp_order_products")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class CpOrderProduct {
	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "ORDER_ID")
	private int orderId;

	@Column(name = "PRODUCT_ID")
	private int productId;

	@Column(name = "QTY")
	private int qty;

	@Column(name = "COST")
	private double cost;

	@Column(name = "TOTAL_PRODUCT_COST")
	private double total_product_cost;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getTotal_product_cost() {
		return total_product_cost;
	}

	public void setTotal_product_cost(double total_product_cost) {
		this.total_product_cost = total_product_cost;
	}

}
