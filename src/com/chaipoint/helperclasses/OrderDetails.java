package com.chaipoint.helperclasses;

import java.util.ArrayList;
import java.util.List;

public class OrderDetails {
	private String orderId;
	private ArrayList<ItemsDetail> order_details;
	private PriceDetails price_details;
	private AddressInfo address_info;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	

	public ArrayList<ItemsDetail> getOrder_details() {
		return order_details;
	}

	public void setOrder_details(ArrayList<ItemsDetail> order_details) {
		this.order_details = order_details;
	}

	public PriceDetails getPrice_details() {
		return price_details;
	}

	public void setPrice_details(PriceDetails price_details) {
		this.price_details = price_details;
	}

	public AddressInfo getAddress_info() {
		return address_info;
	}

	public void setAddress_info(AddressInfo address_info) {
		this.address_info = address_info;
	}

}
