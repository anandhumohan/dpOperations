package com.chaipoint.dphelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.chaipoint.constants.Constants;
import com.chaipoint.deliveryappapis.HelperAPI;
import com.chaipoint.deliverypartner.DpOperations;
import com.chaipoint.helperclasses.LoginMsg;
import com.chaipoint.hibernatehelper.HibernateTemplate;

public class LoginHelper {

	public static Map<Integer, Boolean> storeNinjaAssign = new HashMap<Integer, Boolean>();
	public static Map<String, Boolean> dpLogincheck = new HashMap<String, Boolean>();

	HibernateTemplate template = null;

	public static Constants constants = new Constants();

	public LoginMsg login(ArrayList<Integer> storeId, String mtfId, String password, String roleId) {

		LoginMsg loginMsg = new LoginMsg();
		loginMsg.setName(HelperAPI.mtfIdNames.get(mtfId.toUpperCase()));
		
		if (roleId.equalsIgnoreCase(constants.role_ninja)) {
			loginMsg.setMessage("SUCCESS");
			loginMsg.setToken("ABCD");

			// API call to super visor default screen

		}

		
		// validate user and role and will get one token

		if (roleId.equalsIgnoreCase(constants.role_super_visor)) {
			loginMsg.setMessage("SUCCESS");
			loginMsg.setToken("ABCD");

			// API call to super visor default screen

		}

		if (roleId.equalsIgnoreCase(constants.role_dp))

		{
			loginMsg.setMessage("SUCCESS");
			loginMsg.setToken("ABCD");

			String status = new DpOperations().initialOperations(storeId.get(0), mtfId);

			// API call to super visor default screen

		}

		return loginMsg;

	}

}
