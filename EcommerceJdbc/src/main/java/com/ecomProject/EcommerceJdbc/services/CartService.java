package com.ecomProject.EcommerceJdbc.services;

import java.sql.SQLException;

import com.ecomProject.EcommerceJdbc.dao.CartDAO;
import com.ecomProject.EcommerceJdbc.models.Cart;


public class CartService {

	CartDAO dao = new CartDAO();

	public void addItem(Cart cart) throws ClassNotFoundException, SQLException {

		if (cart.getQuantity() > 0)
			dao.addToCart(cart);
		else
			System.out.println("Invalid Quantity");
	}

	public void removeItem(int productId) throws ClassNotFoundException, SQLException {

		dao.removeFromCart(productId);

	}

	public void increaseQuantity(int productId, int quantity) throws ClassNotFoundException, SQLException {

		dao.updateQuantity(productId, quantity);
	}

	public void decreaseQuantity(int productId, int quantity) throws ClassNotFoundException, SQLException {

		if (quantity > 0)
			dao.updateQuantity(productId, quantity);
		else
			dao.removeFromCart(productId);

	}

	public double calculateCartTotal() throws ClassNotFoundException, SQLException {

		return dao.calculateTotal();

	}

	public void clearCart() throws ClassNotFoundException, SQLException {

		dao.clearCart();

	}

	public void viewCart() throws ClassNotFoundException, SQLException {

		dao.viewCart();

	}

}

