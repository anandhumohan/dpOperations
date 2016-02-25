package com.chaipoint.dppojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.google.gson.Gson;

@Entity
@Table(name = "STORE_MASTER")
public class StoreMaster {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "STORE_ID", unique = true)
	private String storeId;

	@Column(name = "STORE_NAME", unique = true)
	private String storeName;

	@Column(name = "STORE_CITY")
	private String storeCity;

	@Column(name = "LATITUDE")
	private double storeLatitude;

	@Column(name = "LONGITUDE")
	private double storeLongitude;

	@Column(name = "RADIUS")
	private double deliveryRadius;

	@Column(name = "POLYGONS", length = 1500)
	private String polygons;

	@Column(name = "POLYGONS_ACTIVE")
	private int polygonActive;

	@Column(name = "STORE_MANAGER_ID")
	private String storeManager;

	@Column(name = "ADDRESS")
	private String storeAddress;

	@Column(name = "IS_ACTIVE")
	private boolean isActive;

	@Column(name = "TOTAL_EMPLOYEE")
	private int totalEmployee;

	@Column(name = "WEEKDAY_EMPLOYEE")
	private int weekdaysEmployee;

	@Column(name = "SATURDAY_EMPLOYEE")
	private int saturdayEmployee;

	@Column(name = "SUNDAY_EMPLOYEE")
	private int sundayEmployee;

	@Column(name = "OPENING_TIME")
	@Type(type = "time")
	private Date openingTime;

	@Column(name = "CLOSING_TIME")
	@Type(type = "time")
	private Date closingTime;

	@Column(name = "WEEKOFF")
	private String weekOff;

	@Column(name = "STORE_PICTURE_LOCATION", length = 1000)
	private String storePictureLocation;

	@Column(name = "STORE_DELIVERY_PARTNER")
	private String storeDeliveryPartner;

	@Column(name = "IS_AC_STORE")
	private boolean isAcStore;

	@Column(name = "STORE_TYPE")
	private String storetype;

	@Column(name = "ORDER_ACCEPTED_SOURCE")
	private String orderAcceptedSource;

	@Column(name = "AUTO_INDENT")
	private boolean autoIndent;

	@Column(name = "IS_24_HOUR_OPEN")
	private boolean is24HourStore;

	@Column(name = "STORE_CONTACT_NUMBERS")
	private String storeContactNumber;

	@Column(name = "TIN_NO")
	private String tinNo;

	@Column(name = "STN_NO")
	private String stnNo;

	@Column(name = "PPC_ID")
	private String ppcId;

	@Column(name = "PPC_PASSWORD")
	private String ppcPwd;

	@Column(name = "PPA_ID")
	private String ppaId;

	@Column(name = "PPA_PASSWORD")
	private String ppaPwd;

	@Column(name = "PPA_TID")
	private String ppaTid;

	@Column(name = "STORE_TAXES")
	private String storeTaxes;

	public String getStoreId() {
		return storeId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreCity() {
		return storeCity;
	}

	public void setStoreCity(String storeCity) {
		this.storeCity = storeCity;
	}

	public double getStoreLatitude() {
		return storeLatitude;
	}

	public void setStoreLatitude(double storeLatitude) {
		this.storeLatitude = storeLatitude;
	}

	public double getStoreLongitude() {
		return storeLongitude;
	}

	public void setStoreLongitude(double storeLongitude) {
		this.storeLongitude = storeLongitude;
	}

	public double getDeliveryRadius() {
		return deliveryRadius;
	}

	public void setDeliveryRadius(double deliveryRadius) {
		this.deliveryRadius = deliveryRadius;
	}

	public ArrayList<ArrayList<String>> getPolygons() {
		return new Gson().fromJson(polygons, ArrayList.class);
	}

	public void setPolygons(ArrayList<ArrayList<String>> polygons) {
		this.polygons = new Gson().toJson(polygons);
	}

	public String getStoreManager() {
		return storeManager;
	}

	public void setStoreManager(String storeManager) {
		this.storeManager = storeManager;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getTotalEmployee() {
		return totalEmployee;
	}

	public void setTotalEmployee(int totalEmployee) {
		this.totalEmployee = totalEmployee;
	}

	public int getWeekdaysEmployee() {
		return weekdaysEmployee;
	}

	public void setWeekdaysEmployee(int weekdaysEmployee) {
		this.weekdaysEmployee = weekdaysEmployee;
	}

	public int getSaturdayEmployee() {
		return saturdayEmployee;
	}

	public void setSaturdayEmployee(int saturdayEmployee) {
		this.saturdayEmployee = saturdayEmployee;
	}

	public int getSundayEmployee() {
		return sundayEmployee;
	}

	public void setSundayEmployee(int sundayEmployee) {
		this.sundayEmployee = sundayEmployee;
	}

	public Date getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(Date openingTime) {
		this.openingTime = openingTime;
	}

	public Date getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(Date closingTime) {
		this.closingTime = closingTime;
	}

	public String getWeekOff() {
		return weekOff;
	}

	public void setWeekOff(String weekOff) {
		this.weekOff = weekOff;
	}

	public String getStorePictureLocation() {
		return storePictureLocation;
	}

	public void setStorePictureLocation(String storePictureLocation) {
		this.storePictureLocation = storePictureLocation;
	}

	public ArrayList<String> getStoreDeliveryPartner() {
		return new Gson().fromJson(storeDeliveryPartner, ArrayList.class);
	}

	public void setStoreDeliveryPartner(ArrayList<String> storeDeliveryPartner) {
		this.storeDeliveryPartner = new Gson().toJson(storeDeliveryPartner);
	}

	public boolean isAcStore() {
		return isAcStore;
	}

	public void setAcStore(boolean isAcStore) {
		this.isAcStore = isAcStore;
	}

	public String getStoretype() {
		return storetype;
	}

	public void setStoretype(String storetype) {
		this.storetype = storetype;
	}

	public ArrayList<String> getOrderAcceptedSource() {
		return new Gson().fromJson(orderAcceptedSource, ArrayList.class);
	}

	public void setOrderAcceptedSource(ArrayList<String> orderAcceptedSource) {
		this.orderAcceptedSource = new Gson().toJson(orderAcceptedSource);
	}

	public boolean isAutoIndent() {
		return autoIndent;
	}

	public void setAutoIndent(boolean autoIndent) {
		this.autoIndent = autoIndent;
	}

	public boolean isIs24HourStore() {
		return is24HourStore;
	}

	public void setIs24HourStore(boolean is24HourStore) {
		this.is24HourStore = is24HourStore;
	}

	public String getStoreContactNumber() {
		return storeContactNumber;
	}

	public void setStoreContactNumber(String storeContactNumber) {
		this.storeContactNumber = storeContactNumber;
	}

	public String getTinNo() {
		return tinNo;
	}

	public void setTinNo(String tinNo) {
		this.tinNo = tinNo;
	}

	public String getStnNo() {
		return stnNo;
	}

	public void setStnNo(String stnNo) {
		this.stnNo = stnNo;
	}

	public String getPpcId() {
		return ppcId;
	}

	public void setPpcId(String ppcId) {
		this.ppcId = ppcId;
	}

	public String getPpcPwd() {
		return ppcPwd;
	}

	public void setPpcPwd(String ppcPwd) {
		this.ppcPwd = ppcPwd;
	}

	public String getPpaId() {
		return ppaId;
	}

	public void setPpaId(String ppaId) {
		this.ppaId = ppaId;
	}

	public String getPpaPwd() {
		return ppaPwd;
	}

	public void setPpaPwd(String ppaPwd) {
		this.ppaPwd = ppaPwd;
	}

	public String getPpaTid() {
		return ppaTid;
	}

	public void setPpaTid(String ppaTid) {
		this.ppaTid = ppaTid;
	}

	// public Map<String, TaxTypes> getStoreTaxes() {
	// return new Gson().fromJson(storeTaxes, Map.class);
	// }

	// public void setStoreTaxes(Map<String, TaxTypes> storeTaxes) {
	// this.storeTaxes = new Gson().toJson(storeTaxes);
	// }

	public int getId() {
		return id;
	}

	public int getPolygonActive() {
		return polygonActive;
	}

	public void setPolygonActive(int polygonActive) {
		this.polygonActive = polygonActive;
	}

}
