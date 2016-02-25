package com.chaipoint.deliverypartner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.chaipoint.helperclasses.DpStatus;
import com.chaipoint.helperclasses.OrderStatus;

public class DpOperations {

	public static Map<String, Queue<String>> DPQueues = new HashMap<String, Queue<String>>();
	public static Map<String, DpStatus> dpStatus = new HashMap<String, DpStatus>();

	public static Map<String, OrderStatus> orderStatus = new HashMap<String, OrderStatus>();
	public static Map<String, String> storeToNinja = new HashMap<String, String>();

	DpStatus status = null;
	Queue<String> queue = null;

	// will call this funtion when.1 login 2.delivered and 3.when one DP is
	// available.
	public String availbleState(String storeId, String DPId, boolean busy, Date deliveryTime) {

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

			} else {
				status = new DpStatus();
				status.setDpId(DPId);
				dpStatus.put(DPId, status);
				queue = DPQueues.get(storeId);
				queue.add(DPId);
				DPQueues.put(storeId, queue);

			}

		}

		return DPId;
	}

	// this method will call when an order is confirmed.
	/*
	 * public String setCofirmOrder(String storeId, ArrayList<String> itemIds) {
	 * 
	 * CategoryCount categoryCount = dpHelper.setCategoryCount(itemIds);
	 * 
	 * String DPId = null; int threshold = 10; if (queue.isEmpty()) {
	 * 
	 * long prepTime = dpHelper.PreparationTime(categoryCount, storeId);
	 * System.out.println("prep" + prepTime); Date expectedPrep = new
	 * TimeHelper().addMinute(new Date(), prepTime);
	 * System.out.println("expectedPrep" + expectedPrep); TimeCalculator cal =
	 * dpHelper.minReturnTimeDPId(dpStatus, queue, storeId, DPQueues); Date
	 * returnTime = cal.getExpectedReturnTIme();
	 * 
	 * System.out.println("returnTime" + returnTime); Date addExpcAndThreshold =
	 * new TimeHelper().addMinute(expectedPrep, threshold); // expectedPrep =
	 * new TimeHelper().addMinute(expectedPrep, // threshold); if
	 * (returnTime.getTime() < addExpcAndThreshold.getTime()) { // possible dpId
	 * DPId = cal.getDPId(); System.out.println("possible DP" + DPId);
	 * 
	 * } else { System.out.println("reached here"); // give order details to
	 * thirdparty-using below class we can // request thirdparty
	 * 
	 * }
	 * 
	 * } return DPId; }
	 */
	// when click on atStore put into queue
	public String atStore(String DPId, String state) {
		String storeId = "";
		queue = DPQueues.get(storeId);
		queue.add(DPId);
		status = dpStatus.get(DPId);
		status.setStatus(state);
		dpStatus.put(DPId, status);
		DPQueues.put(storeId, queue);

		return null;
	}

	public String orderDelivered(String orderId, String dPId, String state) {
		String status = "";
		if (orderId != null && state == "DELIVERED") {
			dpStatus.get(dPId).setStatus("AVAILABLE");
			// send order status for tracking
			status = "OK";
		}
		return status;
	}

	public String outForDelivery(String DPId, String OrderId, String state) {

		String status = "";
		// orderStatus = state;
		// send order status for tracking
		return status;

	}

	// role id [DP, Ninja, Supervisor]

	// for flushing the values after closing
	public String forceFlush() {
		DPQueues.clear();
		dpStatus.clear();
		return "success";
	}

}
