package com.chaipoint.dppojos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_MASTER")
public class CpProductMaster {

	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "CODE")
	private String code;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DISPLAY_NAME")
	private String display_name;

	@Column(name = "PRODUCT_IMAGE")
	private String product_image;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "COMBO_MENU")
	private String comboMenu;

	@Column(name = "PACKAGING")
	private String packaging;

	@Column(name = "COST")
	private double cost;

	@Column(name = "PRICE")
	private double price;

	@Column(name = "EXCLUSIVE_PRICE")
	private double exclusive_price;

	@Column(name = "COST_OF_UNDER_STOCKING")
	private float costOfUnderStocking;

	@Column(name = "COST_OF_OVER_STOCKING")
	private float costOfOverStocking;

	@Column(name = "TAX")
	private int tax;

	@Column(name = "TAX_LOCATION_MAPPING")
	private String taxLocationMapping;

	@Column(name = "SEVICE_TAX_RATE")
	private int serviceTaxRate;

	@Column(name = "COC")
	private boolean isCoC;

	@Column(name = "SUBSCRIPTION")
	private boolean isSubsription;

	@Column(name = "FOE")
	private boolean isFoe;

	@Column(name = "WEB")
	private boolean isWeb;

	@Column(name = "CAW")
	private boolean isCaw;

	@Column(name = "FREE")
	private boolean isFree;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "CREATED_BY")
	private int createdBy;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(name = "UPDATED_BY")
	private int updatedBy;

	@Column(name = "ACTIVE")
	private boolean isActive;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "SEQUENCE")
	private int sequence;

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

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getComboMenu() {
		return comboMenu;
	}

	public void setComboMenu(String comboMenu) {
		this.comboMenu = comboMenu;
	}

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getExclusive_price() {
		return exclusive_price;
	}

	public void setExclusive_price(double exclusive_price) {
		this.exclusive_price = exclusive_price;
	}

	public float getCostOfUnderStocking() {
		return costOfUnderStocking;
	}

	public void setCostOfUnderStocking(float costOfUnderStocking) {
		this.costOfUnderStocking = costOfUnderStocking;
	}

	public float getCostOfOverStocking() {
		return costOfOverStocking;
	}

	public void setCostOfOverStocking(float costOfOverStocking) {
		this.costOfOverStocking = costOfOverStocking;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public String getTaxLocationMapping() {
		return taxLocationMapping;
	}

	public void setTaxLocationMapping(String taxLocationMapping) {
		this.taxLocationMapping = taxLocationMapping;
	}

	public int getServiceTaxRate() {
		return serviceTaxRate;
	}

	public void setServiceTaxRate(int serviceTaxRate) {
		this.serviceTaxRate = serviceTaxRate;
	}

	public boolean isCoC() {
		return isCoC;
	}

	public void setCoC(boolean isCoC) {
		this.isCoC = isCoC;
	}

	public boolean isSubsription() {
		return isSubsription;
	}

	public void setSubsription(boolean isSubsription) {
		this.isSubsription = isSubsription;
	}

	public boolean isFoe() {
		return isFoe;
	}

	public void setFoe(boolean isFoe) {
		this.isFoe = isFoe;
	}

	public boolean isWeb() {
		return isWeb;
	}

	public void setWeb(boolean isWeb) {
		this.isWeb = isWeb;
	}

	public boolean isCaw() {
		return isCaw;
	}

	public void setCaw(boolean isCaw) {
		this.isCaw = isCaw;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

}
