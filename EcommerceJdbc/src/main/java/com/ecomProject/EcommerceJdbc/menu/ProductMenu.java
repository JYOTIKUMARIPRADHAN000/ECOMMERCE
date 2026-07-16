package com.ecomProject.EcommerceJdbc.menu;

import java.util.List;
import java.util.Scanner;

import com.ecomProject.EcommerceJdbc.models.Product;
import com.ecomProject.EcommerceJdbc.services.ProductService;
import com.ecomProject.EcommerceJdbc.utils.CsvReader;

public class ProductMenu {

    private Scanner scanner;
    private ProductService productService;

    public ProductMenu() {
        scanner = new Scanner(System.in);
        productService = new ProductService();
    }

    public void displayMenu() {

        int choice;

        do {

            System.out.println("\n========== PRODUCT MENU ==========");
            System.out.println("1. Import Products from CSV");
            System.out.println("2. Add Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. View Products");
            System.out.println("6. Search Product");
            System.out.println("7. Check Stock");
            System.out.println("8. Update Stock");
            System.out.println("9. Back / Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {

            case 1:
                importProducts();
                break;

            case 2:
                addProduct();
                break;

            case 3:
                updateProduct();
                break;

            case 4:
                deleteProduct();
                break;

            case 5:
                viewProducts();
                break;

            case 6:
                searchProduct();
                break;

            case 7:
                checkStock();
                break;

            case 8:
                updateStock();
                break;

            case 9:
                System.out.println("Returning...");
                break;

            default:
                System.out.println("Invalid Choice.");
            }

        } while (choice != 9);
    }

    // Import Products from CSV
    private void importProducts() {

        scanner.nextLine();

        System.out.print("Enter CSV File Path : ");
        String filePath = scanner.nextLine();

        List<Product> products = CsvReader.readProductsFromCSV(filePath);

        if (products == null || products.isEmpty()) {
            System.out.println("No products found in CSV.");
            return;
        }

        int count = 0;

        for (Product product : products) {

            if (productService.addProduct(product)) {
                count++;
            }
        }

        System.out.println(count + " Products Imported Successfully.");
    }

    // Add Product
    private void addProduct() {

        Product product = new Product();

        scanner.nextLine();

        System.out.print("Enter Description : ");
        product.setDescription(scanner.nextLine());

        System.out.print("Enter Product Name : ");
        product.setProductName(scanner.nextLine());

        System.out.print("Enter Category : ");
        product.setCategory(scanner.nextLine());

        System.out.print("Enter Price : ");
        product.setPrice(scanner.nextDouble());

        System.out.print("Enter Stock : ");
        product.setStock(scanner.nextInt());

        if (productService.addProduct(product)) {
            System.out.println("Product Added Successfully.");
        } else {
            System.out.println("Failed to Add Product.");
        }
    }

    // Update Product
    private void updateProduct() {

        Product product = new Product();

        scanner.nextLine();

        System.out.print("Enter Product ID : ");
        product.setProductId(scanner.nextInt());

        // VERY IMPORTANT
        scanner.nextLine();

        System.out.print("Enter New Product Name : ");
        product.setProductName(scanner.nextLine());

        System.out.print("Enter New Description : ");
        product.setDescription(scanner.nextLine());

        System.out.print("Enter New Category : ");
        product.setCategory(scanner.nextLine());

        System.out.print("Enter New Price : ");
        product.setPrice(scanner.nextDouble());

        System.out.print("Enter New Stock : ");
        product.setStock(scanner.nextInt());

        if (productService.editProduct(product)) {
            System.out.println("Product Updated Successfully.");
        } else {
            System.out.println("Product Update Failed.");
        }
    }

    // Delete Product
    private void deleteProduct() {

        System.out.print("Enter Product ID to Delete : ");
        int productId = scanner.nextInt();

        if (productService.removeProduct(productId)) {
            System.out.println("Product Deleted Successfully.");
        } else {
            System.out.println("Product Not Found.");
        }
    }

    // View Products
    private void viewProducts() {

        List<Product> productList = productService.viewProducts();

        if (productList.isEmpty()) {
            System.out.println("No Products Available.");
            return;
        }

        System.out.println("\n========== PRODUCT LIST ==========");

        for (Product product : productList) {
            System.out.println(product);
            System.out.println("-----------------------------------");
        }
    }

    // Search Product
    private void searchProduct() {

        System.out.print("Enter Product ID : ");
        int productId = scanner.nextInt();

        Product product = productService.searchProduct(productId);

        if (product != null) {
            System.out.println("\nProduct Found");
            System.out.println(product);
        } else {
            System.out.println("Product Not Found.");
        }
    }

    // Check Stock
    private void checkStock() {

        System.out.print("Enter Product ID : ");
        int productId = scanner.nextInt();

        productService.checkStock(productId);
    }

    // Update Stock
    private void updateStock() {

        System.out.print("Enter Product ID : ");
        int productId = scanner.nextInt();

        System.out.print("Enter New Stock : ");
        int stock = scanner.nextInt();

        if (productService.updateStock(productId, stock)) {
            System.out.println("Stock Updated Successfully.");
        } else {
            System.out.println("Stock Update Failed.");
        }
    }
}