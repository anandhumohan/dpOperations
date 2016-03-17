package com.chaipoint.helperclasses;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class DpStatus {
	private String dpName;
	private String storeId;
	private ArrayList<String> orderAssigned;
	private LinkedList<OrderDetails> orderDetailsAssigned;
	private LinkedList<OrderDetails> orderDetailsAccepted;
	private ArrayList<OrderDetails> dpDelivered;
	
	private int assignedCount = 0;
	private String dpId;
	private String status;
	private Date lastAtStorePressed;

	
	
	public ArrayList<OrderDetails> getDpDelivered() {
		return dpDelivered;
	}

	public void setDpDelivered(ArrayList<OrderDetails> dpDelivered) {
		this.dpDelivered = dpDelivered;
	}

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

	public LinkedList<OrderDetails> getOrderDetailsAssigned() {
		return orderDetailsAssigned;
	}

	public void setOrderDetailsAssigned(LinkedList<OrderDetails> orderDetailsAssigned) {
		this.orderDetailsAssigned = orderDetailsAssigned;
	}

	public LinkedList<OrderDetails> getOrderDetailsAccepted() {
		return orderDetailsAccepted;
	}

	public void setOrderDetailsAccepted(LinkedList<OrderDetails> orderDetailsAccepted) {
		this.orderDetailsAccepted = orderDetailsAccepted;
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
