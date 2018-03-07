package com.model;

public class discounts {
	int discountCategory;
	int discountRate;
	public int getDiscountCategory() {
		return discountCategory;
	}
	public void setDiscountCategory(int discountCategory) {
		this.discountCategory = discountCategory;
	}
	public int getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}
	public discounts(int discountCategory, int discountRate) {
		super();
		this.discountCategory = discountCategory;
		this.discountRate = discountRate;
	}
	

}
