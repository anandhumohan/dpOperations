package com.chaipoint.helperclasses;

import java.util.ArrayList;

public class LoginCredentials {
	private ArrayList<Integer> storeIds;
	private String mtfId;
	private String password;
	private String role;
	private String deviceId;
	

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	

	public ArrayList<Integer> getStoreIds() {
		return storeIds;
	}

	public void setStoreIds(ArrayList<Integer> storeIds) {
		this.storeIds = storeIds;
	}

	public String getMtfId() {
		return mtfId;
	}

	public void setMtfId(String mtfId) {
		this.mtfId = mtfId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
