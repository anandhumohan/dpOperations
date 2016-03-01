package com.chaipoint.deliverypartner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.chaipoint.constants.Constants;
import com.chaipoint.helperclasses.DpStatus;
import com.chaipoint.helperclasses.OrderStatus;

public class DpOperations {

	public static Map<String, Queue<String>> DPQueues = new HashMap<String, Queue<String>>();
	public static Map<String, DpStatus> dpStatus = new HashMap<String, DpStatus>();

	public static Map<String, OrderStatus> orderStatus = new HashMap<String, OrderStatus>();

	DpStatus status = null;
	Queue<String> queue = null;

	// will call this funtion after succesful login for setting maps
	// available.

	public String initialOperations(String storeId, String mtfId) {
		String DPId = mtfId;
		// creating two maps when first guy login
		if (!DPQueues.containsKey(storeId)) {
			queue = new LinkedList<String>();
			queue.add(DPId);
			DPQueues.put(storeId, queue);
			status = new DpStatus();
			status.setDpId(DPId);

			dpStatus.put(DPId, status);
		} else {
			if (dpStatus.containsKey(DPId)) {
				queue = DPQueues.get(storeId);
				queue.add(DPId);
				status = dpStatus.get(DPId);

				dpStatus.put(DPId, status);
				DPQueues.put(storeId, queue);

			}
		}

		return DPId;
	}

	public String orderReadyState() {

		return null;
	}

	public String DpAvailbleAtStore(String mtfId, String storeId) {

		String DPId = mtfId;
		queue = DPQueues.get(storeId);
		queue.add(DPId);
		status = dpStatus.get(DPId);
		status.setStatus(Constants.dp_Status_available);
		dpStatus.put(DPId, status);
		DPQueues.put(storeId, queue);

		return Constants.sucsess;
	}

	public String DpOutForDelivery(String mtfId, String storeId) {

		String status = "";
		String DPId = mtfId;
		status = dpStatus.get(DPId);
		status.setStatus(Constants.dp_Status_available);
		dpStatus.put(DPId, status);

		// orderStatus = state;
		// send order status for tracking
		return status;

	}

	public String DpReturnToStore(String mtfId, String storeId) {
		String orderId = "";
		String state = "";
		String DPId = mtfId;
		status = dpStatus.get(DPId);
		status.setStatus(Constants.dp_Status_available);
		dpStatus.put(DPId, status);

		return null;
	}

	// Here here here Here have to write changes happen for a particular order.
	// like new-confirm-ready-dispatch-delivered-cancel

	public ArrayList<String> confirmActionDisplay(String storeId) {

		ArrayList<String> orderList = new ArrayList<String>();
		// getting all orders in the store

		return null;
	}

	public ArrayList<String> readyActionDisplay(String storeId) {

		ArrayList<String> orderList = new ArrayList<String>();
		return orderList;
	}

	public ArrayList<String> readyActionDisplay(String storeId, String mtfId) {

		// get allorders that are assighed to this dp
		return null;
	}

	public ArrayList<String> deliveredActionDisplay(String storeId, String mtfId) {

		// get all orders that he has delivered today.
		return null;
	}

	// for flushing the values after closing
	public String forceFlush() {
		DPQueues.clear();
		dpStatus.clear();
		return "success";
	}

}
