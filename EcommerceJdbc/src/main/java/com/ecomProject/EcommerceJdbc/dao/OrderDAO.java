package com.ecomProject.EcommerceJdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.ecomProject.EcommerceJdbc.models.Order;
import com.ecomProject.EcommerceJdbc.utils.DBConnection;

public class OrderDAO {

	public int createOrder(Connection con, Order order) throws SQLException {

		String sql = "insert into orders(customer_id,product_id,quantity,total_amount)" + " values(?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, order.getCustomerId());
		ps.setInt(2, order.getProductId());
		ps.setInt(3, order.getQuantity());
		ps.setDouble(4, order.getTotalAmount());

		ps.executeUpdate();

		ResultSet rs = ps.getGeneratedKeys();

		if (rs.next()) {

			return rs.getInt(1);
		}

		return 0;
	}

	public void saveOrder(Connection con, Order order) throws SQLException {

		createOrder(con, order);

	}

	public Order getOrder(int id) throws Exception {

		Connection con = DBConnection.getConnection();

		String sql = "select * from orders where order_id=?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			Order order = new Order(rs.getInt("customer_id"), rs.getInt("product_id"), rs.getInt("quantity"));

			order.setOrderId(rs.getInt("order_id"));

			order.setTotalAmount(rs.getDouble("total_amount"));

			return order;
		}

		return null;
	}

	public List<Order> getOrdersByCustomer(int customerId) throws Exception {

		List<Order> list = new ArrayList<>();

		Connection con = DBConnection.getConnection();

		String sql = "select * from orders where customer_id=?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, customerId);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			Order order = new Order(rs.getInt("customer_id"), rs.getInt("product_id"), rs.getInt("quantity"));

			order.setOrderId(rs.getInt("order_id"));

			order.setTotalAmount(rs.getDouble("total_amount"));

			list.add(order);

		}

		return list;
	}
}