package com.chaipoint.helperclasses;

import java.util.ArrayList;
import java.util.Map;

public class RootAssign {
	private Map<String, ArrayList<OrderDetails>> orderDetails;

	public Map<String, ArrayList<OrderDetails>> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Map<String, ArrayList<OrderDetails>> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
