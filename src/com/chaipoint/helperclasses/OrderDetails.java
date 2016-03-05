package com.chaipoint.helperclasses;

import java.util.ArrayList;
import java.util.List;

public class OrderDetails {
	private int orderId;
	private String storeName;
	private String DeliveredBy;
	private AddressInfo customerDetails;
	private ArrayList<ItemsDetails> orderDetails;
	private PaymentDetails paymentDetails;
	private Pricing pricing;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getDeliveredBy() {
		return DeliveredBy;
	}

	public void setDeliveredBy(String deliveredBy) {
		DeliveredBy = deliveredBy;
	}

	public AddressInfo getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(AddressInfo customerDetails) {
		this.customerDetails = customerDetails;
	}

	public ArrayList<ItemsDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(ArrayList<ItemsDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public Pricing getPricing() {
		return pricing;
	}

	public void setPricing(Pricing pricing) {
		this.pricing = pricing;
	}

}
