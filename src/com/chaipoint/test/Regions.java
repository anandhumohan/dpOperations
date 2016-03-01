package com.chaipoint.test;

import java.util.Map;

import org.hibernate.sql.Template;
import org.slf4j.spi.LocationAwareLogger;

import com.chaipoint.dphelper.RegionHelper;
import com.chaipoint.dppojos.LocationMaster;
import com.chaipoint.hibernatehelper.HibernateTemplate;

public class Regions {
public static	HibernateTemplate template = new HibernateTemplate();

	public static void main(String[] args) {
		LocationMaster locationMaster = new LocationMaster();
		locationMaster.setName("Bangalore");
		locationMaster.setCity("Bangalore");
		locationMaster.setState("Karnataka");
		locationMaster.setZone("South");
		try {
			template.save(locationMaster);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		LocationMaster location = new LocationMaster();
		location.setName("Chennai");
		location.setCity("Chennai");
		location.setState("Tamil Nadu");
		location.setZone("South");
		
		try {
			template.save(location);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		Map<String, String> regionToRegionId= new RegionHelper().regions();
		System.out.println(regionToRegionId);		
		System.out.println("reached here");

	}

}
