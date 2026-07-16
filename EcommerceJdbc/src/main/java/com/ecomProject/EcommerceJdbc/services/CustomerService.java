package com.ecomProject.EcommerceJdbc.services;

import java.util.ArrayList;

import com.ecomProject.EcommerceJdbc.dao.CustomerDAO;
import com.ecomProject.EcommerceJdbc.exception.CustomerNotFoundException;
import com.ecomProject.EcommerceJdbc.models.Customer;

public class CustomerService {

	private CustomerDAO customerDAO = new CustomerDAO();

	// ===============================
	// Register Customer
	// ===============================
	public boolean registerCustomer(Customer customer) {

		if (!validateCustomer(customer)) {
			return false;
		}

		return customerDAO.addCustomer(customer);
	}

	// ===============================
	// Update Customer
	// ===============================
	public boolean updateCustomer(Customer customer) throws CustomerNotFoundException {

		if (!validateCustomer(customer))
			return false;

		Customer existing = customerDAO.getCustomerById(customer.getCustomerId());

		if (existing == null) {

			throw new CustomerNotFoundException("Customer with ID " + customer.getCustomerId() + " not found.");
		}

		return customerDAO.updateCustomer(customer);
	}

	// ===============================
	// Delete Customer
	// ===============================
	public boolean deleteCustomer(int customerId) throws CustomerNotFoundException {

		Customer customer = customerDAO.getCustomerById(customerId);

		if (customer == null) {

			throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
		}

		return customerDAO.deleteCustomer(customerId);
	}

	// ===============================
	// View Single Customer
	// ===============================
	public Customer viewCustomer(int customerId) throws CustomerNotFoundException {

		Customer customer = customerDAO.getCustomerById(customerId);

		if (customer == null) {
			throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
		}

		return customer;
	}

	// ===============================
	// View All Customers
	// ===============================
	public ArrayList<Customer> viewAllCustomers() {

		return customerDAO.getAllCustomers();
	}

	// ===============================
	// Validation
	// ===============================
	public boolean validateCustomer(Customer customer) {

		// First Name
		if (customer.getFirstName() == null || customer.getFirstName().trim().isEmpty()) {

			System.out.println("First Name cannot be empty.");
			return false;
		}

		// Last Name
		if (customer.getLastName() == null || customer.getLastName().trim().isEmpty()) {

			System.out.println("Last Name cannot be empty.");
			return false;
		}

		// Email
		if (customer.getEmail() == null || !customer.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

			System.out.println("Invalid Email.");
			return false;
		}

		// Phone
		if (customer.getPhone() == null || !customer.getPhone().matches("\\d{10}")) {

			System.out.println("Phone number must contain exactly 10 digits.");
			return false;
		}

		// Password
		if (customer.getPassword() == null || customer.getPassword().length() < 6) {

			System.out.println("Password should contain at least 6 characters.");
			return false;
		}

		// Address
		if (customer.getAddress() == null || customer.getAddress().trim().isEmpty()) {

			System.out.println("Address cannot be empty.");
			return false;
		}

		// Customer Type
		if (customer.getCustomerType() == null || !(customer.getCustomerType().equalsIgnoreCase("USER")
				|| customer.getCustomerType().equalsIgnoreCase("RESELLER"))) {

			System.out.println("Customer Type must be USER or RESELLER.");
			return false;
		}

		return true;
	}

}