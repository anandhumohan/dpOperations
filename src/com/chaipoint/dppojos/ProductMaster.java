package com.chaipoint.dppojos;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "product_master")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ProductMaster {
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "code")
	private String code;

	@Column(name = "name")
	private String name;

	@Column(name = "display_name")
	private String displayName;

	@Column(name = "product_image")
	private String productimage;

	@Column(name = "type")
	private String type;

	@Column(name = "category")
	private String category;

	@Column(name = "ComboMenu")
	private boolean comboMenu;

	@Column(name = "packaging")
	private String packing;

	@Column(name = "cost")
	private float cost;

	@Column(name = "price")
	private float price;

	@Column(name = "exclusive_price")
	private double exclusivePrice;

	@Column(name = "costofunderstocking")
	private float costOfUnderStocking;

	@Column(name = "costofoverstocking")
	private float costOfOverStocking;

	@Column(name = "tax")
	private int tax;

	@Column(name = "tax_location_master")
	private String taxLocationMapping;

	@Column(name = "service_tax_rate")
	private int serviceTaxrate;

	@Column(name = "is_coc")
	private boolean isCoc;

	@Column(name = "is_subscription")
	private boolean isSubscription;

	@Column(name = "is_foe")
	private boolean isFoe;

	@Column(name = "is_web")
	private boolean isWeb;

	@Column(name = "is_caw")
	private boolean isCaw;

	@Column(name = "is_free")
	private boolean isFree;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "active")
	private boolean active;

	@Column(name = "location")
	private String location;

	@Column(name = "sequence")
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getProductimage() {
		return productimage;
	}

	public void setProductimage(String productimage) {
		this.productimage = productimage;
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

	public boolean isComboMenu() {
		return comboMenu;
	}

	public void setComboMenu(boolean comboMenu) {
		this.comboMenu = comboMenu;
	}

	public String getPacking() {
		return packing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public double getExclusivePrice() {
		return exclusivePrice;
	}

	public void setExclusivePrice(double exclusivePrice) {
		this.exclusivePrice = exclusivePrice;
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

	public int getServiceTaxrate() {
		return serviceTaxrate;
	}

	public void setServiceTaxrate(int serviceTaxrate) {
		this.serviceTaxrate = serviceTaxrate;
	}

	public boolean isCoc() {
		return isCoc;
	}

	public void setCoc(boolean isCoc) {
		this.isCoc = isCoc;
	}

	public boolean isSubscription() {
		return isSubscription;
	}

	public void setSubscription(boolean isSubscription) {
		this.isSubscription = isSubscription;
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
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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
