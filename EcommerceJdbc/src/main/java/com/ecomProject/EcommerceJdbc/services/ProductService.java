package com.ecomProject.EcommerceJdbc.services;
import com.ecomProject.EcommerceJdbc.dao.ProductDao;
import com.ecomProject.EcommerceJdbc.models.Product;

import java.util.List;

import com.ecommerce.ProductDao;
import com.ecommerce.Product;

public class ProductService {

	private ProductDao productDAO;

	public ProductService() {
		productDAO = new ProductDao();
	}

	// Add Product
	public boolean addProduct(Product product) {

		if (!validateProduct(product)) {
			return false;
		}

		if (productDAO.getProductById(product.getProductId()) != null) {
			System.out.println("Product ID already exists.");
			return false;
		}

		return productDAO.addProduct(product);
	}

	// Edit Product
	public boolean editProduct(Product product) {

		if (!validateProduct(product)) {
			return false;
		}

		if (productDAO.getProductById(product.getProductId()) == null) {
			System.out.println("Product not found.");
			return false;
		}

		return productDAO.updateProduct(product);
	}

	// Remove Product
	public boolean removeProduct(int productId) {

		if (productDAO.getProductById(productId) == null) {
			System.out.println("Product not found.");
			return false;
		}

		return productDAO.deleteProduct(productId);
	}

	// View Products
	public List<Product> viewProducts() {
		return productDAO.getAllProducts();
	}

	// Search Product
	public Product searchProduct(int productId) {
		return productDAO.getProductById(productId);
	}

	// Check Stock
	public void checkStock(int productId) {

		Product product = productDAO.getProductById(productId);

		if (product != null) {
			
			System.out.println("Product Name : " + product.getProductName());
			System.out.println("Available Stock : " + product.getStock());
			
		} else {
			System.out.println("Product not found.");
		}
	}

	// Update Stock
	public boolean updateStock(int productId, int stock) {

		if (stock < 0) {
			System.out.println("Stock cannot be negative.");
			return false;
		}

		if (productDAO.getProductById(productId) == null) {
			System.out.println("Product not found.");
			return false;
		}

		return productDAO.updateStock(productId, stock);
	}

	// Product Validation
	private boolean validateProduct(Product product) {

		if (product.getProductId() <= 0) {
			System.out.println("Invalid Product ID.");
			return false;
		}

		if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
			System.out.println("Product Name cannot be empty.");
			return false;
		}

		if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
			System.out.println("Category cannot be empty.");
			return false;
		}

		if (product.getPrice() <= 0) {
			System.out.println("Price must be greater than zero.");
			return false;
		}

		if (product.getStock() < 0) {
			System.out.println("Stock cannot be negative.");
			return false;
		}

		return true;
	}
}
