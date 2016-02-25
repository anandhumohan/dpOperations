package com.chaipoint.deliveryappapis;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.chaipoint.dphelper.LoginHelper;
import com.chaipoint.helperclasses.LoginCredentials;
import com.google.gson.Gson;

@Path("/login")
public class loginAPI {
	@Path("/member")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response memberLogin(String credentials) {
		LoginCredentials logincred = new Gson().fromJson(credentials, LoginCredentials.class);
		String status = new LoginHelper().login(logincred.getStoreId(), logincred.getMtfId(), logincred.getPassword(),
				logincred.getRoleid());
		return Response.ok(status, MediaType.APPLICATION_JSON).build();

	}

}
