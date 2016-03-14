package com.chaipoint.dppojos;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "coc_order_view")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class CoCOrderDetailstesting {

	@Column(name = "coupon_code")
	private String couponCode;

	@Column(name = "cancel_reason")
	private String cancelReasson;

	@Column(name = "delivery_charge")
	private double deliveryCharge;
	
	
	@Column(name = "order_id")
	private int orderId;
	
	
	@Column(name = "product_id")
	private int productId;

	// @Column(name = "comment")
	// private String comment;

	// @Id
	// private MyJoinClassKey key;

	@Column(name = "store_id")
	private int storeId;

	@Column(name = "status")
	private String status;

	@Column(name = "net_amount")
	private double netAmount;

	@Column(name = "total_amount")
	private double totalAmount;

	@Column(name = "delivery_boy")
	private String deliveryBoy;

	@Column(name = "company")
	private String company;

	@Column(name = "phone")
	private String phone;

	@Column(name = "name")
	private String name;

	@Column(name = "building")
	private String building;

	@Column(name = "flat")
	private String flat;

	@Column(name = "floor")
	private String floor;

	@Column(name = "locality")
	private String locality;

	@Column(name = "sublocality")
	private String sublocality;

	@Column(name = "landmark")
	private String landmark;

	@Column(name = "city")
	private String city;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "qty")
	private int qty;

	@Column(name = "cost")
	private double cost;

	@Column(name = "total_product_cost")
	private double totalProductCost;

	@Column(name = "channel_name")
	private String channelName;

	@Column(name = "channel_id")
	private int channelId;

	@Column(name = "payment_method")
	private String paymentMethod;

	@Column(name = "payment_id")
	private int paymentId;

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCancelReasson() {
		return cancelReasson;
	}

	public void setCancelReasson(String cancelReasson) {
		this.cancelReasson = cancelReasson;
	}

	public double getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(double deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getDeliveryBoy() {
		return deliveryBoy;
	}

	public void setDeliveryBoy(String deliveryBoy) {
		this.deliveryBoy = deliveryBoy;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getSublocality() {
		return sublocality;
	}

	public void setSublocality(String sublocality) {
		this.sublocality = sublocality;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getTotalProductCost() {
		return totalProductCost;
	}

	public void setTotalProductCost(double totalProductCost) {
		this.totalProductCost = totalProductCost;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

}