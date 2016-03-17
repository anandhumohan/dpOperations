package com.chaipoint.deliveryappapis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.soap.MTOMFeature;

import org.json.simple.JSONObject;

import com.chaipoint.constants.Constants;
import com.chaipoint.deliverypartner.DpOperations;
import com.chaipoint.helperclasses.CancelRoot;
import com.chaipoint.helperclasses.DpAtStore;
import com.chaipoint.helperclasses.OrderDetails;
import com.chaipoint.helperclasses.RootOrderList;
import com.chaipoint.helperclasses.RootUpdate;
import com.chaipoint.ninja.NinjaOperations;
import com.google.gson.Gson;

@Path("/ninjascreen")
public class NinjaAPI {
	
	
	
	@Path("/orderdetailsId")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response OrderDetailsUsingId(@QueryParam("orderId") int orderId) {
		// String status = "New";
		RootOrderList rootOrder = new RootOrderList();
		Map<String, ArrayList<OrderDetails>> orderDetais = new NinjaOperations().getOrderDetailsById(orderId);
		if (orderDetais.values() == null) {
			return Response.ok("NO OREDERS", MediaType.TEXT_PLAIN).build();
		} else {
			rootOrder.setOrderList(orderDetais);
		//	Map<String, Long> count = new NinjaOperations().getAllCounts(storeId);
		//	rootOrder.setOrderCount(count);
			return Response.ok(new Gson().toJson(rootOrder), MediaType.TEXT_PLAIN).build();
		}
	}

	@Path("/orderdetails")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newState(@QueryParam("storeId") int storeId, @QueryParam("status") String status) {
		// String status = "New";
		RootOrderList rootOrder = new RootOrderList();
		Map<String, ArrayList<OrderDetails>> orderDetais = new NinjaOperations().getOrderDetails(storeId, status);
		if (orderDetais.values() == null) {
			return Response.ok("NO OREDERS", MediaType.TEXT_PLAIN).build();
		} else {
			rootOrder.setOrderList(orderDetais);
			Map<String, Long> count = new NinjaOperations().getAllCounts(storeId);
		//	rootOrder.setOrderCount(count);
			return Response.ok(new Gson().toJson(rootOrder), MediaType.TEXT_PLAIN).build();
		}
	}

	@Path("/orderdetailsfinal")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newStateTest(@QueryParam("storeId") int storeId, @QueryParam("status") String status) {
		// String status = "New";
		RootOrderList rootOrder = new RootOrderList();
		Map<String, ArrayList<OrderDetails>> orderDetais = new NinjaOperations().getOrderDetailsTest(storeId, status);

		rootOrder.setOrderList(orderDetais);
	//	Map<String, Long> count = new NinjaOperations().getAllCounts(storeId);
	//	rootOrder.setOrderCount(count);
		if(orderDetais.get(status).isEmpty()){
			rootOrder.setMessage("No orders");
		}
		else{
			rootOrder.setMessage(Constants.success);
		}
		return Response.ok(new Gson().toJson(rootOrder), MediaType.TEXT_PLAIN).build();

	}
	
	@Path("/orderdetailstestopt")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newStateTestopt(@QueryParam("storeId") int storeId, @QueryParam("status") String status) {
		// String status = "New";
		RootOrderList rootOrder = new RootOrderList();
		Map<String, ArrayList<OrderDetails>> orderDetais = new NinjaOperations().getOrderDetailsTestopt(storeId, status);

		rootOrder.setOrderList(orderDetais);
	//	Map<String, Long> count = new NinjaOperations().getAllCounts(storeId);
	//	rootOrder.setOrderCount(count);
		return Response.ok(new Gson().toJson(rootOrder), MediaType.TEXT_PLAIN).build();

	}
	
	@Path("/orderdetailstest")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newStateTestfinal(@QueryParam("storeId") int storeId, @QueryParam("status") String status) {
		// String status = "New";
		RootOrderList rootOrder = new RootOrderList();
		Map<String, ArrayList<OrderDetails>> orderDetais = new NinjaOperations().getOrderDetailsFinal(storeId, status);

		rootOrder.setOrderList(orderDetais);
		if(orderDetais.get(status).isEmpty()){
			rootOrder.setMessage("No orders");
		}
		else{
			rootOrder.setMessage(Constants.success);
		}
	//	Map<String, Long> count = new NinjaOperations().getAllCounts(storeId);
	//	rootOrder.setOrderCount(count);
		return Response.ok(new Gson().toJson(rootOrder), MediaType.TEXT_PLAIN).build();

	}
	

	@Path("/count")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response OrderStatuscount(@QueryParam("storeId") int storeId) {
	//	int storeId = 102;
		Map<String, Long> orderDetais = new NinjaOperations().getAllCounts(storeId);
		return Response.ok(new Gson().toJson(orderDetais), MediaType.TEXT_PLAIN_TYPE).build();

	}

	@Path("/update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response OrderStatusUpdate(String json) {
		RootUpdate rootUpdate = new Gson().fromJson(json, RootUpdate.class);

		int orderId = rootUpdate.getOrderId();
		String status = rootUpdate.getStatus();
		
		int storeId = rootUpdate.getStoreId();
		String code = new NinjaOperations().updateOrderStatus(orderId, status, storeId);
		return Response.ok(code, MediaType.TEXT_PLAIN_TYPE).build();

	}

	@Path("/cancel")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancelReasonUpdate(String cancelJson) {

		CancelRoot cancelRoot = new Gson().fromJson(cancelJson, CancelRoot.class);
		int orderId = cancelRoot.getOrderId();
		int reasonId = cancelRoot.getReasonId();

		String code = new NinjaOperations().saveCancelreason(orderId, reasonId);
		return Response.ok(code, MediaType.TEXT_PLAIN_TYPE).build();

	}
//when some one press the assign button
	@Path("/dpatstore")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response assignDpManually(String json) {
		int storeId = 0;
		DpAtStore atStore = new DpAtStore();
		Map<String, String> names = new HashMap<String, String>();
		ArrayList<String> dpAtstores = new DpOperations().getAllDpAtStore(storeId);
		if(dpAtstores.size() == 0){
			atStore.setMessage("No dp");
			
		}else{
			atStore.setMessage(Constants.success);
			for(String id : dpAtstores){
				String s = HelperAPI.mtfIdNames.get(id);
				names.put(id, s);
			}
			
			atStore.setDpIdNames(names);
		}
		
		return Response.ok(atStore, MediaType.TEXT_PLAIN_TYPE).build();

	}
	
	@Path("/getalldps")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response dplist(@QueryParam("storeId") int storeId) {
	//	RootUpdate rootUpdate = new Gson().fromJson(json, RootUpdate.class);
		Map<String, String> nameMtfidMap = new NinjaOperations().manualAssign(storeId);
		return Response.ok(new Gson().toJson(nameMtfidMap), MediaType.TEXT_PLAIN_TYPE).build();

	}
	
	@Path("/manualdispatch")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response manualDispatch(String json) {
		RootUpdate rootUpdate = new Gson().fromJson(json, RootUpdate.class);
		
		String status = new NinjaOperations().dplist(rootUpdate.getMtfId(), rootUpdate.getStoreId(), rootUpdate.getOrderId());
		
		return Response.ok(status, MediaType.TEXT_PLAIN_TYPE).build();

	}
	
	
	
	
}
