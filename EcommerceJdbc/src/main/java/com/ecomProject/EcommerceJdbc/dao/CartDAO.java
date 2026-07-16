package com.ecomProject.EcommerceJdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecomProject.EcommerceJdbc.models.Cart;
import com.ecomProject.EcommerceJdbc.utils.DBConnection;



public class CartDAO {

	public void addToCart(Cart cart) throws ClassNotFoundException, SQLException {

		Connection connection = DBConnection.getConnection();

		String sql = "insert into cart(product_id,product_name,price,quantity,total) values(?,?,?,?,?)";
		try {

			PreparedStatement ps = connection.prepareStatement(sql);
			connection.setAutoCommit(false);
			ps.setInt(1, cart.getProductId());
			ps.setString(2, cart.getProductName());
			ps.setDouble(3, cart.getPrice());
			ps.setInt(4, cart.getQuantity());
			ps.setDouble(5, cart.getPrice() * cart.getQuantity());

			ps.executeUpdate();

			System.out.println("Item Added Successfully");
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}

	}

	public void removeFromCart(int productId) throws SQLException, ClassNotFoundException {

		Connection connection = DBConnection.getConnection();
		String Query = "delete from cart where product_id=?";

		PreparedStatement ps = connection.prepareStatement(Query);
		try {
			connection.setAutoCommit(false);
			ps.setInt(1, productId);

			ps.executeUpdate();

			System.out.println("Item Removed");
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}

	}

	public void updateQuantity(int productId, int quantity) throws SQLException, ClassNotFoundException {

		Connection connection = DBConnection.getConnection();
		String Query = "update cart set quantity=?, total=price*? where product_id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(Query);
			connection.setAutoCommit(false);
			ps.setInt(1, quantity);
			ps.setInt(2, quantity);
			ps.setInt(3, productId);

			ps.executeUpdate();

			System.out.println("Quantity Updated");
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}

	}

	public void viewCart() throws SQLException, ClassNotFoundException {

		Connection connection = DBConnection.getConnection();
		String Query = "select * from cart";
		try {
			PreparedStatement st = connection.prepareStatement(Query);
			connection.setAutoCommit(false);

			ResultSet rs = st.executeQuery(Query);

			System.out.println("---------------------------------------------");

			while (rs.next()) {

				System.out.println(rs.getInt("product_id") + " " + rs.getString("product_name") + " "
						+ rs.getDouble("price") + " " + rs.getInt("quantity") + " " + rs.getDouble("total"));

			}

			System.out.println("---------------------------------------------");
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}

	}

	public void clearCart() throws SQLException, ClassNotFoundException {

		Connection connection = DBConnection.getConnection();
		String Query = "delete from cart";
		try {
			PreparedStatement st = connection.prepareStatement(Query);
			connection.setAutoCommit(false);

			st.executeUpdate(Query);

			System.out.println("Cart Cleared");
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}

	}

	public double calculateTotal() throws ClassNotFoundException, SQLException {

		double total = 0;

		Connection connection = DBConnection.getConnection();

		String Query = "select sum(total) from cart";
		try {
			PreparedStatement st = connection.prepareStatement(Query);
			connection.setAutoCommit(false);

			ResultSet rs = st.executeQuery(Query);

			if (rs.next()) {

				total = rs.getDouble(1);

			}
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}

		return total;

	}

}