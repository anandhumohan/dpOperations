package com.chaipoint.supervisor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.chaipoint.constants.Constants;
import com.chaipoint.dphelper.OrderDetailsDaoImpl;
import com.chaipoint.dppojos.CpOrders;
import com.chaipoint.helperclasses.AddressInfo;
import com.chaipoint.helperclasses.ItemsDetails;
import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.helperclasses.PaymentDetails;
import com.chaipoint.helperclasses.Pricing;
import com.chaipoint.hibernatehelper.HibernateTemplate;
import com.chaipoint.ninja.NinjaOperations;

public class SuperVisorOperations {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://54.64.5.133/cpos";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root@897";

	HibernateTemplate template = null;
	public static Constants constants = new Constants();
	public static OrderDetailsDaoImpl orderDetailsDaoImpl = new OrderDetailsDaoImpl();

	public Map<String, ArrayList<OrderDetails>> getOrderDetails(ArrayList<Integer> idList, String status) {

		// Map<String, ArrayList<OrderDetails>> orderDetails = new
		// HashMap<String, ArrayList<OrderDetails>>();

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
for(int storeId : idList){
			String sql = "SELECT order_id, store_id, order_time, delivery_coordinates, name, phone, floor, building, flat, landmark, product_name, qty, cost, total_product_cost, payment_method, channel_name, total_amount, delivery_charge, net_amount,coupon_code  FROM coc_order_view WHERE status = '"
					+ status + "'and store_id =" + storeId + ";";
			ResultSet rs = stmt.executeQuery(sql);
			// STEP 5: Extract data from result set
			System.out.println("got result");
			// ArrayList<CoCOrderDetails> orderDet =
			// (ArrayList<CoCOrderDetails>)
			// getHibernatetemplate().get(criteria);
			// CoCOrderDetails details = orderDet.get(0);
			OrderDetails orderDetailsObject = null;
			while (rs.next()) {

				if (!orderDetails.containsKey(rs.getInt("order_id"))) {

					orderDetailsObject = new OrderDetails();
					orderDetailsObject.setStoreName("JNC");
					orderDetailsObject.setOrderId(rs.getInt("order_id"));
					orderDetailsObject.setOrderTime(rs.getString("order_time"));
					// orderDetailsObject.setStoreName(details.setStoreName);

					AddressInfo addressInfo = new AddressInfo();

					addressInfo.setFlat(rs.getString("flat"));
					addressInfo.setFloor(rs.getString("floor"));
					addressInfo.setLandmark(rs.getString("landmark"));
					addressInfo.setBuilding(rs.getString("building"));
					addressInfo.setPhone(rs.getString("phone"));
					addressInfo.setName(rs.getString("name"));
					/*
					 * if(rs.getString("delivery_coordinates") == null){
					 * addressInfo.setLatitude("0.0");
					 * addressInfo.setLongitude("0.0");
					 * 
					 * }else{ String cordinates =
					 * rs.getString("delivery_coordinates"); List<String>
					 * elementList = Arrays.asList(cordinates.split(","));
					 * addressInfo.setLatitude(elementList.get(0));
					 * addressInfo.setLongitude(elementList.get(1)); }
					 */
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
					pricing.setFinalPayableCost(rs.getDouble("net_amount"));
					pricing.setTotalPrice(rs.getDouble("total_amount"));
					pricing.setTotalTax(rs.getDouble("net_amount") - rs.getDouble("total_amount"));
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
			finalMap.put(status, listOfOrderdeatails);
			rs.close();
			
			
		}
			
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

	

	public Map<String, Long> getAllOrderStatusCounts(ArrayList<Integer> storeIds) {


		ArrayList<String> statusList = new NinjaOperations().getAllStatus();
		// Map<String, Integer> stateCount = new HashMap<String, Integer>();
		Map<String, Long> stateCount = new HashMap<String, Long>();
		for (String status : statusList) {
			
		long totalCount = 0;
			
				
			for(Integer storeId : storeIds){

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

		//	System.out.println("minimum date" + minDate);
		//	System.out.println("minimum date" + maxDate);

			criteria.add(Restrictions.ge("createdDate", minDate));

			criteria.add(Restrictions.lt("createdDate", maxDate));

			ArrayList<Long> count = (ArrayList<Long>) getHibernatetemplate().get(criteria);

			totalCount = totalCount + count.get(0);
			}
			stateCount.put(status, totalCount);
		}
			
		return stateCount;

	

		}
	

	public HibernateTemplate getHibernatetemplate() {
		if (template == null) {
			template = new HibernateTemplate();
		}
		return template;
	}

}