package com.chaipoint.deliveryappapis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.chaipoint.dppojos.LocationMaster;
import com.chaipoint.dppojos.StaffMaster;
import com.chaipoint.dppojos.StoreMaster;
import com.chaipoint.helperclasses.Regions;
import com.chaipoint.helperclasses.StoreLocation;
import com.chaipoint.hibernatehelper.HibernateOperations;

public class HelperAPI {
	HibernateOperations template = null;

	public static Map<Integer, String> storeIdName = new HashMap<Integer, String>();
	public static Map<String, String> mtfIdNames = new HashMap<String, String>();
	public static ArrayList<Regions> regions = new ArrayList<Regions>();
	public static Map<Integer, ArrayList<StoreLocation>> storeIdLocation = new HashMap<Integer, ArrayList<StoreLocation>>();

	public String getAllIdAndNames() {

		Criteria criteria = getTemplate().getSession().createCriteria(StaffMaster.class);
		// criteria.add(Restrictions.eq("locationId", locationId));
		criteria.add(Restrictions.eq("active", 'Y'));
		ArrayList<StaffMaster> names = (ArrayList<StaffMaster>) getTemplate().get(criteria);

		for (StaffMaster staffNames : names) {
			mtfIdNames.put(staffNames.getStaffCode(), staffNames.getStaffName());
		}
		System.out.println("reached here");
		getAllStoreIdNames();
		return "sucsess";
	}

	public void getAllStoreIdNames() {

		Criteria criteria = getTemplate().getSession().createCriteria(StoreMaster.class);
		// criteria.add(Restrictions.eq("locationId", locationId));
		criteria.add(Restrictions.eq("active", 'Y'));
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("id"));
		projectionList.add(Projections.property("name"));
		criteria.setProjection(projectionList);

		ArrayList<Object[]> locationList = (ArrayList<Object[]>) getTemplate().get(criteria);

		for (Object[] staff : locationList) {
			storeIdName.put((int) staff[0], staff[1].toString());
		}

		System.out.println("reached here");
		getAllregionNames();

	}

	public void getAllregionNames() {
		Criteria criteria = getTemplate().getSession().createCriteria(LocationMaster.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("name"));
		projectionList.add(Projections.property("id"));
		criteria.setProjection(projectionList);

		ArrayList<Object[]> regionList = (ArrayList<Object[]>) getTemplate().get(criteria);

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
		System.out.println("reached");
		getAllstoreNames();
	}

	public void getAllstoreNames() {
		Criteria criteria = getTemplate().getSession().createCriteria(LocationMaster.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("id"));
		criteria.setProjection(projectionList);

		ArrayList<Integer> regionList = (ArrayList<Integer>) getTemplate().get(criteria);
		for (Integer list : regionList) {
			Criteria cr = getTemplate().getSession().createCriteria(StoreMaster.class);
			cr.add(Restrictions.eq("locationId", list));
			cr.add(Restrictions.eq("active", 'Y'));
		//	criteria.addOrder(Order.asc("name"));
			// criteria.add(Restrictions.eq("active", true));
			ProjectionList projectionList1 = Projections.projectionList();
			projectionList1.add(Projections.property("id"));
			projectionList1.add(Projections.property("name"));
			projectionList1.add(Projections.property("latitude"));
			projectionList1.add(Projections.property("longitude"));
			cr.setProjection(projectionList1);

			ArrayList<Object[]> locationList = (ArrayList<Object[]>) getTemplate().get(cr);
			
			ArrayList<StoreLocation> locations = new ArrayList<StoreLocation>();
			for (Object[] list1 : locationList) {

				StoreLocation location = new StoreLocation();
				location.setId((int) list1[0]);
				location.setName(list1[1].toString());
				location.setLatitude((double) list1[2]);
				location.setLongitude((double) list1[3]);
			//	storeLocations.put((int) list1[0], location);
				locations.add(location);

			}
			storeIdLocation.put(list, locations);
			
		}
		
		
System.out.println("reached");

	}

	public HibernateOperations getTemplate() {
		if (template == null) {
			template = new HibernateOperations();
		}
		return template;
	}

}
