package com.chaipoint.helperclasses;

public class RootUpdate {
	private int orderId;
	private String status;
	private int storeId;
	private String mtfId;

	public String getMtfId() {
		return mtfId;
	}

	public void setMtfId(String mtfId) {
		this.mtfId = mtfId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

}
