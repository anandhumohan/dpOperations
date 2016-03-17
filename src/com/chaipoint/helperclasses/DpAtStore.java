package com.chaipoint.helperclasses;

import java.util.Map;

public class DpAtStore {

	private Map<String, String> dpIdNames;
	private String message;

	public Map<String, String> getDpIdNames() {
		return dpIdNames;
	}

	public void setDpIdNames(Map<String, String> dpIdNames) {
		this.dpIdNames = dpIdNames;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
