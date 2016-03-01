package com.chaipoint.dphelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.chaipoint.dppojos.LocationMaster;
import com.chaipoint.dppojos.StoreMaster;
import com.chaipoint.helperclasses.StoreLocation;
import com.chaipoint.hibernatehelper.HibernateTemplate;

public class RegionHelper {
	HibernateTemplate template = null;

	public Map<Integer, StoreLocation> stores(String regionId) {
		Map<Integer, StoreLocation> storeLocations = new HashMap<Integer, StoreLocation>();
		// int locationId = getLocationId(region);
		Criteria criteria = template.getSession().createCriteria(StoreMaster.class);
		criteria.add(Restrictions.eq("location_id", regionId));
		criteria.add(Restrictions.eq("active", true));
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("storeId"));
		projectionList.add(Projections.property("name"));
		projectionList.add(Projections.property("latitude"));
		projectionList.add(Projections.property("longiude"));
		criteria.setProjection(projectionList);
		ArrayList<StoreLocation> locationList = (ArrayList<StoreLocation>) getTemplate().get(criteria);

		for (StoreLocation stores : locationList) {
			storeLocations.put(stores.getStoreId(), stores);
		}
		return storeLocations;
	}

	public Map<String, StoreLocation> getStores(int locationId, Map<String, StoreLocation> storeLocations) {
		Criteria criteria = template.getSession().createCriteria(StoreMaster.class);
		criteria.add(Restrictions.eq("location_id", locationId));
		criteria.add(Restrictions.eq("active", true));
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("name"));
		projectionList.add(Projections.property("latitude"));
		projectionList.add(Projections.property("longiude"));
		criteria.setProjection(projectionList);
		ArrayList<String> DPList = (ArrayList<String>) getTemplate().get(criteria);

		for (int i = 0; i < DPList.size(); i += 3) {
			StoreLocation location = new StoreLocation();
			location.setLatitude(Double.parseDouble(DPList.get(i + 1)));
			location.setLongitude(Double.parseDouble(DPList.get(i + 2)));
			storeLocations.put(DPList.get(i + 0), location);
		}
		return storeLocations;
	}

	public Map<String, String> regions() {

		Map<String, String> regionMap = new HashMap<String, String>();
		Criteria criteria = getTemplate().getSession().createCriteria(LocationMaster.class);
		// criteria.add(Restrictions.eq("name", region));
		criteria.setProjection(Projections.property("name"));
		criteria.setProjection(Projections.property("id"));
		ArrayList<String> regionList = (ArrayList<String>) getTemplate().get(criteria);
		System.out.println(regionList);
		for (int i = 0; i < regionList.size(); i += 2) {
			regionMap.put(regionList.get(i), regionList.get(i + 1));
		}
		return regionMap;

	}

	public HibernateTemplate getTemplate() {
		if (template == null) {
			template = new HibernateTemplate();
		}
		return template;
	}

}
