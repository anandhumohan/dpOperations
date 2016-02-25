package com.chaipoint.deliveryappapis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.supervisor.SuperVisorOperations;

@Path("/supervisor")
public class SuperVisorAPI {
	@Path("/default")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response orderStatus(ArrayList<String> storeList) {
		Map<String, ArrayList<OrderDetails>> orderDetails = new HashMap<String, ArrayList<OrderDetails>>();
		orderDetails = new SuperVisorOperations().getOrderStatus(storeList, orderDetails);
		return Response.ok(orderDetails, MediaType.APPLICATION_JSON).build();

	}
	
	@Path("/count")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response OrderCounts(ArrayList<String> storeList, String orderStatus) {
		
		ArrayList<String> orderList = new SuperVisorOperations().getOrderCounts(storeList, orderStatus);
		return Response.ok(orderList.size(), MediaType.APPLICATION_JSON).build();

	}
	
	@Path("/details")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response OrderDetails(ArrayList<String> orderList, String orderStatus) {
		
		ArrayList<OrderDetails> orderDetailsList = new SuperVisorOperations().getOrderDetails(orderList);
		return Response.ok(orderList.size(), MediaType.APPLICATION_JSON).build();

	}
	
	
	
	
	
	
}
