package com.chaipoint.dphelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.chaipoint.constants.Constants;
import com.chaipoint.deliverypartner.DpOperations;
import com.chaipoint.helperclasses.LoginMsg;
import com.chaipoint.hibernatehelper.HibernateTemplate;

public class LoginHelper {

	public static Map<Integer, Boolean> storeNinjaAssign = new HashMap<Integer, Boolean>();

	HibernateTemplate template = null;

	public static Constants constants = new Constants();

	public LoginMsg login(ArrayList<Integer> storeId, String mtfId, String password, String roleId) {

		LoginMsg loginMsg = new LoginMsg();
		/*
		 * if (roleId.equalsIgnoreCase(Constants.role_ninja)) {
		 * 
		 * // check already attached any particular
		 * 
		 * if (!storeNinjaAssign.get(storeId.get(0)) == true) {
		 * loginMsg.setMessage("A ninja is attached to the store already");
		 * 
		 * } else { storeNinjaAssign.put(storeId.get(0), true); // call to
		 * authentication loginMsg.setMessage("SUCCESS");
		 * loginMsg.setToken("ABCD"); }
		 * 
		 * }
		 * 
		 */
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
