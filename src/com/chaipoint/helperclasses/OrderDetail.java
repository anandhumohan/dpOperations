package com.chaipoint.helperclasses;

import java.util.List;

public class OrderDetail {
	private String store_Id;
	private String deliveryDate;
	private List<ItemsDetail> items_details;

	public String getStore_Id() {
		return store_Id;
	}

	public void setStore_Id(String store_Id) {
		this.store_Id = store_Id;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public List<ItemsDetail> getItems_details() {
		return items_details;
	}

	public void setItems_details(List<ItemsDetail> items_details) {
		this.items_details = items_details;
	}

}
