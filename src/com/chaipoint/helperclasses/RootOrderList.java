package com.chaipoint.helperclasses;

import java.util.ArrayList;
import java.util.Map;

public class RootOrderList {
	private Map<String, ArrayList<OrderDetails>> orderList;
	private String message;
	// private Map<String, Long> orderCount;

	public Map<String, ArrayList<OrderDetails>> getOrderList() {
		return orderList;
	}

	public void setOrderList(Map<String, ArrayList<OrderDetails>> orderList) {
		this.orderList = orderList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/*
	 * public Map<String, Long> getOrderCount() { return orderCount; }
	 * 
	 * public void setOrderCount(Map<String, Long> orderCount) { this.orderCount
	 * = orderCount; }
	 */
}
