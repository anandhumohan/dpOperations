package com.chaipoint.dppojos;

//import java.sql.Date;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "cp_orders")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CpOrders {

	@Id
	@Column(name = "id")
	public int id;

	@Column(name = "status")
	public String status;

	@Column(name = "cancel_reason")
	public String cancelReason;

	@Column(name = "customer_id")
	public int customerId;

	@Column(name = "store_id")
	public int storeId;

	@Column(name = "channel")
	public int channel;

	// @Column(name = "products")
	// public String products;

	@Column(name = "total_amount")
	public double totalAmount;

	@Column(name = "discount")
	public double discount;

	@Column(name = "delivery_charge")
	public double deliveryChange;

	@Column(name = "service_charge")
	public double serviceCharge;

	@Column(name = "vat")
	public double vat;

	@Column(name = "net_amount")
	public double netAmount;

	@Column(name = "payment_method")
	public String paymentMethod;

	@Column(name = "confirm_time")
	public Date confirmTime;

	@Column(name = "dispatch_time")
	public Date dispatchTime;

	@Column(name = "final_delivery_time")
	public Date finalDeliveryTime;

	@Column(name = "cancel_time")
	public Date cancelTime;

	@Column(name = "delivery_date")
	public Date deliveryDate;

	@Column(name = "created_date")
	public Date createdDate;

	@Column(name = "sms_flag")
	public String smsFlag;

	@Column(name = "delivery_boy")
	public int deliveryBoy;

	@Column(name = "coupon_code")
	public String couponCode;

	@Column(name = "dp_status")
	public String dpStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFinalDeliveryTime() {
		return finalDeliveryTime;
	}

	public void setFinalDeliveryTime(Date finalDeliveryTime) {
		this.finalDeliveryTime = finalDeliveryTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
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

	public double getDeliveryChange() {
		return deliveryChange;
	}

	public void setDeliveryChange(double deliveryChange) {
		this.deliveryChange = deliveryChange;
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

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Date getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(Date dispatchTime) {
		this.dispatchTime = dispatchTime;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getSmsFlag() {
		return smsFlag;
	}

	public void setSmsFlag(String smsFlag) {
		this.smsFlag = smsFlag;
	}

	public int getDeliveryBoy() {
		return deliveryBoy;
	}

	public void setDeliveryBoy(int deliveryBoy) {
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
