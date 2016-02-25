package com.chaipoint.dphelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.chaipoint.constants.Constants;
import com.chaipoint.hibernatehelper.HibernateTemplate;

public class LoginHelper {

	public static Map<String, Boolean> storeNinjaAssign = new HashMap<String, Boolean>();

	HibernateTemplate template = null;

	public static Constants constants = new Constants();

	public String login(ArrayList<String> storeId, String mtfId, String password, String roleId) {

		String msg = "";

		if (roleId == constants.role_ninja) {

			// check already attached any particular
			if (storeNinjaAssign.get(storeId) == true) {
				return "A ninja is attached to the store already";

			}
		}

		// validate user and role and will get one token
		String token = "";
		if (roleId == constants.role_super_visor && token != null)

		{

			// API call to super visor default screen

		} else

		{
			msg = "erorr";
		}

		if (roleId == constants.role_ninja && token != null) {
			storeNinjaAssign.put(storeId.get(0), true);
			// API call to super visor default screen
		} else

		{
			msg = "erorr";
		}

		if (roleId == constants.role_dp && token != null)

		{
			// API call to super visor default screen

		} else

		{
			msg = "erorr";
		}

		return msg;

	}

}
