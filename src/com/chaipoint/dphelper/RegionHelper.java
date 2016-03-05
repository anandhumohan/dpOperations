package com.chaipoint.dphelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.chaipoint.helperclasses.Regions;
import com.chaipoint.helperclasses.StoreLocation;
import com.chaipoint.hibernatehelper.HibernateOperations;
import com.chaipoint.hibernatehelper.HibernateTemplate;

public class RegionHelper {
	HibernateOperations template = null;

	public ArrayList<StoreLocation> getStores(String regionId) {
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
		ArrayList<StoreLocation> locations = new ArrayList<StoreLocation>();
		for (Object[] list : locationList) {

			StoreLocation location = new StoreLocation();
			location.setId((int) list[0]);
			location.setName(list[1].toString());
			location.setLatitude((double) list[2]);
			location.setLongitude((double) list[3]);
			storeLocations.put((int) list[0], location);
			locations.add(location);

		}

		// return locations;

		Comparator<StoreLocation> storeComparator = new Comparator<StoreLocation>() {

			public int compare(StoreLocation s1, StoreLocation s2) {
				String StoreName1 = s1.getName().toUpperCase();
				String StoreName2 = s2.getName().toUpperCase();

				// ascending order
				return StoreName1.compareTo(StoreName2);

				// descending order
				// return StudentName2.compareTo(StudentName1);
			}
		};
		Collections.sort(locations, storeComparator);
		return locations;

	}

	public ArrayList<Regions> getRegions() {

		Map<String, String> regionMap = new HashMap<String, String>();
		Criteria criteria = getTemplate().getSession().createCriteria(LocationMaster.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("name"));
		projectionList.add(Projections.property("id"));
		criteria.setProjection(projectionList);

		ArrayList<Object[]> regionList = (ArrayList<Object[]>) getTemplate().get(criteria);
		ArrayList<Regions> regions = new ArrayList<Regions>();
		System.out.println(regionList.get(0));
		for (Object[] list : regionList) {
			Regions region = new Regions();
			region.setName(list[0].toString());
			region.setId(list[1].toString());
			regions.add(region);
			// regionMap.put(list[0].toString(), list[1].toString());
			// System.out.println(list[0]);
			// System.out.println(list[1]);

		}
		// Map<String, String> map = new TreeMap<String, String>(regionMap);
		return regions;

	}

	public HibernateOperations getTemplate() {
		if (template == null) {
			template = new HibernateOperations();
		}
		return template;
	}

}
