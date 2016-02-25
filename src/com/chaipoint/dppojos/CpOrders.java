package com.chaipoint.dppojos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CP_ORDERS")
public class CpOrders {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CUSTOMER_ID")
	private String customerId;

	@Column(name = "STORE_ID")
	private String storeid;

	@Column(name = "CHANNEL")
	private int channel;

	@Column(name = "PRODUCTS")
	private String products;

	@Column(name = "TOTAL_AMOUNT")
	private double totalAmount;

	@Column(name = "DISCOUNT")
	private double discount;

	@Column(name = "DELIVERY_CHARGE")
	private double deliveryCharge;

	@Column(name = "SERVICE_CHANRGE")
	private double serviceCharge;

	@Column(name = "VAT")
	private double vat;

	@Column(name = "NET_AMOUNT")
	private double netAmount;

	@Column(name = "PAYMENT_METHOD")
	private String paymentMethod;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name = "CONFIRM_NAME")
	private Date confirmDate;

	@Column(name = "DISPATCH_DATE")
	private Date dispatchDate;

	@Column(name = "CANCEL_DATE")
	private Date cancelDate;

	@Column(name = "DELIVERY_DATE")
	private Date deliveryDate;

	@Column(name = "FINAL_DELIVERY_DATE")
	private Date finalDeliveryDate;

	@Column(name = "CREATED_BY")
	private int createdBy;

	@Column(name = "CREATED_DATE")
	private String createdDate;

	@Column(name = "UPADATED_BY")
	private int updatedBy;

	@Column(name = "UPDATED_DATE")
	private String updatedDate;

	@Column(name = "SMS_FLAG")
	private String smsFlag;

	@Column(name = "DELIVERY_BOY")
	private String deliveryBoy;

	@Column(name = "COUPON_CODE")
	private String couponCode;

	@Column(name = "DP_STATUS")
	private String dpStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(double deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public Date getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getFinalDeliveryDate() {
		return finalDeliveryDate;
	}

	public void setFinalDeliveryDate(Date finalDeliveryDate) {
		this.finalDeliveryDate = finalDeliveryDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getSmsFlag() {
		return smsFlag;
	}

	public void setSmsFlag(String smsFlag) {
		this.smsFlag = smsFlag;
	}

	public String getDeliveryBoy() {
		return deliveryBoy;
	}

	public void setDeliveryBoy(String deliveryBoy) {
		this.deliveryBoy = deliveryBoy;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getDpStatus() {
		return dpStatus;
	}

	public void setDpStatus(String dpStatus) {
		this.dpStatus = dpStatus;
	}

}
