package com.ecomProject.EcommerceJdbc.models;

public class Product {

	private int productId;
	private String productName;
	private String category;
	private double price;
	private int stock;

	// Default Constructor
	public Product() {

	}

	// Parameterized Constructor
	public Product(int productId, String productName, String category, double price, int stock) {
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.stock = stock;
	}

	// Getter for Product ID
	public int getProductId() {
		return productId;
	}

	// Setter for Product ID
	public void setProductId(int productId) {
		this.productId = productId;
	}

	// Getter for Product Name
	public String getProductName() {
		return productName;
	}

	// Setter for Product Name
	public void setProductName(String productName) {
		this.productName = productName;
	}

	// Getter for Category
	public String getCategory() {
		return category;
	}

	// Setter for Category
	public void setCategory(String category) {
		this.category = category;
	}

	// Getter for Price
	public double getPrice() {
		return price;
	}

	// Setter for Price
	public void setPrice(double price) {
		this.price = price;
	}

	// Getter for Stock
	public int getStock() {
		return stock;
	}

	// Setter for Stock
	public void setStock(int stock) {
		this.stock = stock;
	}

	// Display Product Details
	@Override
	public String toString() {
		return "Product ID : " + productId + "\nProduct Name : " + productName + "\nCategory : " + category
				+ "\nPrice : ₹" + price + "\nStock : " + stock + "\n";
	}
}
