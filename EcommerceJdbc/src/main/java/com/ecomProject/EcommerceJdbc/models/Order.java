package com.ecomProject.EcommerceJdbc.models;

public class Order {

	private int orderId;
	private int customerId;
	private int productId;
	private int quantity;
	private double totalAmount;

	public Order(int customerId, int productId, int quantity) {

		this.customerId = customerId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {

		this.totalAmount = totalAmount;
	}

	public void setOrderId(int orderId) {

		this.orderId = orderId;
	}

	@Override
	public String toString() {

		return " Order ID : " + orderId +
			   "\n Customer ID : " + customerId + 
			   "\n Product ID : " + productId + 
			   "\n Quantity : "+ quantity + 
			   "\n Total : " + totalAmount;
	}
}