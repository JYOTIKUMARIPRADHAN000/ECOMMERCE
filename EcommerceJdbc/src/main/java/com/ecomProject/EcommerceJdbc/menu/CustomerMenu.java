package com.ecomProject.EcommerceJdbc.menu;

import java.util.ArrayList;

import com.ecomProject.EcommerceJdbc.exception.CustomerNotFoundException;
import com.ecomProject.EcommerceJdbc.models.Customer;
import com.ecomProject.EcommerceJdbc.services.CustomerService;
import com.ecomProject.EcommerceJdbc.utils.InputUtil;
package com.ecomProject.EcommerceJdbc.menu;

import java.util.ArrayList;

import com.ecomProject.EcommerceJdbc.exception.CustomerNotFoundException;
import com.ecomProject.EcommerceJdbc.models.Customer;
import com.ecomProject.EcommerceJdbc.services.CustomerService;
import com.ecomProject.EcommerceJdbc.utils.InputUtil;
import com.ecomProject.EcommerceJdbc.utils.Session;

public class CustomerMenu {

	private CustomerService customerService = new CustomerService();

	// Number of customers per page
	private static final int PAGE_SIZE = 5;

	// =====================================
	// Customer Main Menu
	// =====================================
	public void showMenu() {

		while (true) {

			System.out.println();
			System.out.println("====================================");
			System.out.println("      CUSTOMER MANAGEMENT");
			System.out.println("====================================");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Back");
			System.out.println("====================================");

			int choice = InputUtil.getInt("Enter Choice : ");

			switch (choice) {

			case 1:
				registerCustomer();
				break;

			case 2:
				loginCustomer();
				break;

			case 3:
				return;

			default:
				System.out.println("Invalid Choice.");
			}
		}

	}

	// =====================================
	// Login
	// =====================================
	private void loginCustomer() {

		String email = InputUtil.getString("Email : ");
		String password = InputUtil.getString("Password : ");

		Customer customer = customerService.login(email, password);

		if (customer == null) {

			System.out.println("\nInvalid Email or Password.");
			return;
		}

		Session.setLoggedInCustomer(customer);

		System.out.println("\nWelcome " + customer.getFirstName() + " " + customer.getLastName());

		routeDashboard();

	}

	// =====================================
	// Dashboard Router
	// =====================================
	private void routeDashboard() {

		Customer customer = Session.getLoggedInCustomer();

		if (customer == null) {

			return;
		}

		if (customerService.isAdmin(customer)) {

			adminDashboard();

		} else if (customerService.isUser(customer)) {

			userDashboard();

		} else if (customerService.isReseller(customer)) {

			resellerDashboard();

		}

	}

	// =====================================
	// Admin Dashboard
	// =====================================
	private void adminDashboard() {

		while (Session.isLoggedIn()) {

			System.out.println();
			System.out.println("====================================");
			System.out.println("          ADMIN DASHBOARD");
			System.out.println("====================================");
			System.out.println("1. View All Customers");
			System.out.println("2. Search Customer");
			System.out.println("3. View Customer By ID");
			System.out.println("4. Update Customer");
			System.out.println("5. Delete Customer");
			System.out.println("6. Logout");
			System.out.println("====================================");

			int choice = InputUtil.getInt("Enter Choice : ");

			switch (choice) {

			case 1:
				viewCustomersWithPagination();
				break;

			case 2:
				searchCustomer();
				break;

			case 3:
				viewCustomerById();
				break;

			case 4:
				updateAnyCustomer();
				break;

			case 5:
				deleteAnyCustomer();
				break;

			case 6:

				Session.logout();

				System.out.println("\nLogged out successfully.");

				return;

			default:
				System.out.println("Invalid Choice.");
			}

		}

	}

	// =====================================
	// USER DASHBOARD
	// =====================================
	private void userDashboard() {

		while (Session.isLoggedIn()) {

			System.out.println();
			System.out.println("====================================");
			System.out.println("          USER DASHBOARD");
			System.out.println("====================================");
			System.out.println("1. View My Profile");
			System.out.println("2. Update My Profile");
			System.out.println("3. Logout");
			System.out.println("====================================");

			int choice = InputUtil.getInt("Enter Choice : ");

			switch (choice) {

			case 1:

				viewMyProfile();
				break;

			case 2:

				updateMyProfile();
				break;

			case 3:

				Session.logout();

				System.out.println("\nLogged Out Successfully.");

				return;

			default:

				System.out.println("Invalid Choice.");

			}

		}

	}

