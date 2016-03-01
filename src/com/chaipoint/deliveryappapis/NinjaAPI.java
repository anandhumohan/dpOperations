package com.chaipoint.deliveryappapis;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.ninja.NinjaOperations;

@Path("/ninja")
public class NinjaAPI {
	@Path("/default/{storeId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newState(@PathParam("storeId") String storeId) {
		String orderState = "NEW";

		Map<String, ArrayList<OrderDetails>> orderDetais = new NinjaOperations().storeOrderDetails(storeId);
		return Response.ok(orderDetais, MediaType.TEXT_PLAIN).build();

	}

	@Path("/count")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response OrderStatuscount(String data) {
		String state = "";
		String token = "";
		Map<String, Integer> orderDetais = new NinjaOperations().getAllCounts(state, token);
		return Response.ok(orderDetais, MediaType.TEXT_PLAIN).build();

	}

}
