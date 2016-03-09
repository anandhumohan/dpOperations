package com.chaipoint.helperclasses;

import java.util.ArrayList;

public class LoginCredentials {
	private ArrayList<Integer> storeId;
	private String mtfId;
	private String password;
	private String roleid;

	public ArrayList<Integer> getStoreId() {
		return storeId;
	}

	public void setStoreId(ArrayList<Integer> storeId) {
		this.storeId = storeId;
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

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

}
