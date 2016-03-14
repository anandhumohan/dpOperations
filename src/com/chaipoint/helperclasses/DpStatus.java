package com.chaipoint.helperclasses;

import java.util.ArrayList;
import java.util.Date;

public class DpStatus {
	private String dpName;
	private String storeId;
	private ArrayList<String> orderAssigned;
	private ArrayList<OrderDetails> orderDetailsAssigned;
	private int assignedCount;
	private ArrayList<OrderDetails> deliveredorders;
	private String dpId;
	private String status;
	private Date lastAtStorePressed;

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public int getAssignedCount() {
		return assignedCount;
	}

	public void setAssignedCount(int assignedCount) {
		this.assignedCount = assignedCount;
	}

	public ArrayList<String> getOrderAssigned() {
		return orderAssigned;
	}

	public void setOrderAssigned(ArrayList<String> orderAssigned) {
		this.orderAssigned = orderAssigned;
	}

	public ArrayList<OrderDetails> getDeliveredorders() {
		return deliveredorders;
	}

	public void setDeliveredorders(ArrayList<OrderDetails> deliveredorders) {
		this.deliveredorders = deliveredorders;
	}

	public ArrayList<OrderDetails> getOrderDetailsAssigned() {
		return orderDetailsAssigned;
	}

	public void setOrderDetailsAssigned(ArrayList<OrderDetails> orderDetailsAssigned) {
		this.orderDetailsAssigned = orderDetailsAssigned;
	}

	public String getDpName() {
		return dpName;
	}

	public void setDpName(String dpName) {
		this.dpName = dpName;
	}

	public String getDpId() {
		return dpId;
	}

	public void setDpId(String dpId) {
		this.dpId = dpId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastAtStorePressed() {
		return lastAtStorePressed;
	}

	public void setLastAtStorePressed(Date lastAtStorePressed) {
		this.lastAtStorePressed = lastAtStorePressed;
	}

}
