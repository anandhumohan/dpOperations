package com.chaipoint.ninja;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.chaipoint.constants.Constants;
import com.chaipoint.deliveryappapis.PushNotificationAPI;
import com.chaipoint.deliverypartner.DpOperations;
import com.chaipoint.dphelper.OrderDetailsDaoImpl;
import com.chaipoint.dppojos.CancelReasons;
import com.chaipoint.dppojos.CoCOrderDetails;
import com.chaipoint.dppojos.CoCOrderDetailsTest;
import com.chaipoint.dppojos.CoCOrderDetailstesting;
import com.chaipoint.dppojos.CpOrderAddress;
import com.chaipoint.dppojos.CpOrders;
import com.chaipoint.dppojos.MyJoinClassKey;
import com.chaipoint.helperclasses.AddressInfo;

import com.chaipoint.helperclasses.DpStatus;
import com.chaipoint.helperclasses.ItemsDetails;
import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.helperclasses.OrderStatus;
import com.chaipoint.helperclasses.PaymentDetails;
import com.chaipoint.helperclasses.Pricing;
import com.chaipoint.hibernatehelper.HibernateOperations;
import com.chaipoint.pushnotification.push;
import com.squareup.okhttp.Response;

public class NinjaOperations {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://54.64.5.133/cpos";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root@897";

	public static Map<String, String> storeToNinja = new HashMap<String, String>();

	public static Map<Integer, String> reasonMap = new HashMap<Integer, String>();

	static {
		HibernateOperations operation = new HibernateOperations();
		Criteria criteria = operation.getSession().createCriteria(CancelReasons.class);
		criteria.add(Restrictions.eq("active", 'Y'));

		ArrayList<CancelReasons> list = (ArrayList<CancelReasons>) operation.get(criteria);

		for (CancelReasons reasons : list) {
			reasonMap.put(reasons.getId(), reasons.getReason());
		}
		System.out.println("kazhijy kazhijy");

	}

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

	private OrderDetails getOrderdetailsByOrderId(int orderId, int storeId) {
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setOrderId(orderId);
		orderDetails.setStoreName(orderDetailsDaoImpl.getstoreName(storeId));
		orderDetails.setOrderDetails(orderDetailsDaoImpl.getOrderDeatils(orderId));

		CpOrders orders = orderDetailsDaoImpl.getOrderdetails(orderId);
		// orderDetails.setOrderTime(orders.getCreatedDate().getTime());
		PaymentDetails paymentDetails = new PaymentDetails();
		// paymentDetails.setChannel(orders.getChannel());
		paymentDetails.setPaymentType(orders.getPaymentMethod());
		orderDetails.setPaymentDetails(paymentDetails);
		orderDetails.setDeliveredBy(orders.getDeliveryBoy());
		Pricing pricing = new Pricing();
		pricing.setCouponApplied(orders.getCouponCode());
		pricing.setDiscountAmount(orders.getDiscount());
		pricing.setTotalPrice(orders.getTotalAmount());
		pricing.setFinalPayableCost(orders.getTotalAmount());
		pricing.setDeliveryCharges(orders.getDeliveryChange());
		orderDetails.setPricing(pricing);
		// finished till here working properly
		// orderDetails.setCustomerDetails(orderDetailsDaoImpl.getAddressdetails(orderList));
		// orderDetails.setCustomerDetails(orderDetailsDaoImpl.getAddressdetailsFromId(orders.getCustomerId()));
		CpOrderAddress orderAddress = orderDetailsDaoImpl.getAddressdetails(orderId);
		AddressInfo addressInfo = new AddressInfo();
		addressInfo.setName(orderAddress.getName());
		addressInfo.setAddress(orderAddress.getAddress());
		addressInfo.setCity(orderAddress.getCity());
		addressInfo.setLandmark(orderAddress.getLandmark());
		addressInfo.setBuilding(orderAddress.getBuilding());
		addressInfo.setFlat(orderAddress.getFlat());
		addressInfo.setFloor(orderAddress.getFloor());
		addressInfo.setLatitude("10.10");
		addressInfo.setLongitude("10.10");

		// addressInfo.setPhone(orderDetailsDaoImpl.getPhoneNumber(orderAddress.getId()));
		orderDetails.setCustomerDetails(addressInfo);
		return orderDetails;

	}

