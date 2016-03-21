package com.chaipoint.deliveryappapis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.chaipoint.constants.Constants;
import com.chaipoint.deliverypartner.DpOperations;
import com.chaipoint.helperclasses.Assign;
import com.chaipoint.helperclasses.AssignAccpet;
import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.helperclasses.RootAssign;
import com.chaipoint.helperclasses.RootAssignAccpet;
import com.chaipoint.helperclasses.RootCancel;
import com.chaipoint.helperclasses.RootOrderList;
import com.chaipoint.ninja.NinjaOperations;
import com.google.gson.Gson;

@Path("/dpscreen")
public class DeliveryPartnerAPI {
	// this is for status tab
	@Path("/atstore")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response dpStatusTab(String atStoreJson) {
		RootCancel rootCancel = new Gson().fromJson(atStoreJson, RootCancel.class);
		String status = new DpOperations().DpAvailbleAtStore(rootCancel.getStoreId(), rootCancel.getMtfId());
		return Response.ok(status, MediaType.TEXT_PLAIN).build();

	}

	// for dispatched tab
	@Path("/dispatched")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response dispatchedTabAction(@QueryParam("storeId") int storeId, @QueryParam("status") String status) {
		RootOrderList rootOrder = new RootOrderList();
		Map<String, ArrayList<OrderDetails>> orderDetais = new NinjaOperations().getOrderDetailsFinal(storeId, status);
		if (orderDetais.get(status).isEmpty()) {
			rootOrder.setMessage("No orders");
		} else {
			rootOrder.setMessage(Constants.success);
		}
		return Response.ok(new Gson().toJson(rootOrder), MediaType.TEXT_PLAIN).build();
		// return Response.ok(orderList, MediaType.TEXT_PLAIN).build();

	}

	// have to show every order details which is assign and accepted(guy)
	@Path("/assign")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAssighedOrders(String assignJson) {
		Assign assignAccept = new Gson().fromJson(assignJson, Assign.class);
		
		
		
	//	RootAssignAccpet assign = new RootAssignAccpet();
		//AssignAccpet orderdetail = new AssignAccpet();
		Map<String, ArrayList<OrderDetails>> orderList = new HashMap<String, ArrayList<OrderDetails>>();
		RootOrderList rootOrderList = new RootOrderList();
		ArrayList<OrderDetails> assignedList = new DpOperations().getAllAssighedOrders(assignAccept.getStoreId(), assignAccept.getMtfId());
		ArrayList<OrderDetails> acceptedList = new DpOperations().getAllAcceptedOrders(assignAccept.getStoreId(), assignAccept.getMtfId());
		
		if(assignedList.isEmpty() && acceptedList.isEmpty()){
			rootOrderList.setMessage("No orders");
		}
		else{
			rootOrderList.setMessage(Constants.success);
			ArrayList<OrderDetails> details = new ArrayList<OrderDetails>();
			if(!assignedList.isEmpty()){
			for(OrderDetails assign : assignedList){
				assign.setOrderStatus("0");
				assign.setStatus("Assigned");
				details.add(assign);
			}
			}
			if(!acceptedList.isEmpty()){
			for(OrderDetails accept : acceptedList){
				accept.setOrderStatus("1");
				accept.setStatus("Accepted");
				details.add(accept);
			}
			}
		//	orderList.put("Ready", assignedList);
			orderList.put("Dispatched", details);
			rootOrderList.setOrderList(orderList);
		}
		

		return Response.ok(new Gson().toJson(rootOrderList), MediaType.TEXT_PLAIN).build();

	}

	// in delivered tab accept button
	@Path("/accept")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response acceptOrders(String acceptJson) {
		RootCancel cancel = new Gson().fromJson(acceptJson, RootCancel.class);

		String msg = new DpOperations().acceptedOrders(cancel.getStoreId(), cancel.getMtfId(), cancel.getOrderId());
		return Response.ok(msg, MediaType.TEXT_PLAIN).build();

	}

	@Path("/delivered")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deliverOrders(String deliveredJson) {
		RootCancel cancel = new Gson().fromJson(deliveredJson, RootCancel.class);

		String msg = new DpOperations().deliveredOrders(cancel.getStoreId(), cancel.getMtfId(), cancel.getOrderId());
		return Response.ok(msg, MediaType.TEXT_PLAIN).build();

	}

	// delivered tab
	@Path("/dpdelivered")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deliveredTabAction(@QueryParam("storeId") int storeId, @QueryParam("mtfId") String mtfId) {
		
		RootOrderList rootOrder = new RootOrderList();
		Map<String, ArrayList<OrderDetails>> orderDetais =new DpOperations().getAllDeliveredOrders(storeId, mtfId);

		rootOrder.setOrderList(orderDetais);
	//	Map<String, Long> count = new NinjaOperations().getAllCounts(storeId);
	//	rootOrder.setOrderCount(count);
		if(orderDetais.get(Constants.Order_Status_delivered).isEmpty()){
			rootOrder.setMessage("No orders");
		}
		else{
			rootOrder.setMessage(Constants.success);
		}
		return Response.ok(new Gson().toJson(rootOrder), MediaType.TEXT_PLAIN).build();
		
		
		

		
	}

	@Path("/dpcount")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response dpTabsCount(String countJson) {
		
		
		RootCancel root = new Gson().fromJson(countJson, RootCancel.class);
		

		Map<String, Long> orderList = new DpOperations().getAllDpCounts(root.getStoreId(), root.getMtfId());
		return Response.ok(new Gson().toJson(orderList), MediaType.TEXT_PLAIN).build();

	}

	@Path("/flush")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response dpFlush() {
		String result = new DpOperations().forceFlush();
		return Response.ok(result, MediaType.TEXT_PLAIN).build();

	}
	
}
