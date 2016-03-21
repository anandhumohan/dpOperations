package com.chaipoint.deliveryappapis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.chaipoint.constants.Constants;
import com.chaipoint.helperclasses.CancelRoot;
import com.chaipoint.helperclasses.LoginCredentials;
import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.helperclasses.RootOrderList;
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
		RootOrderList rootOrder = new RootOrderList();
		
		SuperVisionRoot superRoot = new Gson().fromJson(superJson, SuperVisionRoot.class);
		Map<String, ArrayList<OrderDetails>> orderDetais = new SuperVisorOperations().getOrderDetails(superRoot.getStoreIds(),
				superRoot.getStatus());
		rootOrder.setOrderList(orderDetais);
		if(orderDetais.get(superRoot.getStatus()).isEmpty()){
			rootOrder.setMessage("No orders");
		}
		else{
			rootOrder.setMessage(Constants.success);
		}
	
		return Response.ok(new Gson().toJson(rootOrder), MediaType.TEXT_PLAIN).build();
	//	return Response.ok(new Gson().toJson(orderDetais), MediaType.TEXT_PLAIN).build();

	}
/*
	@Path("/allcount")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response OrderCounts(String storeIds) {
		 LoginCredentials credentials = new Gson().fromJson(storeIds, LoginCredentials.class);


		Map<String, Long> orderDetais = new SuperVisorOperations().getAllOrderStatusCounts(credentials.getStoreIds());
		return Response.ok(new Gson().toJson(orderDetais), MediaType.TEXT_PLAIN_TYPE).build();

	}
*/
}
