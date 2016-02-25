package com.chaipoint.ninja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.chaipoint.constants.Constants;
import com.chaipoint.dphelper.OrderDetailsDaoImpl;
import com.chaipoint.helperclasses.AddressInfo;
//import com.chaipoint.deliverypartner.DPselector;
import com.chaipoint.helperclasses.DpStatus;
import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.helperclasses.OrderStatus;
import com.chaipoint.hibernatehelper.HibernateTemplate;

//import com.chaipoint.deliverypartner.DPselector;

public class NinjaOperations {

	public static Map<String, Queue<String>> DPQueues = new HashMap<String, Queue<String>>();
	public static Map<String, DpStatus> dpStatus = new HashMap<String, DpStatus>();

	public static Map<String, OrderStatus> orderStatus = new HashMap<String, OrderStatus>();
	public static Map<String, String> storeToNinja = new HashMap<String, String>();

	DpStatus status = null;
	Queue<String> queue = null;
	public static Constants constants = new Constants();
	HibernateTemplate template = null;
	public static OrderDetailsDaoImpl orderDetailsDaoImpl = new OrderDetailsDaoImpl();

	public OrderDetails newState(String orderId, String state) {

		String ordeDetails = "";
		OrderDetails details = new OrderDetails();
		if (!orderId.equals(null) && state.equals("NEW")) {
			// get order details

		}

		return details;
	}

	public Map<String, ArrayList<OrderDetails>> storeOrderDetails(String storeId) {

		/*
		 * 
		 * Map<String, ArrayList<String>> storeOrdersMap = new HashMap<String,
		 * ArrayList<String>>(); for (String storeId : storeList) {
		 * ArrayList<String> orderIds = new ArrayList<String>(); orderIds =
		 * orderDetailsDaoImpl.getOrderIdsfromStoreId(storeId);
		 * storeOrdersMap.put(storeId, orderIds); }
		 */

		Map<String, ArrayList<OrderDetails>> orderDetails = new HashMap<String, ArrayList<OrderDetails>>();

		ArrayList<OrderDetails> newOrderList = getOrderList(storeId, constants.Order_Status_new);
		orderDetails.put(constants.Order_Status_new, newOrderList);
		ArrayList<OrderDetails> confirmOrderList = getOrderList(storeId, constants.Order_Status_confirmed);
		orderDetails.put(constants.Order_Status_confirmed, confirmOrderList);
		ArrayList<OrderDetails> readyOrderList = getOrderList(storeId, constants.Order_Status_ready);
		orderDetails.put(constants.Order_Status_ready, readyOrderList);
		ArrayList<OrderDetails> dispatchedOrderList = getOrderList(storeId, constants.Order_Status_dispatched);
		orderDetails.put(constants.Order_Status_dispatched, dispatchedOrderList);
		ArrayList<OrderDetails> deliveredOrderList = getOrderList(storeId, constants.Order_Status_delivered);
		orderDetails.put(constants.Order_Status_delivered, deliveredOrderList);
		ArrayList<OrderDetails> cancelledOrderList = getOrderList(storeId, constants.Order_Status_cancelled);
		orderDetails.put(constants.Order_Status_cancelled, deliveredOrderList);
		return orderDetails;
	}

	private ArrayList<OrderDetails> getOrderList(String storeId, String status) {

		ArrayList<OrderDetails> orderList = new ArrayList<OrderDetails>();

		OrderDetails orderDetails = new OrderDetails();
		// AddressInfo addressInfo =
		// orderDetailsDaoImpl.getCustomerDeliveryAddress(orderId);
		Criteria criteria = getHibernatetemplate().getSession().createCriteria(OrderDetails.class);
		criteria.add(Restrictions.eq("storeId", storeId));
		criteria.add(Restrictions.eq("status", status));
		orderDetails = (OrderDetails) template.get(criteria);
		orderList.add(orderDetails);

		return orderList;
	}

	public HibernateTemplate getHibernatetemplate() {
		if (template == null) {
			template = new HibernateTemplate();
		}
		return template;
	}

