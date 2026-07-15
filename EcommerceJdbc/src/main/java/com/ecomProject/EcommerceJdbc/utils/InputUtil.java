package com.ecomProject.EcommerceJdbc.utils;

import java.util.Scanner;

public class InputUtil {

	private static final Scanner scanner = new Scanner(System.in);

	private InputUtil() {

	}

	public static int getInt(String message) {

		while (true) {

			System.out.print(message);

			try {

				int value = Integer.parseInt(scanner.nextLine());
				return value;

			} catch (NumberFormatException e) {

				System.out.println("Invalid number! Please enter a valid integer.");
			}
		}
	}

	public static double getDouble(String message) {

		while (true) {

			System.out.print(message);

			try {

				double value = Double.parseDouble(scanner.nextLine());
				return value;

			} catch (NumberFormatException e) {

				System.out.println("Invalid number! Please enter a valid decimal value.");
			}
		}
	}

	public static String getString(String message) {

		System.out.print(message);
		return scanner.nextLine().trim();
	}

	public static void closeScanner() {
		scanner.close();
	}

}
