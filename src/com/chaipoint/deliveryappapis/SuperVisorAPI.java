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
import com.chaipoint.ninja.NinjaOperations;
import com.chaipoint.supervisor.SuperVisorOperations;

@Path("/supervisor")
public class SuperVisorAPI {

	@Path("/details")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response OrderDetails(ArrayList<String> orderList, String orderStatus) {

		Map<String, ArrayList<OrderDetails>> orderDetais = new SuperVisorOperations().getOrderDetails(orderList, orderStatus);
		return Response.ok(orderDetais, MediaType.TEXT_PLAIN).build();

	}

	@Path("/count")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response OrderCounts(ArrayList<String> storeList, String orderStatus) {

		ArrayList<String> orderList = new SuperVisorOperations().getOrderCounts(storeList, orderStatus);
		return Response.ok(orderList.size(), MediaType.APPLICATION_JSON).build();

	}

}
