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
import com.google.gson.Gson;

@Path("/dp")
public class DeliveryPartnerAPI {
	@Path("/default")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setDpAfterLogin(String storeId, String mtfId) {

		// String DPId = new DPselector().setCofirmOrder(details.getStoreId(),
		// ids);

		String DPId = new DpOperations().initialOperations(storeId, mtfId);

		return Response.ok(DPId, MediaType.TEXT_PLAIN).build();

	}
	
	//5 buttons in dp page

	@Path("/confirmed")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response confirmedAction(String storeId) {

		ArrayList<String> orderList = new DpOperations().confirmActionDisplay(storeId);
		return Response.ok(orderList, MediaType.TEXT_PLAIN).build();

	}

	@Path("/ready")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response readyAction(String storeId) {

		ArrayList<String> orderList = new DpOperations().readyActionDisplay(storeId);
		return Response.ok(orderList, MediaType.TEXT_PLAIN).build();

	}

	@Path("/dispatched")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response dispatchedAction(String storeId, String mtfId) {

		ArrayList<String> orderList = new DpOperations().readyActionDisplay(storeId, mtfId);
		return Response.ok(orderList, MediaType.TEXT_PLAIN).build();

	}

	@Path("/delivered")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deliveredAction(String storeId, String mtfId) {

		ArrayList<String> orderList = new DpOperations().deliveredActionDisplay(storeId, mtfId);
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