	// =====================================
	// RESELLER DASHBOARD
	// =====================================
	private void resellerDashboard() {

		while (Session.isLoggedIn()) {

			System.out.println();
			System.out.println("====================================");
			System.out.println("        RESELLER DASHBOARD");
			System.out.println("====================================");
			System.out.println("1. View My Profile");
			System.out.println("2. Update My Profile");
			System.out.println("3. Logout");
			System.out.println("====================================");

			int choice = InputUtil.getInt("Enter Choice : ");

			switch (choice) {

			case 1:

				viewMyProfile();
				break;

			case 2:

				updateMyProfile();
				break;

			case 3:

				Session.logout();

				System.out.println("\nLogged Out Successfully.");

				return;

			default:

				System.out.println("Invalid Choice.");

			}

		}

	}

	// =====================================
	// Register Customer
	// =====================================
	private void registerCustomer() {

		System.out.println("\n========== CUSTOMER REGISTRATION ==========");

		String firstName = InputUtil.getString("First Name : ");
		String lastName = InputUtil.getString("Last Name : ");
		String email = InputUtil.getString("Email : ");
		String phone = InputUtil.getString("Phone : ");
		String password = InputUtil.getString("Password : ");
		String address = InputUtil.getString("Address : ");

		System.out.println("\nCustomer Type");
		System.out.println("1. USER");
		System.out.println("2. RESELLER");

		int choice = InputUtil.getInt("Enter Choice : ");

		String customerType;

		switch (choice) {

		case 1:
			customerType = "USER";
			break;

		case 2:
			customerType = "RESELLER";
			break;

		default:
			System.out.println("Invalid Customer Type.");
			return;

		}

		Customer customer = new Customer(firstName, lastName, email, phone, password, address, customerType);

		if (customerService.registerCustomer(customer)) {

			System.out.println("\nCustomer Registered Successfully.");

		} else {

			System.out.println("\nRegistration Failed.");

		}

	}

//=====================================
//View My Profile
//=====================================
	private void viewMyProfile() {

		Customer currentCustomer = Session.getLoggedInCustomer();

		if (currentCustomer == null) {

			System.out.println("No user logged in.");
			return;

		}

		try {

			Customer customer = customerService.viewCustomer(currentCustomer.getCustomerId());

			System.out.println();
			System.out.println(customer);

		} catch (CustomerNotFoundException e) {

			System.out.println(e.getMessage());

		}

	}

//=====================================
//Update My Profile
//=====================================
	private void updateMyProfile() {

		Customer currentCustomer = Session.getLoggedInCustomer();

		if (currentCustomer == null) {

			System.out.println("No user logged in.");
			return;

		}

		try {

			Customer customer = customerService.viewCustomer(currentCustomer.getCustomerId());

			System.out.println("\n========== UPDATE PROFILE ==========");

			String firstName = InputUtil.getString("First Name : ");
			String lastName = InputUtil.getString("Last Name : ");
			String email = InputUtil.getString("Email : ");
			String phone = InputUtil.getString("Phone : ");
			String password = InputUtil.getString("Password : ");
			String address = InputUtil.getString("Address : ");

			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setEmail(email);
			customer.setPhone(phone);
			customer.setPassword(password);
			customer.setAddress(address);

			// Customer type should NOT be changed by USER/RESELLER
			customer.setCustomerType(currentCustomer.getCustomerType());

			if (customerService.updateCustomer(customer)) {

				// Update current session object
				Session.setLoggedInCustomer(customer);

				System.out.println("\nProfile Updated Successfully.");

			} else {

				System.out.println("\nProfile Update Failed.");

			}

		} catch (CustomerNotFoundException e) {

			System.out.println(e.getMessage());

		}

	}

//=====================================
//View Customer By ID
//=====================================
	private void viewCustomerById() {

		int customerId = InputUtil.getInt("Enter Customer ID : ");

		try {

			Customer customer = customerService.viewCustomer(customerId);

			System.out.println();
			System.out.println(customer);

		} catch (CustomerNotFoundException e) {

			System.out.println(e.getMessage());

		}

	}

//=====================================
//View Customers With Pagination
//=====================================
	private void viewCustomersWithPagination() {

		int page = 1;

		while (true) {

			int totalCustomers = customerService.getCustomerCount();

			if (totalCustomers == 0) {

				System.out.println("\nNo Customers Found.");
				return;

			}

			int totalPages = (int) Math.ceil((double) totalCustomers / PAGE_SIZE);

			ArrayList<Customer> customers = customerService.viewCustomersByPage(page, PAGE_SIZE);

			System.out.println();
			System.out.println("==============================================================");
			System.out.printf("%-5s %-15s %-25s %-15s%n", "ID", "Name", "Email", "Type");
			System.out.println("==============================================================");

			for (Customer customer : customers) {

				System.out.printf("%-5d %-15s %-25s %-15s%n", customer.getCustomerId(),
						customer.getFirstName() + " " + customer.getLastName(), customer.getEmail(),
						customer.getCustomerType());

			}

			System.out.println("==============================================================");
			System.out.println("Page " + page + " of " + totalPages);
			System.out.println("==============================================================");

			System.out.println("1. Next Page");
			System.out.println("2. Previous Page");
			System.out.println("3. Exit");

			int choice = InputUtil.getInt("Enter Choice : ");

			switch (choice) {

			case 1:

				if (page < totalPages) {

					page++;

				} else {

					System.out.println("Already on the last page.");

				}

				break;

			case 2:

				if (page > 1) {

					page--;

				} else {

					System.out.println("Already on the first page.");

				}

				break;

			case 3:

				return;

			default:

				System.out.println("Invalid Choice.");

			}

		}

	}

//=====================================
//Search Customer
//=====================================
	private void searchCustomer() {

		String keyword = InputUtil.getString("Enter Name or Email : ");

		ArrayList<Customer> customers = customerService.searchCustomer(keyword);

		if (customers.isEmpty()) {

			System.out.println("\nNo Customer Found.");
			return;

		}

		System.out.println("\n========== SEARCH RESULT ==========");

		for (Customer customer : customers) {

			System.out.println(customer);

		}

	}

//=====================================
//Update Any Customer (ADMIN)
//=====================================
	private void updateAnyCustomer() {

		int customerId = InputUtil.getInt("Enter Customer ID : ");

		try {

			Customer customer = customerService.viewCustomer(customerId);

			System.out.println("\nLeave blank to keep existing value.");

			String firstName = InputUtil.getString("First Name (" + customer.getFirstName() + ") : ");
			if (!firstName.isEmpty())
				customer.setFirstName(firstName);

			String lastName = InputUtil.getString("Last Name (" + customer.getLastName() + ") : ");
			if (!lastName.isEmpty())
				customer.setLastName(lastName);

			String email = InputUtil.getString("Email (" + customer.getEmail() + ") : ");
			if (!email.isEmpty())
				customer.setEmail(email);

			String phone = InputUtil.getString("Phone (" + customer.getPhone() + ") : ");
			if (!phone.isEmpty())
				customer.setPhone(phone);

			String password = InputUtil.getString("Password (Press Enter to keep same) : ");
			if (!password.isEmpty())
				customer.setPassword(password);

			String address = InputUtil.getString("Address (" + customer.getAddress() + ") : ");
			if (!address.isEmpty())
				customer.setAddress(address);

			System.out.println("\nCustomer Type");
			System.out.println("1. ADMIN");
			System.out.println("2. USER");
			System.out.println("3. RESELLER");
			System.out.println("4. Keep Existing");

			int choice = InputUtil.getInt("Enter Choice : ");

			switch (choice) {

			case 1:
				customer.setCustomerType("ADMIN");
				break;

			case 2:
				customer.setCustomerType("USER");
				break;

			case 3:
				customer.setCustomerType("RESELLER");
				break;

			case 4:
				break;

			default:
				System.out.println("Invalid Choice.");
				return;

			}

			if (customerService.updateCustomer(customer)) {

				System.out.println("\nCustomer Updated Successfully.");

			} else {

				System.out.println("\nCustomer Update Failed.");

			}

		} catch (CustomerNotFoundException e) {

			System.out.println(e.getMessage());

		}

	}

//=====================================
//Delete Any Customer (ADMIN)
//=====================================
	private void deleteAnyCustomer() {

		int customerId = InputUtil.getInt("Enter Customer ID : ");

		Customer current = Session.getLoggedInCustomer();

		if (current != null && current.getCustomerId() == customerId) {

			System.out.println("You cannot delete your own account while logged in.");
			return;

		}

		String confirm = InputUtil.getString("Are you sure? (Y/N) : ");

		if (!confirm.equalsIgnoreCase("Y")) {

			System.out.println("Delete Cancelled.");
			return;

		}

		try {

			if (customerService.deleteCustomer(customerId)) {

				System.out.println("Customer Deleted Successfully.");

			}

		} catch (CustomerNotFoundException e) {

			System.out.println(e.getMessage());

		}

	}
}