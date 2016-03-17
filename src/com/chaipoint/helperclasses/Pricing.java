package com.chaipoint.helperclasses;

public class Pricing {
	private double totalPrice;
	private double discountAmount;
	private String couponApplied;
	private double deliveryCharges;
	private double finalPayableCost;
	private double totalTax;
	

	

	public double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public String getCouponApplied() {
		return couponApplied;
	}

	public void setCouponApplied(String couponApplied) {
		this.couponApplied = couponApplied;
	}

	public double getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(double deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	public double getFinalPayableCost() {
		return finalPayableCost;
	}

	public void setFinalPayableCost(double finalPayableCost) {
		this.finalPayableCost = finalPayableCost;
	}

}
