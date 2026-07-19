package com.ecomProject.EcommerceJdbc.dao;

import java.util.List;

import com.ecomProject.EcommerceJdbc.models.OrderItem;

public interface OrderItemDAO {

	// add order item

	boolean addOrderItem(OrderItem orderItem);

	// get order item by id

	OrderItem getOrderItemById(int orderItemId);

	// retrieve order items for specific order

	List<OrderItem> getOrderItemsByOrderId(int orderId);

	// retrieve order items for specific product
	List<OrderItem> getOrderItemsByProductId(int productId);

	// retrieve all order items from the database
	List<OrderItem> getAllOrderItems();

	// update quantity for a order item
	boolean updateOrderItemQuantity();

	// update unit price of a order item
	boolean updateOrderItemPrice();
	// delete order item by id

	boolean deleteOrderItem(int id);

	// delete all order items belong to order id
	boolean deleteOrderItemsByOrderId(int id);

}
