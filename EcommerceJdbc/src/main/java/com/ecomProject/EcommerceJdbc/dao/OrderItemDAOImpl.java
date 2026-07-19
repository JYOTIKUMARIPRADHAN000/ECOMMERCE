package com.ecomProject.EcommerceJdbc.dao;

import java.sql.Connection;
import java.util.List;

import com.ecomProject.EcommerceJdbc.models.OrderItem;
import com.ecomProject.EcommerceJdbc.utils.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {
	// 1. Create database connection
	Connection con= DBConnection.getConnection();

    // 2. Write SQL query
	String sql="";

	@Override
	public boolean addOrderItem(OrderItem orderItem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OrderItem getOrderItemById(int orderItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItem> getOrderItemsByProductId(int productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItem> getAllOrderItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateOrderItemQuantity() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOrderItemPrice() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOrderItem(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOrderItemsByOrderId(int id) {
		// TODO Auto-generated method stub
		return false;
	}

    // 3. Create PreparedStatement

    // 4. Set PreparedStatement parameters

    // 5. Execute query/update

    // 6. Process ResultSet (for SELECT operations)

    // 7. Convert each row into an OrderItem object

    // 8. Store objects in a List (if multiple records)

    // 9. Return object/List/boolean based on the method

    // 10. Close ResultSet, PreparedStatement, and Connection
	
	
}
