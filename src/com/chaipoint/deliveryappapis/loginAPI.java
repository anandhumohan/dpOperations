package com.chaipoint.deliveryappapis;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.chaipoint.dphelper.LoginHelper;
import com.chaipoint.dphelper.RegionHelper;
import com.chaipoint.helperclasses.LoginCredentials;
import com.chaipoint.helperclasses.LoginMsg;
import com.chaipoint.helperclasses.Regions;
import com.chaipoint.helperclasses.RootRegions;
import com.chaipoint.helperclasses.RootStores;
import com.chaipoint.helperclasses.StoreLocation;
import com.google.gson.Gson;

@Path("/userlogin")
public class loginAPI {
	
	@Path("/getallregions")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getAllRegions() {
		System.out.println("reached here");

		RootRegions rootregions = new RootRegions();
		ArrayList<Regions> regionToRegionId = new RegionHelper().getRegions();
		rootregions.setRegions(regionToRegionId);
		String regionJson = new Gson().toJson(rootregions);
		return Response.ok(regionJson, MediaType.APPLICATION_JSON).build();

	}

	@Path("/getallstores")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response regionWiseStores(@QueryParam("regionId") String regionId) {
		System.out.println("reached here");
		RootStores rootStores = new RootStores();
		ArrayList<StoreLocation> storeLocations = new ArrayList<StoreLocation>();
		storeLocations = new RegionHelper().getStores(regionId);
		rootStores.setStores(storeLocations);
		String storeJson = new Gson().toJson(rootStores);
		return Response.ok(storeJson, MediaType.APPLICATION_JSON).build();

	}

	@Path("/userauth")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response memberLogin(String credentials) {
		LoginCredentials logincred = new Gson().fromJson(credentials, LoginCredentials.class);
		LoginMsg loginMsg = new LoginHelper().login(logincred.getStoreIds(), logincred.getMtfId(), logincred.getPassword(),
				logincred.getRole());
		
	//	LoginMsg loginMsg = new LoginMsg();
		return Response.ok(new Gson().toJson(loginMsg), MediaType.APPLICATION_JSON).build();

	}

}
