package com.ecomProject.EcommerceJdbc.menu;

import java.util.List;
import java.util.Scanner;

import com.ecomProject.EcommerceJdbc.models.Order;
import com.ecomProject.EcommerceJdbc.services.OrderService;


public class OrderMenu {

	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);

		OrderService service = new OrderService();

		while (true) {
			
			System.out.println("\n===== ORDER MENU =====");

			System.out.println("\n1. Place Order");

			System.out.println("2. View Orders");

			System.out.println("3. Back");

			System.out.print("Enter Choice : ");

			int choice = sc.nextInt();
			
			 

			switch (choice) {

			
			case 1:

				System.out.print("Customer ID : ");

				int cid = sc.nextInt();

				System.out.print("Product ID : ");

				int pid = sc.nextInt();

				System.out.print("Quantity : ");

				int qty = sc.nextInt();

				Order order = new Order(cid, pid, qty);

				service.placeOrder(order);

				break;

			case 2:

				System.out.print("Customer ID : ");

				int customer = sc.nextInt();

				List<Order> orders = service.viewOrders(customer);

				for (Order o : orders) {

					System.out.println(o);

				}

				break;

			case 3:

				System.out.println("Returning");

				System.exit(0);

			default:

				System.out.println("Invalid Choice");

			}

		}

	}

}