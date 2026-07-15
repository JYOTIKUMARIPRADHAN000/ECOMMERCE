package com.ecomProject.EcommerceJdbc.menu;

import java.util.ArrayList;

import com.ecomProject.EcommerceJdbc.exception.CustomerNotFoundException;
import com.ecomProject.EcommerceJdbc.models.Customer;
import com.ecomProject.EcommerceJdbc.services.CustomerService;
import com.ecomProject.EcommerceJdbc.utils.InputUtil;

public class CustomerMenu {

	private final CustomerService customerService = new CustomerService();

	public void showMenu() throws CustomerNotFoundException {

		int choice;

		do {

			System.out.println("\n==================================");
			System.out.println("       CUSTOMER MENU");
			System.out.println("==================================");
			System.out.println("1. Add Customer");
			System.out.println("2. View Customers");
			System.out.println("3. Update Customer");
			System.out.println("4. Delete Customer");
			System.out.println("5. Back");
			System.out.println("==================================");

			choice = InputUtil.getInt("Enter Choice : ");

			switch (choice) {

			case 1:
				addCustomer();
				break;

			case 2:
				viewCustomers();
				break;

			case 3:
				updateCustomer();
				break;

			case 4:
				deleteCustomer();
				break;

			case 5:
				System.out.println("Returning...");
				break;

			default:
				System.out.println("Invalid Choice.");
			}

		} while (choice != 5);

	}

	// ===========================
	// Add Customer
	// ===========================

	private void addCustomer() {

		String firstName = InputUtil.getString("First Name : ");
		String lastName = InputUtil.getString("Last Name : ");
		String email = InputUtil.getString("Email : ");
		String phone = InputUtil.getString("Phone : ");
		String password = InputUtil.getString("Password : ");
		String address = InputUtil.getString("Address : ");

		System.out.println("\nCustomer Type");
		System.out.println("1. USER");
		System.out.println("2. RESELLER");

		int typeChoice = InputUtil.getInt("Enter Choice : ");

		String customerType;

		if (typeChoice == 1)
			customerType = "USER";
		else
			customerType = "RESELLER";

		Customer customer = new Customer(firstName, lastName, email, phone, password, address, customerType);

		if (customerService.registerCustomer(customer))
			System.out.println("\nCustomer Registered Successfully.");
		else
			System.out.println("\nCustomer Registration Failed.");
	}

	// ===========================
	// View Customers
	// ===========================

	private void viewCustomers() throws CustomerNotFoundException {

		System.out.println("\n1. View By ID");
		System.out.println("2. View All");

		int choice = InputUtil.getInt("Enter Choice : ");

		if (choice == 1) {

			int id = InputUtil.getInt("Enter Customer ID : ");

			Customer customer = customerService.viewCustomer(id);

			if (customer == null)
				System.out.println("Customer Not Found.");
			else
				System.out.println(customer);

		} else if (choice == 2) {

			ArrayList<Customer> customers = customerService.viewAllCustomers();

			if (customers.isEmpty()) {

				System.out.println("No Customers Found.");
				return;
			}

			for (Customer customer : customers)
				System.out.println(customer);

		} else {

			System.out.println("Invalid Choice.");
		}

	}

	// ===========================
	// Update Customer
	// ===========================

	private void updateCustomer() throws CustomerNotFoundException {

		int id = InputUtil.getInt("Enter Customer ID : ");

		Customer existingCustomer = customerService.viewCustomer(id);

		if (existingCustomer == null) {

			System.out.println("Customer Not Found.");
			return;
		}

		String firstName = InputUtil.getString("First Name : ");
		String lastName = InputUtil.getString("Last Name : ");
		String email = InputUtil.getString("Email : ");
		String phone = InputUtil.getString("Phone : ");
		String password = InputUtil.getString("Password : ");
		String address = InputUtil.getString("Address : ");

		System.out.println("\nCustomer Type");
		System.out.println("1. USER");
		System.out.println("2. RESELLER");

		int typeChoice = InputUtil.getInt("Enter Choice : ");

		String customerType = (typeChoice == 1) ? "USER" : "RESELLER";

		Customer customer = new Customer(id, firstName, lastName, email, phone, password, address, customerType);

		try {

			if (customerService.updateCustomer(customer))
				System.out.println("Customer Updated Successfully.");

		} catch (CustomerNotFoundException e) {

			System.out.println(e.getMessage());

		}
	}

	// ===========================
	// Delete Customer
	// ===========================

	private void deleteCustomer() {

		int id = InputUtil.getInt("Enter Customer ID : ");

		try {

			if (customerService.deleteCustomer(id))
				System.out.println("Customer Deleted Successfully.");

		} catch (CustomerNotFoundException e) {

			System.out.println(e.getMessage());

		}
	}

}