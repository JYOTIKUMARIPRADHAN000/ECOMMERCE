package com.ecomProject.EcommerceJdbc.dao;
import com.ecomProject.EcommerceJdbc.models.Product;
import com.ecomProject.EcommerceJdbc.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes

public class ProductDAO {

	// Add Product
	public boolean addProduct(Product product) {

		String sql = "INSERT INTO product(product_name, description, price, stock, category) VALUES(?,?,?,?,?)";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {

			
			pstmt.setString(1, product.getProductName());
			pstmt.setString(2, product.getDescription());
			pstmt.setDouble(3, product.getPrice());
			pstmt.setInt(4, product.getStock());
			pstmt.setString(5, product.getCategory());
			
			

			int rows = pstmt.executeUpdate();

			return rows > 0;

		} catch (SQLException e) {
			System.out.println("Error while adding product.");
			e.printStackTrace();
		}

		return false;
	}

	// Update Product
	public boolean updateProduct(Product product) {

		String sql = "UPDATE product SET product_name = ?,description=?,  price = ?, stock = ?,category = ? WHERE product_id = ?";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, product.getProductName());
			pstmt.setString(2, product.getDescription());
			pstmt.setDouble(3, product.getPrice());
			pstmt.setInt(4, product.getStock());
			pstmt.setString(5, product.getCategory());
			pstmt.setInt(6, product.getProductId());

			int rows = pstmt.executeUpdate();

			return rows > 0;

		} catch (SQLException e) {
			System.out.println("Error while updating product.");
			e.printStackTrace();
		}

		return false;
	}

	// Delete Product
	public boolean deleteProduct(int productId) {

		String sql = "DELETE FROM product WHERE product_id = ?";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setInt(1, productId);

			int rows = pstmt.executeUpdate();

			return rows > 0;

		} catch (SQLException e) {
			System.out.println("Error while deleting product.");
			e.printStackTrace();
		}

		return false;
	}

	// Get Product By ID
	public Product getProductById(int productId) {

		String sql = "SELECT * FROM product WHERE product_id = ?";

		Product product = null;

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setInt(1, productId);

			try (java.sql.ResultSet rs = pstmt.executeQuery()) {

				if (rs.next()) {

					product = new Product();

					product.setProductId(rs.getInt("product_id"));
					product.setProductName(rs.getString("product_name"));
					product.setDescription(rs.getString("description"));
					product.setPrice(rs.getDouble("price"));
					product.setStock(rs.getInt("stock"));
					product.setCategory(rs.getString("category"));
					
					
				}
			}

		} catch (SQLException e) {
			System.out.println("Error while searching product.");
			e.printStackTrace();
		}

		return product;
	}

	// Get All Products
	public java.util.List<Product> getAllProducts() {

		String sql = "SELECT * FROM product";

		java.util.List<Product> productList = new java.util.ArrayList<>();

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);
				java.sql.ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {

				Product product=new Product();

				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setDescription(rs.getString("description"));
				product.setPrice(rs.getDouble("price"));
				product.setStock(rs.getInt("stock"));
				product.setCategory(rs.getString("category"));

				productList.add(product);
			}

		} catch (SQLException e) {
			System.out.println("Error while fetching products.");
			e.printStackTrace();
		}

		return productList;
	}

	// Update Stock
	public boolean updateStock(int productId, int stock) {

		String sql = "UPDATE product SET stock = ? WHERE product_id = ?";

		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setInt(1, stock);
			pstmt.setInt(2, productId);

			int rows = pstmt.executeUpdate();

			return rows > 0;

		} catch (SQLException e) {
			System.out.println("Error while updating stock.");
			e.printStackTrace();
		}

		return false;
	}

}
