package com.chaipoint.supervisor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.chaipoint.constants.Constants;
import com.chaipoint.dphelper.OrderDetailsDaoImpl;
import com.chaipoint.dppojos.CpOrders;
import com.chaipoint.helperclasses.AddressInfo;
import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.helperclasses.PaymentDetails;
import com.chaipoint.helperclasses.Pricing;
import com.chaipoint.hibernatehelper.HibernateTemplate;

public class SuperVisorOperations {
	HibernateTemplate template = null;
	public static Constants constants = new Constants();
	public static OrderDetailsDaoImpl orderDetailsDaoImpl = new OrderDetailsDaoImpl();
	
	
public Map<String, ArrayList<OrderDetails>> getOrderDetails(ArrayList<String> orderList, String status) {
		
		Map<String, ArrayList<OrderDetails>> orderDetails = new HashMap<String, ArrayList<OrderDetails>>();
		ArrayList<OrderDetails> listOfOrders = getOrderList(orderList, status);
		orderDetails.put(status, listOfOrders);

		return orderDetails;

		
	}


private ArrayList<OrderDetails> getOrderList(ArrayList<String> storeIds, String status) {
	
	ArrayList<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
	ArrayList<Integer> orderIdList = orderDetailsDaoImpl.getAllOrderIds(storeIds);
	
	for(int orderList : orderIdList ){
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setOrderId(orderList);

		orderDetails.setOrderDetails(orderDetailsDaoImpl.getOrderDeatils(orderList));
		//orderDetails.setCustomerDetails(orderDetailsDaoImpl.getAddressdetails(orderList));
		CpOrders orders = orderDetailsDaoImpl.getOrderdetails(orderList);
		orderDetails.setStoreName(orderDetailsDaoImpl.getstoreName(orders.getStoreId()));
		PaymentDetails paymentDetails = new PaymentDetails();
		paymentDetails.setChannel(orders.getChannel());
		paymentDetails.setPaymentType(orders.getPaymentMethod());
		orderDetails.setPaymentDetails(paymentDetails);
		
		Pricing pricing = new Pricing();
		pricing.setCouponApplied(orders.getCouponCode());
		pricing.setDiscountAmount(orders.getDiscount());
		pricing.setTotalPrice(orders.getTotalAmount());
		pricing.setFinalPayableCost(orders.getTotalAmount());
		pricing.setDeliveryCharges(orders.getDeliveryCharge());
		orderDetails.setPricing(pricing);
		
		orderDetailsList.add(orderDetails);
		
	}

	return orderDetailsList;
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

	

	public HibernateTemplate getHibernatetemplate() {
		if (template == null) {
			template = new HibernateTemplate();
		}
		return template;
	}

}