	private ArrayList<OrderDetails> getOrderList(int storeId, String status) {

		ArrayList<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
		ArrayList<Integer> orderIdList = orderDetailsDaoImpl.getAllOrderId(storeId, status);

		for (int orderList : orderIdList) {
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setOrderId(orderList);
			orderDetails.setStoreName(orderDetailsDaoImpl.getstoreName(storeId));
			orderDetails.setOrderDetails(orderDetailsDaoImpl.getOrderDeatils(orderList));

			CpOrders orders = orderDetailsDaoImpl.getOrderdetails(orderList);
			// orderDetails.setOrderTime(orders.getCreatedDate().getTime());
			PaymentDetails paymentDetails = new PaymentDetails();
			// paymentDetails.setChannel(orders.getChannel());
			paymentDetails.setPaymentType(orders.getPaymentMethod());
			orderDetails.setPaymentDetails(paymentDetails);
			orderDetails.setDeliveredBy(orders.getDeliveryBoy());
			Pricing pricing = new Pricing();
			pricing.setCouponApplied(orders.getCouponCode());
			pricing.setDiscountAmount(orders.getDiscount());
			pricing.setTotalPrice(orders.getTotalAmount());
			pricing.setFinalPayableCost(orders.getTotalAmount());
			pricing.setDeliveryCharges(orders.getDeliveryChange());
			orderDetails.setPricing(pricing);
			// finished till here working properly
			// orderDetails.setCustomerDetails(orderDetailsDaoImpl.getAddressdetails(orderList));
			// orderDetails.setCustomerDetails(orderDetailsDaoImpl.getAddressdetailsFromId(orders.getCustomerId()));
			CpOrderAddress orderAddress = orderDetailsDaoImpl.getAddressdetails(orderList);
			AddressInfo addressInfo = new AddressInfo();
			addressInfo.setName(orderAddress.getName());
			addressInfo.setAddress(orderAddress.getAddress());
			addressInfo.setCity(orderAddress.getCity());
			addressInfo.setLandmark(orderAddress.getLandmark());
			addressInfo.setBuilding(orderAddress.getBuilding());
			addressInfo.setFlat(orderAddress.getFlat());
			addressInfo.setFloor(orderAddress.getFloor());
			addressInfo.setLatitude("10.10");
			addressInfo.setLongitude("10.10");

			// addressInfo.setPhone(orderDetailsDaoImpl.getPhoneNumber(orderAddress.getId()));
			orderDetails.setCustomerDetails(addressInfo);
			orderDetailsList.add(orderDetails);

		}

		return orderDetailsList;
	}

