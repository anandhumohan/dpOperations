package com.chaipoint.deliverypartner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.chaipoint.constants.Constants;
import com.chaipoint.deliveryappapis.HelperAPI;
import com.chaipoint.dppojos.CpOrders;
import com.chaipoint.helperclasses.DpStatus;
import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.helperclasses.OrderStatus;
import com.chaipoint.hibernatehelper.HibernateOperations;
import com.chaipoint.ninja.NinjaOperations;

public class DpOperations {

	public static Map<Integer, Queue<String>> DPQueues = new HashMap<Integer, Queue<String>>();
	public static Map<String, DpStatus> dpStatus = new HashMap<String, DpStatus>();
	public static Map<Integer, ArrayList<String>> storeDpList = new HashMap<Integer, ArrayList<String>>();
	// public static Map<String, ArrayList<DpStatus>> stoteDpStatusMap = new
	// HashMap<String, ArrayList<DpStatus>>();

	// public static Map<String, OrderStatus> orderStatus = new HashMap<String,
	// OrderStatus>();
	HibernateOperations template = null;

	DpStatus status = null;
	Queue<String> queue = null;
	ArrayList<String> dpStores = null;

	// will call this funtion after succesful login for setting maps
	// available.

	public String initialOperations(int storeId, String mtfId) {
		String DPId = mtfId;
		// creating two maps when first guy login
		if (!storeDpList.containsKey(storeId)) {

			status = new DpStatus();
			status.setStatus(Constants.dp_Status_returning_to_store);
			status.setDpId(DPId);
			dpStatus.put(DPId, status);

			queue = new LinkedList<String>();
			DPQueues.put(storeId, queue);

			dpStores = new ArrayList<String>();
			dpStores.add(DPId);
			storeDpList.put(storeId, dpStores);

		} else {
			status = new DpStatus();
			status.setStatus(Constants.dp_Status_returning_to_store);
			status.setDpId(DPId);
			dpStatus.put(DPId, status);

			// queue = DPQueues.get(storeId);
			// queue.add(mtfId);
			// DPQueues.put(storeId, queue);

			dpStores = storeDpList.get(storeId);
			dpStores.add(DPId);
			storeDpList.put(storeId, dpStores);

		}

		return Constants.success;
	}

	public String DpAvailbleAtStore(int storeId, String DPId) {

		status = dpStatus.get(DPId);
		status.setStatus(Constants.dp_Status_available);
		dpStatus.put(DPId, status);
		// check for null check

		queue = DPQueues.get(storeId);
		queue.add(DPId);
		DPQueues.put(storeId, queue);

		return Constants.success;
	}

	// get all Order details of dispatched items of the store
	public Map<String, ArrayList<OrderDetails>> getAllDispatchedOrder(int storeId) {

		Map<String, ArrayList<OrderDetails>> orderDetails = new NinjaOperations().getOrderDetails(storeId,
				Constants.Order_Status_dispatched);
		return orderDetails;
	}

	public Map<String, ArrayList<OrderDetails>> getAllDeliveredOrders(int storeId, String mtfId) {
		Map<String, ArrayList<OrderDetails>> orderDetails = new NinjaOperations().getOrderDetails(storeId,
				Constants.Order_Status_dispatched);
		for (ArrayList<OrderDetails> details : orderDetails.values()) {
			for (OrderDetails order : details) {
				/*
				 * if (!order.getDeliveredBy().equals(mtfId)) {
				 * details.remove(order); }
				 */
			}

		}
		return orderDetails;
	}

	public String DpOutForDelivery(String mtfId, String storeId) {

		String status = "";
		String DPId = mtfId;
		// status = dpStatus.get(DPId);
		// status.setStatus(Constants.dp_Status_available);
		// dpStatus.put(DPId, status);

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

	// for flushing the values after closing
	public String forceFlush() {
		DPQueues.clear();
		dpStatus.clear();
		storeDpList.clear();
		return "success";
	}

	public Map<String, ArrayList<OrderDetails>> getAllAssighedOrders(int storeId, String mtfId) {

		Map<String, ArrayList<OrderDetails>> assigned = new HashMap<String, ArrayList<OrderDetails>>();
		assigned.put("Assigned", dpStatus.get(mtfId).getOrderDetailsAssigned());
		return assigned;
	}

	public String acceptedOrders(int storeId, String mtfId) {
		int count = dpStatus.get(mtfId).getAssignedCount();
		String msg = "";
		if (count == 1) {
			dpStatus.get(mtfId).setAssignedCount(0);
			dpStatus.get(mtfId).setStatus(Constants.dp_Status_out_for_delivery);
			msg = "Accepted";
		} else {
			dpStatus.get(mtfId).setAssignedCount(count - 1);
			msg = "Add more";
		}
		return msg;
	}

	public Map<String, ArrayList<OrderDetails>> deliveredOrders(int storeId, String mtfId) {
		int OrderId = 0;
		String msg = "";
		int count = dpStatus.get(mtfId).getOrderDetailsAssigned().size();
		String status = new NinjaOperations().updateOrderStatus(OrderId, Constants.Order_Status_delivered);
		if (status.equalsIgnoreCase(Constants.success) && count == 1) {
			dpStatus.get(mtfId).setAssignedCount(0);
			dpStatus.get(mtfId).setStatus(Constants.dp_Status_out_for_delivery);
			msg = "Delivered";
		} else {
			dpStatus.get(mtfId).setAssignedCount(count - 1);
			msg = "Add more";
		}

		return null;
	}

	public String getMaxPriorityDpname(int storeId) {

		return null;
	}

	public ArrayList<String> getAllDpAtStore(int storeId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Long> getAllDpCounts(int storeId, String mtfId) {
		String status = new HelperAPI().getAllIdAndNames();
		Map<String, Long> dpScreenCount = new HashMap<String, Long>();

		
		// get orders assighed to him

		Criteria criteria = getTemplate().getSession().createCriteria(CpOrders.class);
		criteria.add(Restrictions.eq("status", "Dispatched"));
		criteria.add(Restrictions.eq("storeId", storeId));
		// criteria.setProjection(Projections.property("id"));
		criteria.setProjection(Projections.rowCount());

		// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd
		// hh:mm:ss");
		// Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		System.out.println("ennathe date" + date);

		Date minDate = null;
		try {
			minDate = formatter.parse(formatter.format(date));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Date maxDate = new Date(minDate.getTime() + TimeUnit.DAYS.toMillis(1));

		System.out.println("minimum date" + minDate);
		System.out.println("minimum date" + maxDate);

		criteria.add(Restrictions.ge("createdDate", minDate));

		criteria.add(Restrictions.lt("createdDate", maxDate));

		ArrayList<Long> count = (ArrayList<Long>) getTemplate().get(criteria);
		
		dpScreenCount.put(Constants.Order_Status_dispatched, count.get(0));
		
	//	if(dpStatus.get(mtfId).getOrderDetailsAssigned() == null){
			dpScreenCount.put("Assigned", (long) 0);
	//	}
	//	else{
	//		dpScreenCount.put("Assigned", (long)dpStatus.get(mtfId).getOrderDetailsAssigned().size());
	//	}
		
	//	if(dpStatus.get(mtfId).getDeliveredorders() == null){
			dpScreenCount.put("Delivered", (long) 0);
	//	}
	//	else{
	//		dpScreenCount.put("Assigned", (long)dpStatus.get(mtfId).getDeliveredorders().size());
	//	}
		
		
		
		
		
		
		
		

		return dpScreenCount;
	}

	private HibernateOperations getTemplate() {

		if (template == null) {
			template = new HibernateOperations();
		}
		return template;
	}

}
