package com.chaipoint.ninja;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.chaipoint.constants.Constants;
import com.chaipoint.dphelper.OrderDetailsDaoImpl;
import com.chaipoint.dppojos.CpOrderAddress;
import com.chaipoint.dppojos.CpOrders;
import com.chaipoint.helperclasses.AddressInfo;
import com.chaipoint.helperclasses.CpOrder;
import com.chaipoint.helperclasses.DpStatus;
import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.helperclasses.OrderStatus;
import com.chaipoint.helperclasses.PaymentDetails;
import com.chaipoint.helperclasses.Pricing;
import com.chaipoint.hibernatehelper.HibernateOperations;

public class NinjaOperations {

	public static Map<String, Queue<String>> DPQueues = new HashMap<String, Queue<String>>();
	public static Map<String, DpStatus> dpStatus = new HashMap<String, DpStatus>();

	public static Map<String, OrderStatus> orderStatus = new HashMap<String, OrderStatus>();
	public static Map<String, String> storeToNinja = new HashMap<String, String>();

	DpStatus status = null;
	Queue<String> queue = null;
	public static Constants constants = new Constants();
	HibernateOperations template = null;
	public static OrderDetailsDaoImpl orderDetailsDaoImpl = new OrderDetailsDaoImpl();

	public Map<String, ArrayList<OrderDetails>> getOrderDetails(int storeId, String status) {

		Map<String, ArrayList<OrderDetails>> orderDetails = new HashMap<String, ArrayList<OrderDetails>>();
		ArrayList<OrderDetails> orderList = getOrderList(storeId, status);
		orderDetails.put(status, orderList);

		// ArrayList<String> statusList = getAllStatus();

		// for (String status : statusList) {
		// ArrayList<OrderDetails> orderList = getOrderList(storeId, status);
		// orderDetails.put(status, orderList);
		// }
		return orderDetails;
	}

	private ArrayList<OrderDetails> getOrderList(int storeId, String status) {

		ArrayList<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
		ArrayList<Integer> orderIdList = orderDetailsDaoImpl.getAllOrderId(storeId);

		for (int orderList : orderIdList) {
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setOrderId(orderList);
			orderDetails.setStoreName(orderDetailsDaoImpl.getstoreName(storeId));
			orderDetails.setOrderDetails(orderDetailsDaoImpl.getOrderDeatils(orderList));

			CpOrders orders = orderDetailsDaoImpl.getOrderdetails(orderList);
			PaymentDetails paymentDetails = new PaymentDetails();
			paymentDetails.setChannel(orders.getChannel());
			paymentDetails.setPaymentType(orders.getPaymentMethod());
			orderDetails.setPaymentDetails(paymentDetails);
orderDetails.setDeliveredBy(orders.getDeliveryBoy());
			Pricing pricing = new Pricing();
			pricing.setCouponApplied(orders.getCouponCode());
			pricing.setDiscountAmount(orders.getDiscount());
			pricing.setTotalPrice(orders.getTotalAmount());
			pricing.setFinalPayableCost(orders.getTotalAmount());
			pricing.setDeliveryCharges(orders.getDeliveryCharge());
			orderDetails.setPricing(pricing);
			// finished till here working properly
			// orderDetails.setCustomerDetails(orderDetailsDaoImpl.getAddressdetails(orderList));
			// orderDetails.setCustomerDetails(orderDetailsDaoImpl.getAddressdetailsFromId(orders.getCustomerId()));
			CpOrderAddress orderAddress = orderDetailsDaoImpl.getAddressdetails(orderList);
			AddressInfo addressInfo = new AddressInfo();
			addressInfo.setName(orderAddress.getName());
			addressInfo.setAddress_line_first(orderAddress.getAddress());
			addressInfo.setLandmark(orderAddress.getLandmark());

			addressInfo.setPhone(orderDetailsDaoImpl.getPhoneNumber(orderAddress.getId()));
			orderDetails.setCustomerDetails(addressInfo);
			orderDetailsList.add(orderDetails);

		}

		return orderDetailsList;
	}

	// getting all available at store dps
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

	// count of each status of current date
	public Map<String, Integer> getAllCounts() {

		ArrayList<String> statusList = getAllStatus();
		Map<String, Integer> stateCount = new HashMap<String, Integer>();
		for (String status : statusList) {

			Criteria criteria = getHibernatetemplate().getSession().createCriteria(CpOrders.class);
			criteria.add(Restrictions.eq("status", status));
			criteria.setProjection(Projections.property("id"));
			ArrayList<Integer> count = (ArrayList<Integer>) getHibernatetemplate().get(criteria);

			stateCount.put(status, count.size());
		}
		return stateCount;

	}

	public ArrayList<String> getAllStatus() {
		ArrayList<String> statusList = new ArrayList<String>();

		FileInputStream fis = null;
		BufferedReader reader = null;

		try {
			fis = new FileInputStream("config");
			reader = new BufferedReader(new InputStreamReader(fis));

			System.out.println("Reading File line by line using BufferedReader");

			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				statusList.add(line);
				line = reader.readLine();
			}

		} catch (FileNotFoundException ex) {

		} catch (IOException ex) {

		} finally {
			try {
				reader.close();
				fis.close();
			} catch (IOException ex) {

			}
		}

		return statusList;
	}

	// save the order cancel reason
	public String saveCancelreason(int orderId, String reason) {
		String code = "";
		CpOrders cpOrders = new CpOrders();
		Criteria criteria = getHibernatetemplate().getSession().createCriteria(CpOrders.class);
		criteria.add(Restrictions.eq("id", orderId));
		ArrayList<CpOrders> count = (ArrayList<CpOrders>) getHibernatetemplate().get(criteria);
		cpOrders = count.get(0);
		cpOrders.setCancelReason(reason);

		if (Constants.success.equals(getHibernatetemplate().update(cpOrders))) {
			code = Constants.success;
		}

		return code;

	}

	// change the status of the order new to confirmed in db
	public String updateOrderStatus(int OrderId, String status) {
		String code = "";
		CpOrders cpOrders = new CpOrders();
		Criteria criteria = getHibernatetemplate().getSession().createCriteria(CpOrders.class);
		criteria.add(Restrictions.eq("id", OrderId));
		ArrayList<CpOrders> count = (ArrayList<CpOrders>) getHibernatetemplate().get(criteria);
		cpOrders = count.get(0);
		cpOrders.setStatus(status);

		if (Constants.success.equals(getHibernatetemplate().update(cpOrders))) {
			code = Constants.success;
		}

		return code;
	}

	public HibernateOperations getHibernatetemplate() {
		if (template == null) {
			template = new HibernateOperations();
		}
		return template;
	}
	/*
	 * public HibernateTemplate getHibernatetemplate() { if (template == null) {
	 * template = new HibernateTemplate(); } return template; }
	 */

}
