package com.chaipoint.deliveryappapis;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.chaipoint.dppojos.StoreMaster;
import com.chaipoint.hibernatehelper.HibernateOperations;

public class HelperAPI {
	HibernateOperations template = null;
	public static Map<String, String> channelIdNames = new HashMap<String, String>();
	public void getAllChannelNames(){
		
		Criteria criteria = getTemplate().getSession().createCriteria(StoreMaster.class);
	//	criteria.add(Restrictions.eq("locationId", locationId));
		criteria.add(Restrictions.eq("active", 'Y'));
		
	}
	
	public HibernateOperations getTemplate(){
		if(template == null){
			template = new HibernateOperations();
		}
		return template;
	}

}
