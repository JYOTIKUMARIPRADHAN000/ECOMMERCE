package com.ecomProject.EcommerceJdbc.utils;



import java.io.BufferedReader;
import com.ecomProject.EcommerceJdbc.models.Product;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.ecomProject.EcommerceJdbc.models.Product;

public class CsvReader {

	public static List<Product> readProductsFromCSV(String filePath) {
		List<Product> productList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(""))) {

			String line;

			// Skip Header
			br.readLine();

			while ((line = br.readLine()) != null) {

				String[] data = line.split(",");

				if (data.length == 5) {

					Product product = new Product();

					product.setProductName(data[0].trim());
					product.setDescription(data[1].trim());
					product.setPrice(Double.parseDouble(data[2].trim()));
					product.setStock(Integer.parseInt(data[3].trim()));
					product.setCategory(data[4].trim());

					productList.add(product);
				}
			}

		} catch (IOException e) {
			System.out.println("Error reading CSV file.");
			e.printStackTrace();

		} catch (NumberFormatException e) {
			System.out.println("Invalid data found in CSV.");
			e.printStackTrace();
		}

		return productList;
	}
}

