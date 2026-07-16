package com.ecomProject.EcommerceJdbc.menu;

package com.ecommerce;

import com.ecommerce.ProductMenu;

public class Main {

	public static void main(String[] args) {

		System.out.println(" ECOMMERCE MANAGEMENT SYSTEM");

		ProductMenu productMenu = new ProductMenu();
		productMenu.displayMenu();

		System.out.println("Application Closed.");
	}
}
