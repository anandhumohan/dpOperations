package com.chaipoint.deliverypartner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.chaipoint.constants.Constants;
import com.chaipoint.deliveryappapis.HelperAPI;
import com.chaipoint.dppojos.CpOrders;
import com.chaipoint.dppojos.StaffMaster;
import com.chaipoint.helperclasses.DpStatus;
import com.chaipoint.helperclasses.NameObject;
import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.helperclasses.OrderStatus;
import com.chaipoint.hibernatehelper.HibernateOperations;
import com.chaipoint.ninja.NinjaOperations;

public class DpOperations {

	HibernateOperations operations = null;
	public static Map<Integer, Queue<String>> DPQueues = new HashMap<Integer, Queue<String>>();
	public static Map<String, DpStatus> dpStatus = new HashMap<String, DpStatus>();
	public static Map<Integer, ArrayList<String>> storeDpList = new HashMap<Integer, ArrayList<String>>();
	// public static Map<String, ArrayList<DpStatus>> stoteDpStatusMap = new
	// HashMap<String, ArrayList<DpStatus>>();

	// public static Map<String, OrderStatus> orderStatus = new HashMap<String,
	// OrderStatus>();
	HibernateOperations template = null;

	// DpStatus status = null;
	// Queue<String> queue = null;
	// ArrayList<String> dpStores = null;

	// will call this funtion after succesful login for setting maps
	// available.

	public String initialOperations(int storeId, String mtfId) {
		String DPId = mtfId;
		// creating two maps when first guy login
		if (!storeDpList.containsKey(storeId)) {

			DpStatus status = new DpStatus();
			status.setStatus(Constants.dp_Status_returning_to_store);
			status.setDpId(DPId);
			status.setAssignedCount(0);

			status.setOrderDetailsAssigned(new LinkedList<OrderDetails>());
			status.setOrderDetailsAccepted(new LinkedList<OrderDetails>());
			status.setDpDelivered(new ArrayList<OrderDetails>());
			dpStatus.put(DPId, status);

			Queue<String> queue = new LinkedList<String>();
			DPQueues.put(storeId, queue);

			ArrayList<String> dpStores = new ArrayList<String>();
			dpStores.add(DPId);
			storeDpList.put(storeId, dpStores);

		} else {
			DpStatus status = new DpStatus();
			status.setStatus(Constants.dp_Status_returning_to_store);
			status.setDpId(DPId);
			status.setAssignedCount(0);
		//	status.setor

			status.setOrderDetailsAssigned(new LinkedList<OrderDetails>());
			status.setOrderDetailsAccepted(new LinkedList<OrderDetails>());
			status.setDpDelivered(new ArrayList<OrderDetails>());
			dpStatus.put(DPId, status);
			// dpStatus.put(DPId, status);

			// queue = DPQueues.get(storeId);
			// queue.add(mtfId);
			// DPQueues.put(storeId, queue);

			ArrayList<String> dpStores = storeDpList.get(storeId);
			dpStores.add(DPId);
			storeDpList.put(storeId, dpStores);

		}

		return Constants.success;
	}

	public String DpAvailbleAtStore(int storeId, String DPId) {

		DpStatus status = dpStatus.get(DPId);
		status.setStatus(Constants.dp_Status_available);
		dpStatus.put(DPId, status);
		// check for null check

		Queue<String> queue = DPQueues.get(storeId);
		queue.add(DPId);
		DPQueues.put(storeId, queue);

		return Constants.success;
	}

	// get all Order details of dispatched items of the store
	public Map<String, ArrayList<OrderDetails>> getAllDispatchedOrder(int storeId) {

		Map<String, ArrayList<OrderDetails>> orderDetails = new NinjaOperations().getOrderDetailsTest(storeId,
				Constants.Order_Status_dispatched);
		return orderDetails;
	}