	// count of each status of current date
	public Map<String, Long> getAllCounts(int storeId) {

		ArrayList<String> statusList = getAllStatus();
		// Map<String, Integer> stateCount = new HashMap<String, Integer>();
		Map<String, Long> stateCount = new HashMap<String, Long>();
		for (String status : statusList) {

			Criteria criteria = getHibernatetemplate().getSession().createCriteria(CpOrders.class);
			criteria.add(Restrictions.eq("status", status));
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

			ArrayList<Long> count = (ArrayList<Long>) getHibernatetemplate().get(criteria);

			// stateCount.put(status, count.size());
			stateCount.put(status, count.get(0));
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
	public String saveCancelreason(int orderId, int id) {
		String code = "";
		CpOrders cpOrders = new CpOrders();
		Criteria criteria = getHibernatetemplate().getSession().createCriteria(CpOrders.class);
		criteria.add(Restrictions.eq("id", orderId));
		ArrayList<CpOrders> list = (ArrayList<CpOrders>) getHibernatetemplate().get(criteria);
		cpOrders = list.get(0);
		cpOrders.setCancelReason(reasonMap.get(id));
		cpOrders.setStatus(Constants.Order_Status_cancelled);
		// cancel date update if needed

		if (Constants.success.equals(getHibernatetemplate().update(cpOrders))) {
			code = Constants.success;
		}

		return code;

	}

	// change the status of the order new to confirmed in db
	public String updateOrderStatus(int OrderId, String status) {
		String msg = "";
		CpOrders cpOrders = new CpOrders();
		Criteria criteria = getHibernatetemplate().getSession().createCriteria(CpOrders.class);

		if (status.equalsIgnoreCase(Constants.Order_Status_confirmed)) {
			criteria.add(Restrictions.eq("id", OrderId));
			ArrayList<CpOrders> count = (ArrayList<CpOrders>) getHibernatetemplate().get(criteria);
			cpOrders = count.get(0);
			cpOrders.setStatus(status);
			cpOrders.setConfirmTime(new Date());

			if (Constants.success.equals(getHibernatetemplate().update(cpOrders))) {

				msg = Constants.success;
			}
		}
		// This condition only works after cpos ready change
		if (status.equalsIgnoreCase(Constants.Order_Status_ready)) {
			criteria.add(Restrictions.eq("id", OrderId));
			ArrayList<CpOrders> count = (ArrayList<CpOrders>) getHibernatetemplate().get(criteria);
			cpOrders = count.get(0);
			cpOrders.setStatus(status);
			// cpOrders.setConfirmTime(new Date());

			if (Constants.success.equals(getHibernatetemplate().update(cpOrders))) {
				msg = Constants.success;
				// ArrayList<String> dpNames =
				// getAllDp(count.get(0).getStoreId());
				// String res = new push().sendMessage(dpNames);
			}
		}

		if (status.equalsIgnoreCase(Constants.Order_Status_dispatched)) {
			criteria.add(Restrictions.eq("id", OrderId));
			ArrayList<CpOrders> count = (ArrayList<CpOrders>) getHibernatetemplate().get(criteria);
			cpOrders = count.get(0);
			cpOrders.setStatus(status);
			cpOrders.setDispatchTime(new Date());
			String dpName = new DpOperations().getMaxPriorityDpname(cpOrders.getStoreId());
			if (dpName == null) {
				msg = "NO DPS Available";
			}
			cpOrders.setDeliveryBoy(10);
			if (Constants.success.equals(getHibernatetemplate().update(cpOrders))) {
				msg = Constants.success;
				String res = new push().sendMessage(new ArrayList<>());
				OrderDetails details = new NinjaOperations().getOrderdetailsByOrderId(OrderId, cpOrders.getStoreId());
				boolean stat = new DpOperations().dpStatus.get("mt").getOrderDetailsAssigned().add(details);
				// set count of order assigned
				// boolean stat1 = new
				// DpOperations().dpStatus.get("mt").setAssignedCount(0);
			}
		}

		return msg;
	}

	// getting all available at store dps
	public ArrayList<String> getAllDp(int storeId) {

		ArrayList<String> dps = new ArrayList<>(new DpOperations().DPQueues.get(storeId));
		DpOperations operations = new DpOperations();
		ArrayList<String> names = new ArrayList<String>();
		for (String dp : dps) {
			names.add(operations.dpStatus.get(dp).getDpName());

		}

		return names;
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

	public String manualAssign(int orderId, int storeId) {
		ArrayList<String> dpAtstores = new DpOperations().getAllDpAtStore(storeId);

		String status = new push().sendMessage(dpAtstores);
		CpOrders cpOrders = new CpOrders();
		Criteria criteria = getHibernatetemplate().getSession().createCriteria(CpOrders.class);
		criteria.add(Restrictions.eq("id", orderId));
		ArrayList<CpOrders> count = (ArrayList<CpOrders>) getHibernatetemplate().get(criteria);
		cpOrders = count.get(0);
		cpOrders.setStatus("Dispatched");
		cpOrders.setDispatchTime(new Date());
		return Constants.success;
	}

	public Map<String, ArrayList<OrderDetails>> getOrderDetailsTest(int storeId, String status2) {
		Map<String, ArrayList<OrderDetails>> finalMap = new HashMap<String, ArrayList<OrderDetails>>();
		Criteria criteria = getHibernatetemplate().getSession().createCriteria(CoCOrderDetails.class);
		// criteria.add(Restrictions.eq("orderId", 182032));

		criteria.add(Restrictions.eq("status", status2));

		ArrayList<CoCOrderDetails> orderDet = (ArrayList<CoCOrderDetails>) getHibernatetemplate().get(criteria);
		ArrayList<OrderDetails> listOfOrderdeatails = new ArrayList<OrderDetails>();
		Map<Integer, OrderDetails> orderDetails = new HashMap<Integer, OrderDetails>();

		for (CoCOrderDetails test : orderDet) {
			System.out.println("name " + test.getProductName() + " qnt " + test.getQty());

		}
		OrderDetails orderDetailsObject = null;

		for (CoCOrderDetails details : orderDet) {

			if (!orderDetails.containsKey(details.getOrderId())) {

				orderDetailsObject = new OrderDetails();
				orderDetailsObject.setStoreName("JNC");
				orderDetailsObject.setOrderId(details.getOrderId());
				// orderDetailsObject.setStoreName(details.setStoreName);

				AddressInfo addressInfo = new AddressInfo();

				addressInfo.setFlat(details.getFlat());
				addressInfo.setFloor(details.getFloor());
				addressInfo.setLandmark(details.getLandmark());
				addressInfo.setBuilding(details.getBuilding());
				addressInfo.setPhone(details.getPhone());
				addressInfo.setName(details.getName());
				orderDetailsObject.setCustomerDetails(addressInfo);

				ArrayList<ItemsDetails> demo = new ArrayList<ItemsDetails>();
				ItemsDetails itemsDetails = new ItemsDetails();
				itemsDetails.setSerialNo(1);
				itemsDetails.setItemName(details.getProductName());
				itemsDetails.setItemUnitCount(details.getQty());
				itemsDetails.setItemUnitPrice(details.getCost());
				itemsDetails.setItemTotalPrice(details.getTotalProductCost());
				demo.add(itemsDetails);
				orderDetailsObject.setOrderDetails(demo);

				Pricing pricing = new Pricing();
				pricing.setCouponApplied(details.getCouponCode());
				pricing.setDeliveryCharges(details.getDeliveryCharge());
				// pricing.setDiscountAmount(details.getdiscountAmount);
				pricing.setFinalPayableCost(details.getNetAmount());
				pricing.setTotalPrice(details.getTotalAmount());
				orderDetailsObject.setPricing(pricing);

				PaymentDetails paymentDetails = new PaymentDetails();
				paymentDetails.setChannel(details.getChannelName());
				paymentDetails.setPaymentType(details.getPaymentMethod());
				orderDetailsObject.setPaymentDetails(paymentDetails);

				listOfOrderdeatails.add(orderDetailsObject);
				orderDetails.put(details.getOrderId(), orderDetailsObject);

			}

			else {

				ItemsDetails itemsDetails = new ItemsDetails();
				itemsDetails.setSerialNo(2);
				itemsDetails.setItemName(details.getProductName());
				itemsDetails.setItemUnitCount(details.getQty());
				itemsDetails.setItemUnitPrice(details.getCost());
				itemsDetails.setItemTotalPrice(details.getTotalProductCost());
				// orderDetailsObject.getOrderDetails().add(itemsDetails);
				orderDetails.get(details.getOrderId()).getOrderDetails().add(itemsDetails);
				// orderDetailsObject.get(details.

				// orderDetailsObject.getget(details.getOrderId()).getOrderDetails().add(itemsDetails);

			}

		}

		finalMap.put(status2, listOfOrderdeatails);

		return finalMap;
	}

	public Map<String, ArrayList<OrderDetails>> getOrderDetailsTestopt(int storeId, String state) {

		ArrayList<Integer> orderIdList = orderDetailsDaoImpl.getAllOrderId(storeId, state);
		Map<String, ArrayList<OrderDetails>> finalMap = new HashMap<String, ArrayList<OrderDetails>>();
		ArrayList<OrderDetails> listOfOrderdeatails = new ArrayList<OrderDetails>();
		Map<Integer, OrderDetails> orderDetails = new HashMap<Integer, OrderDetails>();

		for (Integer orderId : orderIdList) {
			Criteria criteria = getHibernatetemplate().getSession().createCriteria(CoCOrderDetails.class);
			criteria.add(Restrictions.eq("orderId", orderId));

			// criteria.add(Restrictions.eq("status", "New"));

			ArrayList<CoCOrderDetails> orderDet = (ArrayList<CoCOrderDetails>) getHibernatetemplate().get(criteria);
			// CoCOrderDetails details = orderDet.get(0);
			OrderDetails orderDetailsObject = null;
			for (CoCOrderDetails details : orderDet) {
				if (!orderDetails.containsKey(details.getOrderId())) {

					orderDetailsObject = new OrderDetails();
					orderDetailsObject.setStoreName("JNC");
					orderDetailsObject.setOrderId(details.getOrderId());
					// orderDetailsObject.setStoreName(details.setStoreName);

					AddressInfo addressInfo = new AddressInfo();

					addressInfo.setFlat(details.getFlat());
					addressInfo.setFloor(details.getFloor());
					addressInfo.setLandmark(details.getLandmark());
					addressInfo.setBuilding(details.getBuilding());
					addressInfo.setPhone(details.getPhone());
					addressInfo.setName(details.getName());
					orderDetailsObject.setCustomerDetails(addressInfo);

					ArrayList<ItemsDetails> demo = new ArrayList<ItemsDetails>();
					ItemsDetails itemsDetails = new ItemsDetails();
					itemsDetails.setSerialNo(1);
					itemsDetails.setItemName(details.getProductName());
					itemsDetails.setItemUnitCount(details.getQty());
					itemsDetails.setItemUnitPrice(details.getCost());
					itemsDetails.setItemTotalPrice(details.getTotalProductCost());
					demo.add(itemsDetails);
					orderDetailsObject.setOrderDetails(demo);

					Pricing pricing = new Pricing();
					pricing.setCouponApplied(details.getCouponCode());
					pricing.setDeliveryCharges(details.getDeliveryCharge());
					// pricing.setDiscountAmount(details.getdiscountAmount);
					pricing.setFinalPayableCost(details.getNetAmount());
					pricing.setTotalPrice(details.getTotalAmount());
					orderDetailsObject.setPricing(pricing);

					PaymentDetails paymentDetails = new PaymentDetails();
					paymentDetails.setChannel(details.getChannelName());
					paymentDetails.setPaymentType(details.getPaymentMethod());
					orderDetailsObject.setPaymentDetails(paymentDetails);

					listOfOrderdeatails.add(orderDetailsObject);
					orderDetails.put(details.getOrderId(), orderDetailsObject);

				}

				else {

					ItemsDetails itemsDetails = new ItemsDetails();
					itemsDetails.setSerialNo(2);
					itemsDetails.setItemName(details.getProductName());
					itemsDetails.setItemUnitCount(details.getQty());
					itemsDetails.setItemUnitPrice(details.getCost());
					itemsDetails.setItemTotalPrice(details.getTotalProductCost());
					// orderDetailsObject.getOrderDetails().add(itemsDetails);
					orderDetails.get(details.getOrderId()).getOrderDetails().add(itemsDetails);
					// orderDetailsObject.get(details.

					// orderDetailsObject.getget(details.getOrderId()).getOrderDetails().add(itemsDetails);

				}

			}

			finalMap.put("New", listOfOrderdeatails);
		}

		return finalMap;

	}

	public Map<String, ArrayList<OrderDetails>> getOrderDetailsFinal(int storeId, String status2) {
		Map<String, ArrayList<OrderDetails>> finalMap = new HashMap<String, ArrayList<OrderDetails>>();
		ArrayList<OrderDetails> listOfOrderdeatails = new ArrayList<OrderDetails>();
		Map<Integer, OrderDetails> orderDetails = new HashMap<Integer, OrderDetails>();
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			String sql = "SELECT order_id, store_id, order_time, name, phone, floor, building, flat, landmark, product_name, qty, cost, total_product_cost, payment_method, channel_name, total_amount, delivery_charge, net_amount,coupon_code  FROM coc_order_view WHERE status = '"
					+ status2 + "'and store_id =" + storeId + ";";
			ResultSet rs = stmt.executeQuery(sql);
			// STEP 5: Extract data from result set
			System.out.println("got result");
		//	ArrayList<CoCOrderDetails> orderDet = (ArrayList<CoCOrderDetails>) getHibernatetemplate().get(criteria);
			// CoCOrderDetails details = orderDet.get(0);
			OrderDetails orderDetailsObject = null;
			while (rs.next()) {

				if (!orderDetails.containsKey(rs.getInt("order_id"))) {

					orderDetailsObject = new OrderDetails();
				//	orderDetailsObject.setStoreName(rs.getInt("storeName"));
					orderDetailsObject.setOrderId(rs.getInt("order_id"));
					// orderDetailsObject.setStoreName(details.setStoreName);

					AddressInfo addressInfo = new AddressInfo();

					addressInfo.setFlat(rs.getString("flat"));
					addressInfo.setFloor(rs.getString("floor"));
					addressInfo.setLandmark(rs.getString("landmark"));
					addressInfo.setBuilding(rs.getString("building"));
					addressInfo.setPhone(rs.getString("phone"));
					addressInfo.setName(rs.getString("name"));
					orderDetailsObject.setCustomerDetails(addressInfo);

					ArrayList<ItemsDetails> demo = new ArrayList<ItemsDetails>();
					ItemsDetails itemsDetails = new ItemsDetails();
					itemsDetails.setSerialNo(1);
					itemsDetails.setItemName(rs.getString("product_name"));
					itemsDetails.setItemUnitCount(rs.getInt("qty"));
					itemsDetails.setItemUnitPrice(rs.getDouble("cost"));
					itemsDetails.setItemTotalPrice(rs.getDouble("total_product_cost"));
					demo.add(itemsDetails);
					orderDetailsObject.setOrderDetails(demo);

					Pricing pricing = new Pricing();
					pricing.setCouponApplied(rs.getString("coupon_code"));
					pricing.setDeliveryCharges(rs.getDouble("delivery_charge"));
					// pricing.setDiscountAmount(details.getdiscountAmount);
					pricing.setFinalPayableCost(rs.getDouble("total_amount"));
					pricing.setTotalPrice(rs.getDouble("net_amount"));
					orderDetailsObject.setPricing(pricing);

					PaymentDetails paymentDetails = new PaymentDetails();
					paymentDetails.setChannel(rs.getString("channel_name"));
					paymentDetails.setPaymentType(rs.getString("payment_method"));
					orderDetailsObject.setPaymentDetails(paymentDetails);

					listOfOrderdeatails.add(orderDetailsObject);
					orderDetails.put(rs.getInt("order_id"), orderDetailsObject);

				}

				else {

					ItemsDetails itemsDetails = new ItemsDetails();
					itemsDetails.setSerialNo(2);
					itemsDetails.setItemName(rs.getString("product_name"));
					itemsDetails.setItemUnitCount(rs.getInt("qty"));
					itemsDetails.setItemUnitPrice(rs.getDouble("cost"));
					itemsDetails.setItemTotalPrice(rs.getDouble("total_product_cost"));
					// orderDetailsObject.getOrderDetails().add(itemsDetails);
					orderDetails.get(rs.getInt("order_id")).getOrderDetails().add(itemsDetails);
					// orderDetailsObject.get(details.

					// orderDetailsObject.getget(details.getOrderId()).getOrderDetails().add(itemsDetails);

				}


			}
			finalMap.put("New", listOfOrderdeatails);
			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");

		return finalMap;
	}
}