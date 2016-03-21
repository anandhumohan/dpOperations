package com.chaipoint.pushnotification;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.chaipoint.dppojos.GCMRegIds;
import com.chaipoint.hibernatehelper.HibernateTemplate;

public class regIdOperations {
	HibernateTemplate template = null;

	public String saveId(String mtfId, String regId) {
		GCMRegIds regIds = new GCMRegIds();
		regIds.setMtfId(mtfId);
		regIds.setRegId(regId);
		regIds.setCreatedDate(new Date());
		regIds.setUpdatedDate(new Date());
		regIds.setActive(true);
		try {
			getTemplate().save(regIds);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return regId;

	}

	public String getRegId(String mtfId) {

		Criteria criteria = getTemplate().getSession().createCriteria(GCMRegIds.class);
		criteria.add(Restrictions.eq("mtfId", mtfId));
		criteria.setProjection(Projections.property("regId"));
		ArrayList<String> DPList = (ArrayList<String>) template.get(criteria);
		return DPList.get(0);

	}

	public String sendmessage(ArrayList<String> mtfIds) {
		
		//send message method
		return null;
	}

	public HibernateTemplate getTemplate() {
		if (template == null) {
			template = new HibernateTemplate();
		}
		return template;
	}
}
