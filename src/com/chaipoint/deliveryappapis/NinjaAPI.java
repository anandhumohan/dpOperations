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
	
	
	
/*
  @Path("/new/{orderId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newState(@PathParam("orderId") String orderId) {
		String orderState = "NEW";
		 
		OrderDetails orderDetais = new NinjaOperations().newState(orderId, orderState);
		return Response.ok(orderDetais, MediaType.TEXT_PLAIN).build();

	}
	
	

	@Path("/confirm/{orderId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response confirmState(@PathParam("orderId") String orderId) {
		String orderState = "CONFIRM";
		String status = new NinjaOperations().confirmState(orderId, orderState);
		return Response.ok(status, MediaType.TEXT_PLAIN).build();

	}

	@Path("/ready/{orderId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response readyState(@PathParam("orderId") String orderId) {
		String orderState = "READY";
		String status = new NinjaOperations().readyState(orderId, orderState);
		return Response.ok(status, MediaType.TEXT_PLAIN).build();

	}

	@Path("/transfer")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response reasignState() {
		String orderState = "TRANSFER";
		String transferReason = "";
		String orderId = null;
		String newstoreId = null;
		String status = new NinjaOperations().transferState(orderId, orderState, newstoreId, transferReason);
		return Response.ok(status, MediaType.TEXT_PLAIN).build();

	}

	@Path("/reassign")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response transferState() {
		String orderState = "REASSIGN";
		String orderId = null;
		String oldDPId = null;
		String newDPId = null;
		String status = new NinjaOperations().reassignState(orderId, orderState, oldDPId, newDPId);
		return Response.ok(status, MediaType.TEXT_PLAIN).build();

	}

	@Path("/cancel")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancelState() {
		String orderState = "CANCELLED";
		String cancelReason = "";
		String orderId = null;
		String status = new NinjaOperations().cancelState(orderId, orderState, cancelReason);
		return Response.ok(status, MediaType.TEXT_PLAIN).build();

	}
	*/
}
