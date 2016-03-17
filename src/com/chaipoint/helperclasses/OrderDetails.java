package com.chaipoint.helperclasses;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class OrderDetails {
	private int orderId;
	private String storeName;
	private int DeliveredBy;
	private String orderTime;
	private String status;
	private String orderStatus;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDeliveredBy() {
		return DeliveredBy;
	}

	public void setDeliveredBy(int deliveredBy) {
		DeliveredBy = deliveredBy;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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
