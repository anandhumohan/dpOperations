package com.chaipoint.dppojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MyJoinClassKey implements Serializable {

	private static final long serialVersionUID = -5L;

	// @Column(name = "product_id")
	private int productId;

	// @Column(name = "comment")
	private int orderId;

	@Column(name = "product_id")
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name = "order_id")
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

}
