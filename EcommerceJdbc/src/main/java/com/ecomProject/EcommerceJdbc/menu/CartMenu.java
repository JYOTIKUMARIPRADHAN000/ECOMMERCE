package com.ecomProject.EcommerceJdbc.menu;

import java.sql.SQLException;
import java.util.Scanner;

import com.ecomProject.EcommerceJdbc.models.Cart;
import com.ecomProject.EcommerceJdbc.services.CartService;



public class CartMenu {

	public static void cartMenu() throws ClassNotFoundException, SQLException {

		Scanner sc = new Scanner(System.in);

		CartService service = new CartService();

		int choice;

		do {
			System.out.println("\n===== CART MENU =====");

			System.out.println("1 Add To Cart");
			System.out.println("2 Remove Item");
			System.out.println("3 Update Quantity");
			System.out.println("4 View Cart");
			System.out.println("5 Clear Cart");
			System.out.println("6 Back");

			System.out.print("Enter Choice : ");

			choice = sc.nextInt();

			switch (choice) {

			case 1:

				System.out.print("Product Id : ");
				int id = sc.nextInt();

				sc.nextLine();

				System.out.print("Product Name : ");
				String name = sc.nextLine();

				System.out.print("Price : ");
				double price = sc.nextDouble();

				System.out.print("Quantity : ");
				int qty = sc.nextInt();

				service.addItem(new Cart(id, name, price, qty));

				break;

			case 2:

				System.out.print("Product Id : ");

				service.removeItem(sc.nextInt());

				break;

			case 3:

				System.out.print("Product Id : ");
				int pid = sc.nextInt();

				System.out.print("New Quantity : ");
				int quantity = sc.nextInt();

				service.increaseQuantity(pid, quantity);

				break;

			case 4:

				service.viewCart();

				System.out.println("Cart Total = " + service.calculateCartTotal());

				break;

			case 5:

				service.clearCart();

				break;

			case 6:

				System.out.println("Returning...");

				break;

			default:

				System.out.println("Invalid Choice");

			}

		} while (choice != 6);

	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		CartMenu m = new CartMenu();
		m.cartMenu();
	}

}