package com.chaipoint.deliveryappapis;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.chaipoint.pushnotification.push;
import com.chaipoint.pushnotification.regIdOperations;
import com.squareup.okhttp.Response;

@Path("/notification")
public class PushNotificationAPI {
	@Path("/send")
	@POST
	public Response sendMessage() {
		ArrayList<String> regIds = new ArrayList<String>();  
		String status = new push().sendMessage(regIds);

		return null;

	}

	@Path("/saveid")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response regIdSave(String mtfId, String regId) {
		String status = new regIdOperations().saveId(mtfId, regId);
		return null;

	}

}
