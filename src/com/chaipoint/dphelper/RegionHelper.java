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

	public Map<String, StoreLocation> region(String region) {
		Map<String, StoreLocation> storeLocations = new HashMap<String, StoreLocation>();
		int locationId = getLocationId(region);
		storeLocations = getStores(locationId, storeLocations);

		return storeLocations;
	}

	public int getLocationId(String region) {
		Criteria criteria = template.getSession().createCriteria(LocationMaster.class);
		criteria.add(Restrictions.eq("name", region));
		criteria.setProjection(Projections.property("id"));
		ArrayList<Integer> DPList = (ArrayList<Integer>) getTemplate().get(criteria);
		return DPList.get(0);
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

	public HibernateTemplate getTemplate() {
		if (template == null) {
			template = new HibernateTemplate();
		}
		return template;
	}
}
