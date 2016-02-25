package com.chaipoint.helperclasses;

public class StoreStatus {

	private String storeId;
	private boolean isNinjaAssigned;
	private boolean isDpAssigned;

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public boolean isNinjaAssigned() {
		return isNinjaAssigned;
	}

	public void setNinjaAssigned(boolean isNinjaAssigned) {
		this.isNinjaAssigned = isNinjaAssigned;
	}

	public boolean isDpAssigned() {
		return isDpAssigned;
	}

	public void setDpAssigned(boolean isDpAssigned) {
		this.isDpAssigned = isDpAssigned;
	}

}