	public Map<String, ArrayList<OrderDetails>> getAllDeliveredOrders(int storeId, String mtfId) {
		Map<String, ArrayList<OrderDetails>> orderDetails = new HashMap<String, ArrayList<OrderDetails>>();
		ArrayList<OrderDetails> delivered = dpStatus.get(mtfId).getDpDelivered();
		
		orderDetails.put(Constants.Order_Status_delivered, delivered);
		

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
		DpStatus status = dpStatus.get(DPId);
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

	public ArrayList<OrderDetails> getAllAssighedOrders(int storeId, String mtfId) {

		if (dpStatus.get(mtfId).getOrderDetailsAssigned().size() == 0
				|| dpStatus.get(mtfId).getOrderDetailsAssigned().isEmpty()
				|| dpStatus.get(mtfId).getOrderDetailsAssigned() == null) {
			return new ArrayList<OrderDetails>();

		} else {
			ArrayList<OrderDetails> list = new ArrayList<>(dpStatus.get(mtfId).getOrderDetailsAssigned());
			return list;
		}

	}

	public ArrayList<OrderDetails> getAllAcceptedOrders(int storeId, String mtfId) {

		if (dpStatus.get(mtfId).getOrderDetailsAccepted().size() == 0
				|| dpStatus.get(mtfId).getOrderDetailsAccepted().isEmpty()
				|| dpStatus.get(mtfId).getOrderDetailsAccepted() == null) {
			return new ArrayList<OrderDetails>();

		} else {
			ArrayList<OrderDetails> list = new ArrayList<>(dpStatus.get(mtfId).getOrderDetailsAccepted());
			return list;
		}

	}

	public String acceptedOrders(int storeId, String mtfId, int orderId) {
		int count = dpStatus.get(mtfId).getAssignedCount();
		dpStatus.get(mtfId).setAssignedCount(count + 1);

		LinkedList<OrderDetails> orders = dpStatus.get(mtfId).getOrderDetailsAssigned();

		// copying
		for (OrderDetails orderList : orders) {
			if (orderList.getOrderId() == orderId) {
				dpStatus.get(mtfId).getOrderDetailsAccepted().add(orderList);
				dpStatus.get(mtfId).getOrderDetailsAssigned().remove(orderList);
				// dpStatus.get(mtfId).setOrderDetailsAssigned(new
				// LinkedList<OrderDetails>());

			} else if (orderList.getOrderId() == orderId && orders.size() == 1) {
				dpStatus.get(mtfId).getOrderDetailsAccepted().add(orderList);
				dpStatus.get(mtfId).getOrderDetailsAssigned().remove(orderList);
				dpStatus.get(mtfId).setOrderDetailsAssigned(new LinkedList<OrderDetails>());

			}

		}

		// changing dp status
		dpStatus.get(mtfId).setStatus(Constants.dp_Status_out_for_delivery);
		DPQueues.get(storeId).poll();
		// update in cpos
		String msg = dispatchStatus(orderId, "Dispatched");
		return msg;
	}

	public String dispatchStatus(int orderId, String status) {
		String msg = "";
		CpOrders cpOrders = new CpOrders();
		Criteria criteria = getTemplate().getSession().createCriteria(CpOrders.class);

		criteria.add(Restrictions.eq("id", orderId));
		ArrayList<CpOrders> count = (ArrayList<CpOrders>) getTemplate().get(criteria);
		cpOrders = count.get(0);
		cpOrders.setStatus(status);
		// cpOrders.setConfirmTime(new Date());

		if (Constants.success.equals(getTemplate().update(cpOrders))) {

			msg = Constants.success;
		}
		return msg;

	}

	public String deliveredOrders(int storeId, String mtfId, int orderId) {

		 int count = dpStatus.get(mtfId).getAssignedCount();
		 dpStatus.get(mtfId).setAssignedCount(--count);
		 if(count == 0){
			 dpStatus.get(mtfId).setStatus(Constants.dp_Status_returning_to_store);
		 }

		// remove from accepted

		LinkedList<OrderDetails> orders = dpStatus.get(mtfId).getOrderDetailsAccepted();
		
		int code = 0;
		String status = updateTable(code, Constants.Order_Status_delivered, orderId);
//check for null point exception
		// copying
		for (OrderDetails orderList : orders) {
			if (orderList.getOrderId() == orderId) {
				dpStatus.get(mtfId).getDpDelivered().add(orderList);
				orders.remove(orderList);

			}

		}
	//	int code = getCodeFromMtfId(mtfId);
		
		// update in cpos

		return status;
	}

	private String updateTable(int code, String status, int orderId) {
		String msg = "";
		CpOrders cpOrders = new CpOrders();
		Criteria criteria = getOperations().getSession().createCriteria(CpOrders.class);

		if (status.equalsIgnoreCase(Constants.Order_Status_delivered)) {
			criteria.add(Restrictions.eq("id", orderId));
			ArrayList<CpOrders> count = (ArrayList<CpOrders>) getOperations().get(criteria);
			cpOrders = count.get(0);
			cpOrders.setStatus(status);
		//	cpOrders.setDeliveryBoy(code);
			// cpOrders.setConfirmTime(new Date());

			if (Constants.success.equals(getOperations().update(cpOrders))) {

				msg = Constants.success;
			}

		}
		return msg;
	}

	public int getCodeFromMtfId(String username) {
	//	String username = mtfId.toUpperCase();
/*
		Criteria criteria = getTemplate().getSession().createCriteria(StaffMaster.class);
		// criteria.add(Restrictions.eq("locationId", locationId));
		criteria.add(Restrictions.eq("code", mft));
		criteria.setProjection(Projections.property("id"));
		ArrayList<Integer> names = (ArrayList<Integer>) getTemplate().get(criteria);
		*/
		Criteria criteria = getTemplate().getSession().createCriteria(StaffMaster.class);
		// criteria.add(Restrictions.eq("locationId", locationId));
		criteria.add(Restrictions.eq("username", username));
		ArrayList<StaffMaster> names = (ArrayList<StaffMaster>) getTemplate().get(criteria);

	

		return names.get(0).getId();

	}

	public String getMaxPriorityDpname(int storeId) {

		String getDp = "";
		if (!DPQueues.containsKey(storeId) || DPQueues.get(storeId) == null || DPQueues.get(storeId).isEmpty()) {
			getDp = "No dp";

		} else {
			getDp = DPQueues.get(storeId).peek();
		}

		return getDp;
	}

	public ArrayList<NameObject> getAllDps(int storeId){
		ArrayList<NameObject> namelist = new ArrayList<NameObject>();
		
		if (!DPQueues.containsKey(storeId) || DPQueues.get(storeId) == null || DPQueues.get(storeId).isEmpty()) {
			return namelist;

		} else {
			for(String mtf : DPQueues.get(storeId)){
				NameObject nameObject = new NameObject();
				nameObject.setMtfId(mtf);
				HelperAPI api = new HelperAPI();
				String idName = api.mtfIdNames.get(mtf.toUpperCase());
				nameObject.setName(idName);
				namelist.add(nameObject);
				
			}
			
		}
		
		
		
		return namelist;
		
		
	}
	
	
	public ArrayList<String> getAllDpAtStore(int storeId) {

		ArrayList<String> dpList = (ArrayList<String>) DPQueues.get(storeId);
		return dpList;
	}

	public Map<String, Long> getAllDpCounts(int storeId, String mtfId) {
		// String status = new HelperAPI().getAllIdAndNames();
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

		dpScreenCount.put("Assigned", (long) dpStatus.get(mtfId).getOrderDetailsAssigned().size());
		dpScreenCount.put("Accepted", (long) dpStatus.get(mtfId).getOrderDetailsAssigned().size());

		Criteria cr = getTemplate().getSession().createCriteria(CpOrders.class);
		cr.add(Restrictions.eq("status", "Dispatched"));
		cr.add(Restrictions.eq("storeId", storeId));
		// cr.add(Restrictions.eq("deliverd_by", storeId));
		// criteria.setProjection(Projections.property("id"));
		cr.setProjection(Projections.rowCount());

		// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd
		// hh:mm:ss");
		// Date date = new Date();
		SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();
		System.out.println("ennathe date" + date1);

		Date min = null;
		try {
			min = forma.parse(forma.format(date1));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Date max = new Date(min.getTime() + TimeUnit.DAYS.toMillis(1));

		System.out.println("minimum date" + min);
		System.out.println("minimum date" + max);

		cr.add(Restrictions.ge("createdDate", min));

		cr.add(Restrictions.lt("createdDate", max));

		ArrayList<Long> count1 = (ArrayList<Long>) getTemplate().get(cr);

		dpScreenCount.put(Constants.Order_Status_delivered, count1.get(0));

		return dpScreenCount;
	}

	private HibernateOperations getTemplate() {

		if (template == null) {
			template = new HibernateOperations();
		}
		return template;
	}

	public String assignOrderToDp(OrderDetails details, String dpMtfId, int storeId) {
		System.out.println("reached here");
		if (!dpStatus.containsKey(dpMtfId) || dpStatus.get(dpMtfId).getOrderDetailsAssigned() == null
				|| dpStatus.get(dpMtfId).getOrderDetailsAssigned().isEmpty()) {
			LinkedList<OrderDetails> assignedDetails = dpStatus.get(dpMtfId).getOrderDetailsAssigned();
			assignedDetails.add(details);
			DPQueues.get(storeId).poll();
			DPQueues.get(storeId).add(dpMtfId);
			

		//	int orderCount = dpStatus.get(dpMtfId).getAssignedCount();
		//	dpStatus.get(dpMtfId).setAssignedCount(++orderCount);
		} else {
			
			dpStatus.get(dpMtfId).getOrderDetailsAssigned().add(details);
			DPQueues.get(storeId).poll();
			DPQueues.get(storeId).add(dpMtfId);
			int orderCount = dpStatus.get(dpMtfId).getAssignedCount();
			dpStatus.get(dpMtfId).setAssignedCount(++orderCount);
		}
		for (OrderDetails c : dpStatus.get(dpMtfId).getOrderDetailsAssigned()) {
			System.out.println("assighned" + c.getOrderId());
		}
		return Constants.success;
	}

	public HibernateOperations getOperations() {
		if (operations == null) {
			operations = new HibernateOperations();
		}
		return operations;
	}
}
