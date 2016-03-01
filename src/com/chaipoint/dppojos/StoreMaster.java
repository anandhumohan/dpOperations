package com.chaipoint.dppojos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "store_master")
public class StoreMaster {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "CODE", unique = true)
	private String code;

	@Column(name = "NAME", unique = true)
	private String name;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "TAX_RATE")
	private int taxRate;

	@Column(name = "SERVICE_TAX_RATE")
	private int serviceTaxRate;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "PHONE_1")
	private String phone1;

	@Column(name = "PHONE_2")
	private int phone2;

	@Column(name = "LOCATION_ID")
	private int locationId;

	@Column(name = "OPENING_TIME")
	@Type(type = "time")
	private Date openingTime;

	@Column(name = "CLOSING_TIME")
	@Type(type = "time")
	private Date closingTime;

	@Column(name = "WEEKLY_OFF")
	private boolean weeklyOff;

	@Column(name = "PHOTO")
	private String photo;

	@Column(name = "LATITUDE")
	private double latitude;

	@Column(name = "LONGITUDE")
	private double longitude;

	@Column(name = "SMS")
	private boolean sms;

	@Column(name = "STORE_TIME")
	private int storeTime;

	@Column(name = "CREATED_TIME")
	private Date createdTime;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_TIME")
	private Date updatedTime;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "ACTIVE")
	private boolean active;

	@Column(name = "FOE_ALLOWED")
	private boolean forAllowed;

	@Column(name = "WEEKDAYS_MANHOURS")
	private int weekdaysManhours;

	@Column(name = "SATURDAY_MANHOURS")
	private int saturdayManhours;

	@Column(name = "SUNDAY_MANHOURS")
	private int sundayManhours;

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

	@Column(name = "STORE_MESSAGE")
	private String storeMessage;

	@Column(name = "BILLING_TYPE")
	private String billingType;

	@Column(name = "IS_PT")
	private String isPt;

	@Column(name = "IS_AC")
	private boolean isAc;

	@Column(name = "WAREHOUSE_ID")
	private int warehouseId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(int taxRate) {
		this.taxRate = taxRate;
	}

	public int getServiceTaxRate() {
		return serviceTaxRate;
	}

	public void setServiceTaxRate(int serviceTaxRate) {
		this.serviceTaxRate = serviceTaxRate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public int getPhone2() {
		return phone2;
	}

	public void setPhone2(int phone2) {
		this.phone2 = phone2;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
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

	public boolean isWeeklyOff() {
		return weeklyOff;
	}

	public void setWeeklyOff(boolean weeklyOff) {
		this.weeklyOff = weeklyOff;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public boolean isSms() {
		return sms;
	}

	public void setSms(boolean sms) {
		this.sms = sms;
	}

	public int getStoreTime() {
		return storeTime;
	}

	public void setStoreTime(int storeTime) {
		this.storeTime = storeTime;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isForAllowed() {
		return forAllowed;
	}

	public void setForAllowed(boolean forAllowed) {
		this.forAllowed = forAllowed;
	}

	public int getWeekdaysManhours() {
		return weekdaysManhours;
	}

	public void setWeekdaysManhours(int weekdaysManhours) {
		this.weekdaysManhours = weekdaysManhours;
	}

	public int getSaturdayManhours() {
		return saturdayManhours;
	}

	public void setSaturdayManhours(int saturdayManhours) {
		this.saturdayManhours = saturdayManhours;
	}

	public int getSundayManhours() {
		return sundayManhours;
	}

	public void setSundayManhours(int sundayManhours) {
		this.sundayManhours = sundayManhours;
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

	public String getStoreMessage() {
		return storeMessage;
	}

	public void setStoreMessage(String storeMessage) {
		this.storeMessage = storeMessage;
	}

	public String getBillingType() {
		return billingType;
	}

	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}

	public String getIsPt() {
		return isPt;
	}

	public void setIsPt(String isPt) {
		this.isPt = isPt;
	}

	public boolean isAc() {
		return isAc;
	}

	public void setAc(boolean isAc) {
		this.isAc = isAc;
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

}
