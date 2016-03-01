package com.chaipoint.deliveryappapis;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.chaipoint.dphelper.RegionHelper;
import com.chaipoint.helperclasses.StoreLocation;
import com.google.gson.Gson;
@Path("/region")
public class RegionAPI {
	@Path("/place")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllRegions() {
		System.out.println("reached here");
		Map<String, String> regionToRegionId= new RegionHelper().regions();
		String storeJson = new Gson().toJson(regionToRegionId);
		return Response.ok(storeJson, MediaType.APPLICATION_JSON).build();

	}

	
	@Path("/place/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response regionWiseStores(@PathParam("id") String id) {
		System.out.println("reached here");
		Map<Integer, StoreLocation> storeLocations = new RegionHelper().stores(id);
		String storeJson = new Gson().toJson(storeLocations);
		return Response.ok(storeJson, MediaType.APPLICATION_JSON).build();

	}

}
