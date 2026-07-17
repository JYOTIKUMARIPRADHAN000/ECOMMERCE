package com.ecomProject.EcommerceJdbc.menu;

import com.ecomProject.EcommerceJdbc.exception.CustomerNotFoundException;
import com.ecomProject.EcommerceJdbc.utils.InputUtil;

public class MainMenu {

	private CustomerMenu customerMenu = new CustomerMenu();
	ProductMenu productMenu = new ProductMenu();

	// These will be added by your team mates
	// private ProductMenu productMenu = new ProductMenu();
	// private CartMenu cartMenu = new CartMenu();
	// private OrderMenu orderMenu = new OrderMenu();

	public void showMainMenu() throws CustomerNotFoundException {

		int choice;

		do {

			System.out.println("\n==========================================");
			System.out.println("       E-COMMERCE MANAGEMENT SYSTEM");
			System.out.println("==========================================");
			System.out.println("1. Customer Management");
			System.out.println("2. Product Management");
			System.out.println("3. Cart Management");
			System.out.println("4. Order Management");
			System.out.println("5. Exit");
			System.out.println("==========================================");

			choice = InputUtil.getInt("Enter your choice: ");

			switch (choice) {

			case 1:
				customerMenu.showMenu();
				break;

			case 2:
				productMenu.displayMenu();
				break;

			case 3:
				System.out.println("Cart Module is under development.");
				// cartMenu.showMenu();
				break;

			case 4:
				System.out.println("Order Module is under development.");
				// orderMenu.showMenu();
				break;

			case 5:
				System.out.println("Thank you for using the E-Commerce System.");
				break;

			default:
				System.out.println("Invalid choice! Please try again.");
			}

		} while (choice != 5);
	}
}
