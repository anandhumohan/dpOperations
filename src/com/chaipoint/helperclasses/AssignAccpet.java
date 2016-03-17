package com.chaipoint.helperclasses;

import java.util.ArrayList;

public class AssignAccpet {
	private ArrayList<OrderDetails> accepted;
	private ArrayList<OrderDetails> assigned;

	public ArrayList<OrderDetails> getAccepted() {
		return accepted;
	}

	public void setAccepted(ArrayList<OrderDetails> accepted) {
		this.accepted = accepted;
	}

	public ArrayList<OrderDetails> getAssigned() {
		return assigned;
	}

	public void setAssigned(ArrayList<OrderDetails> assigned) {
		this.assigned = assigned;
	}

}
