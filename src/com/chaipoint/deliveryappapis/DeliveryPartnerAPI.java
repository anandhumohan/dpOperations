package com.chaipoint.deliveryappapis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.chaipoint.deliverypartner.DpOperations;
import com.chaipoint.helperclasses.OrderDetails;
import com.google.gson.Gson;

@Path("/dp")
public class DeliveryPartnerAPI {
	// this is for status tab
	@Path("/atstorebutton")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response dpStatusTab(String storeId, String mtfId) {

		String status = new DpOperations().DpAvailbleAtStore(storeId, mtfId);
		return Response.ok(status, MediaType.TEXT_PLAIN).build();

	}

	// for dispatched tab
	@Path("/dispatched")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response dispatchedTabAction(int storeId, String mtfId) {

		Map<String, ArrayList<OrderDetails>> orderList = new DpOperations().getAllDispatchedOrder(storeId);
		return Response.ok(orderList, MediaType.TEXT_PLAIN).build();

	}

	// have to show every order details which is assign to that guy
	@Path("/assign")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAssighedOrders(int storeId, String mtfId) {

		Map<String, ArrayList<OrderDetails>> orderList = new DpOperations().getAllAssighedOrders(storeId, mtfId);
		return Response.ok(orderList, MediaType.TEXT_PLAIN).build();

	}

	// in delivered tab accept button
	@Path("/accept")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response acceptOrders(int storeId, String mtfId) {

		Map<String, ArrayList<OrderDetails>> orderList = new DpOperations().acceptedOrders(storeId, mtfId);
		return Response.ok(orderList, MediaType.TEXT_PLAIN).build();

	}
	
	@Path("/delivered")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deliverOrders(int storeId, String mtfId) {

		Map<String, ArrayList<OrderDetails>> orderList = new DpOperations().deliveredOrders(storeId, mtfId);
		return Response.ok(orderList, MediaType.TEXT_PLAIN).build();

	}


	// delivered tab
	@Path("/deliveredtab")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deliveredTabAction(int storeId, String mtfId) {

		Map<String, ArrayList<OrderDetails>> orderList = new DpOperations().getAllDeliveredOrders(storeId, mtfId);
		return Response.ok(orderList, MediaType.TEXT_PLAIN).build();

	}

	@Path("/flush")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response dpFlush() {
		String result = new DpOperations().forceFlush();
		return Response.ok(result, MediaType.TEXT_PLAIN).build();

	}

}