	public ArrayList<String> getOrderCounts(ArrayList<String> storeList, String orderStatus) {

		// change OrderDetails to cpos order class
		ArrayList<String> orderList = new ArrayList<String>();
		for (String storeId : storeList) {
			Criteria criteria = getHibernatetemplate().getSession().createCriteria(OrderDetails.class);
			criteria.add(Restrictions.eq("status", orderStatus));
			criteria.add(Restrictions.eq("storeid", storeId));
			criteria.setProjection(Projections.property("orderId"));
			ArrayList<String> orderDetails = (ArrayList<String>) getHibernatetemplate().get(criteria);
			orderList.addAll(orderDetails);
		}
		return orderList;
	}

	public ArrayList<OrderDetails> getOrderDetails(ArrayList<String> orderList) {

		ArrayList<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
		for (String orderId : orderList) {
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setOrderId(orderId);
			AddressInfo addressInfo = orderDetailsDaoImpl.getCustomerDeliveryAddress(orderId);
			orderDetails.setAddress_info(addressInfo);
			orderDetails.setOrder_details(orderDetailsDaoImpl.getItemdetails(orderId));
			orderDetails.setPrice_details(orderDetailsDaoImpl.getPriceDetails(orderId));

			orderDetailsList.add(orderDetails);
		}

		return orderDetailsList;
	}

	/*
	 * public String confirmState(String orderId, String state) {
	 * 
	 * String status = ""; if (!orderId.equals(null) &&
	 * state.equals("CONFIRMED")) {
	 * 
	 * // send order status for tracking status = "OK"; } return status; }
	 * 
	 * public String readyState(String orderId, String state) {
	 * 
	 * String storeId = ""; String status = ""; if (!orderId.equals(null) &&
	 * state.equals("READY")) { String dpId = getAtStoreDP(storeId); if (dpId ==
	 * null) { ArrayList<String> availDp = new ArrayList<String>(); availDp =
	 * getAvailableDp(storeId); // push notification to all available availDp
	 * status = "OK"; } else { // give order details to DpId }
	 * 
	 * // send order status
	 * 
	 * } return status; }
	 * 
	 * public String reassignState(String orderId, String state, String oldDPId,
	 * String newDPId) {
	 * 
	 * String status = ""; if (!orderId.equals(null) &&
	 * state.equals("REASSIGN")) {
	 * 
	 * // Send order status for tracking, Generate alert for stakeholders status
	 * = "OK"; } return status; }
	 * 
	 * public String transferState(String orderId, String state, String
	 * newStoreId, String transferReason) {
	 * 
	 * String status = ""; if (!orderId.equals(null) &&
	 * state.equals("TRANSFER")) { // Send order status for tracking, Generate
	 * alert for stakeholders status = "OK"; } return status; }
	 * 
	 * public String cancelState(String orderId, String state, String
	 * cancelReason) {
	 * 
	 * String status = ""; if (!orderId.equals(null) &&
	 * state.equals("CANCELLED")) { // Send order status for tracking, Generate
	 * alert for stakeholders status = "OK"; } return status; }
	 * 
	 * 
	 */

	public String getAtStoreDP(String storeId) {

		String dpId = "";
		queue = DPQueues.get(storeId);
		dpId = queue.poll();
		return dpId;

	}

	public ArrayList<String> getAvailableDp(String storeId) {

		ArrayList<String> dps = new ArrayList<>(DPQueues.get(storeId));
		ArrayList<String> availDp = new ArrayList<>();
		for (String status : dps) {
			if (dpStatus.get(status).getStatus() == "AVAILABLE") {
				availDp.add(status);

			}
		}

		return availDp;
	}

	public String saveCancelreason(String orderId, String reason) {
		Criteria criteria = getHibernatetemplate().getSession().createCriteria(OrderDetails.class);
		criteria.add(Restrictions.eq("orderId", orderId));
		ArrayList<OrderDetails> orderDetails = (ArrayList<OrderDetails>) template.get(criteria);
		// write reason to cpos
		orderDetails.get(0);

		return reason;

	}

	public ArrayList<String> getAllTrasferStores(String storeId) {
		ArrayList<String> storeIds = new ArrayList<String>();
		// get All Store transfer store ids
		return null;
	}

	public String transferOrder(String storeId, String orderId) {
		// Sending order to cpos

		return null;
	}

	public String updateOrderStatus(String orderId) {

		// change the status of the order new to confirmed in db
		return null;
	}

}
