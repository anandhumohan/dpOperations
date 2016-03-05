package com.chaipoint.deliveryappapis;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.ninja.NinjaOperations;
import com.google.gson.Gson;

@Path("/ninjascreen")
public class NinjaAPI {

	@Path("/orderdetails/{storeId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newState(@PathParam("storeId") int storeId) {
		String status = "New";
		Map<String, ArrayList<OrderDetails>> orderDetais = new NinjaOperations().getOrderDetails(storeId, status);
		return Response.ok(orderDetais, MediaType.TEXT_PLAIN).build();

	}

	@Path("/count")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response OrderStatuscount() {

		Map<String, Integer> orderDetais = new NinjaOperations().getAllCounts();
		return Response.ok(new Gson().toJson(orderDetais), MediaType.TEXT_PLAIN_TYPE).build();

	}

	@Path("/update")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response OrderStatusUpdate(@QueryParam("orderId") int orderId, @QueryParam("status") String status) {

		String code = new NinjaOperations().updateOrderStatus(orderId, status);
		return Response.ok(code, MediaType.TEXT_PLAIN_TYPE).build();

	}
	
	@Path("/cancel")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancelReasonUpdate(@QueryParam("orderId") int orderId, @QueryParam("reason") String reason) {

		String code = new NinjaOperations().saveCancelreason(orderId, reason);
		return Response.ok(code, MediaType.TEXT_PLAIN_TYPE).build();

	}
	
	

}
