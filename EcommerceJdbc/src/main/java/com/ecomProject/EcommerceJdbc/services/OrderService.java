package com.ecomProject.EcommerceJdbc.services;

import java.sql.Connection;
import java.util.List;
import com.ecomProject.EcommerceJdbc.dao.OrderDAO;
import com.ecomProject.EcommerceJdbc.models.Order;
import com.ecomProject.EcommerceJdbc.utils.DBConnection;





public class OrderService {

	OrderDAO dao = new OrderDAO();

	public void placeOrder(Order order) {

		Connection con = null;

		try {

			con = DBConnection.getConnection();

			// Transaction Start

			con.setAutoCommit(false);

			validateCustomer(order);

			validateStock(order);

			double total = calculateTotal(order);

			order.setTotalAmount(total);

			dao.createOrder(con, order);

			createTransaction();

			commitTransaction(con);

			System.out.println("Order placed successfully");

		} catch (Exception e) {

			rollbackTransaction(con);

			System.out.println("Order Failed : " + e.getMessage());

		}

	}

	public void validateCustomer(Order order) throws Exception {

		if (order.getCustomerId() <= 0) {

			throw new Exception("Invalid Customer");

		}

	}

	public void validateStock(Order order) throws Exception {

		// Normally check product table

		if (order.getQuantity() <= 0) {

			throw new Exception("Invalid Quantity");

		}

	}

	public double calculateTotal(Order order) {

		double price = 500;

		return price * order.getQuantity();

	}

	public void createTransaction() {

		System.out.println("Transaction Created");

	}

	public void commitTransaction(Connection con) throws Exception {

		con.commit();

		System.out.println("Transaction Committed");

	}

	public void rollbackTransaction(Connection con) {

		try {

			if (con != null) {

				con.rollback();

				System.out.println("Transaction Rolled Back");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public List<Order> viewOrders(int customerId) throws Exception {

		return dao.getOrdersByCustomer(customerId);

	}

}
