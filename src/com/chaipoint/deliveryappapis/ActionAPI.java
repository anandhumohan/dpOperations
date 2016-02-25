package com.chaipoint.deliveryappapis;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.chaipoint.ninja.NinjaOperations;

@Path("/action")
public class ActionAPI {
	@Path("/cancel")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<String> cancelAction() {

		ArrayList<String> cancelResnList = new ArrayList<String>();
		// read reason listing file and put it in cancelReasnList
		return cancelResnList;

	}

	@Path("/reason")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public String storeReason(String storeId, String orderId, String reason) {

		// update in cpos table with reason
		String status = new NinjaOperations().saveCancelreason(orderId, reason);

		return status;

	}

	@Path("/confirm")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public String confirmAction(String orderId) {

		String status = new NinjaOperations().updateOrderStatus(orderId);
		return status;

	}

	@Path("/transfer")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)

	public ArrayList<String> transferAction(String storeId) {
		ArrayList<String> transList = new ArrayList<String>();
		// will return all transferable store names
		transList = new NinjaOperations().getAllTrasferStores(storeId);
		return transList;

	}

	@Path("/transferdone")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)

	public String transferOrder(String storeId, String orderId) {

		// will return all transferable store names
		String status = new NinjaOperations().transferOrder(storeId, orderId);

		return status;

	}

	@Path("/ready")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)

	public String readyAction() {
		return null;

	}

	@Path("/dispatch")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)

	public String dispatchAction() {
		return null;

	}

	@Path("/reassign")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)

	public String reassignAction(String storeId) {
		// DPS with status available at store, ofd, returning to store along
		// with their activity status.
		// buttons for third party dps
		return null;

	}

	@Path("/track")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)

	public String trackOrderAction() {
		return null;

	}

}
