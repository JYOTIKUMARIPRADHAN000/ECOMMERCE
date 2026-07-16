package com.ecomProject.EcommerceJdbc.utils;

import com.ecomProject.EcommerceJdbc.models.Customer;

public class Session {

	// Currently logged-in customer
	private static Customer loggedInCustomer;

	// Private constructor
	private Session() {

	}

	// Login
	public static void setLoggedInCustomer(Customer customer) {

		loggedInCustomer = customer;
	}

	// Get logged-in customer
	public static Customer getLoggedInCustomer() {

		return loggedInCustomer;
	}

	// Check whether anyone is logged in
	public static boolean isLoggedIn() {

		return loggedInCustomer != null;
	}

	// Logout
	public static void logout() {

		loggedInCustomer = null;
	}

}