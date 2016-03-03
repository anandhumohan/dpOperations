package com.chaipoint.dphelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.chaipoint.dppojos.LocationMaster;
import com.chaipoint.dppojos.StoreMaster;
import com.chaipoint.helperclasses.RegionId;
import com.chaipoint.helperclasses.StoreLocation;
import com.chaipoint.hibernatehelper.HibernateOperations;
import com.chaipoint.hibernatehelper.HibernateTemplate;

public class RegionHelper {
	HibernateOperations template = null;

	public Map<Integer, StoreLocation> getStores(String regionId) {
		int locationId = Integer.parseInt(regionId);
		Map<Integer, StoreLocation> storeLocations = new LinkedHashMap<Integer, StoreLocation>();
		Criteria criteria = getTemplate().getSession().createCriteria(StoreMaster.class);
		criteria.add(Restrictions.eq("locationId", locationId));
		criteria.addOrder(Order.asc("name"));
		// criteria.add(Restrictions.eq("active", true));
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("id"));
		projectionList.add(Projections.property("name"));
		projectionList.add(Projections.property("latitude"));
		projectionList.add(Projections.property("longitude"));
		criteria.setProjection(projectionList);

		ArrayList<Object[]> locationList = (ArrayList<Object[]>) getTemplate().get(criteria);
		for (Object[] list : locationList) {

			StoreLocation location = new StoreLocation();

			location.setName(list[1].toString());
			location.setLatitude((double) list[2]);
			location.setLongitude((double) list[3]);
			storeLocations.put((int) list[0], location);

		}

		return storeLocations;
	}

	public Map<String, String> getRegions() {

		Map<String, String> regionMap = new HashMap<String, String>();
		Criteria criteria = getTemplate().getSession().createCriteria(LocationMaster.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("name"));
		projectionList.add(Projections.property("id"));
		criteria.setProjection(projectionList);

		ArrayList<Object[]> regionList = (ArrayList<Object[]>) getTemplate().get(criteria);

		System.out.println(regionList.get(0));
		for (Object[] list : regionList) {
			regionMap.put(list[0].toString(), list[1].toString());
			System.out.println(list[0]);
			System.out.println(list[1]);

		}
		Map<String, String> map = new TreeMap<String, String>(regionMap);
		return map;

	}

	public HibernateOperations getTemplate() {
		if (template == null) {
			template = new HibernateOperations();
		}
		return template;
	}

}
