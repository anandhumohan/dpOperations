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

import com.chaipoint.helperclasses.CancelRoot;
import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.helperclasses.SuperVisionRoot;
import com.chaipoint.ninja.NinjaOperations;
import com.chaipoint.supervisor.SuperVisorOperations;
import com.google.gson.Gson;

@Path("/supervisor")
public class SuperVisorAPI {

	@Path("/details")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response OrderDetails(String superJson) {
		SuperVisionRoot superRoot = new Gson().fromJson(superJson, SuperVisionRoot.class);
		Map<String, ArrayList<OrderDetails>> orderDetais = new SuperVisorOperations().getOrderDetails(superRoot.getStoreIds(),
				superRoot.getStatus());
		return Response.ok(new Gson().toJson(orderDetais), MediaType.TEXT_PLAIN).build();

	}

	@Path("/count")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response OrderCounts(ArrayList<String> storeList, String orderStatus) {

		ArrayList<String> orderList = new SuperVisorOperations().getOrderCounts(storeList, orderStatus);
		return Response.ok(orderList.size(), MediaType.APPLICATION_JSON).build();

	}

}
