package com.chaipoint.supervisor;

import java.util.ArrayList;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.chaipoint.constants.Constants;
import com.chaipoint.dphelper.OrderDetailsDaoImpl;
import com.chaipoint.helperclasses.AddressInfo;
import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.hibernatehelper.HibernateTemplate;

public class SuperVisorOperations {
	HibernateTemplate template = null;
	public static Constants constants = new Constants();
	public static OrderDetailsDaoImpl orderDetailsDaoImpl = new OrderDetailsDaoImpl();

	public Map<String, ArrayList<OrderDetails>> getOrderStatus(ArrayList<String> storeList,
			Map<String, ArrayList<OrderDetails>> orderDetails) {

		/*
		 * 
		 * Map<String, ArrayList<String>> storeOrdersMap = new HashMap<String,
		 * ArrayList<String>>(); for (String storeId : storeList) {
		 * ArrayList<String> orderIds = new ArrayList<String>(); orderIds =
		 * orderDetailsDaoImpl.getOrderIdsfromStoreId(storeId);
		 * storeOrdersMap.put(storeId, orderIds); }
		 */

		ArrayList<OrderDetails> newOrderList = getOrderList(storeList, constants.Order_Status_new);
		orderDetails.put(constants.Order_Status_new, newOrderList);
		ArrayList<OrderDetails> confirmOrderList = getOrderList(storeList, constants.Order_Status_confirmed);
		orderDetails.put(constants.Order_Status_confirmed, confirmOrderList);
		ArrayList<OrderDetails> readyOrderList = getOrderList(storeList, constants.Order_Status_ready);
		orderDetails.put(constants.Order_Status_ready, readyOrderList);
		ArrayList<OrderDetails> dispatchedOrderList = getOrderList(storeList, constants.Order_Status_dispatched);
		orderDetails.put(constants.Order_Status_dispatched, dispatchedOrderList);
		ArrayList<OrderDetails> deliveredOrderList = getOrderList(storeList, constants.Order_Status_delivered);
		orderDetails.put(constants.Order_Status_delivered, deliveredOrderList);
		ArrayList<OrderDetails> cancelledOrderList = getOrderList(storeList, constants.Order_Status_cancelled);
		orderDetails.put(constants.Order_Status_cancelled, deliveredOrderList);
		return orderDetails;
	}

	private ArrayList<OrderDetails> getOrderList(ArrayList<String> storeList, String status) {

		ArrayList<OrderDetails> orderList = new ArrayList<OrderDetails>();
		for (String store : storeList) {
			OrderDetails orderDetails = new OrderDetails();
			// AddressInfo addressInfo =
			// orderDetailsDaoImpl.getCustomerDeliveryAddress(orderId);
			Criteria criteria = getHibernatetemplate().getSession().createCriteria(OrderDetails.class);
			criteria.add(Restrictions.eq("storeId", store));
			criteria.add(Restrictions.eq("status", status));
			orderDetails = (OrderDetails) template.get(criteria);
			orderList.add(orderDetails);
		}
		return orderList;
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
	//		orderDetails.setAddress_info(addressInfo);
	//		orderDetails.setOrder_details(orderDetailsDaoImpl.getItemdetails(orderId));
	//		orderDetails.setPrice_details(orderDetailsDaoImpl.getPriceDetails(orderId));

			orderDetailsList.add(orderDetails);
		}

		return orderDetailsList;
	}

	public HibernateTemplate getHibernatetemplate() {
		if (template == null) {
			template = new HibernateTemplate();
		}
		return template;
	}

}