package com.ecomProject.EcommerceJdbc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.ecomProject.EcommerceJdbc.models.Product;
import com.ecomProject.EcommerceJdbc.services.ProductService;

public class ProductTestCases {

    ProductService service = new ProductService();

    @Test
    public void testAddProduct() {

        Product product = new Product(
                0,
                "Laptop",
                "Electronics",
<<<<<<< Updated upstream
                50000,
                5,
                "Good laptop"
        );
=======
                55000,
                5, null);
>>>>>>> Stashed changes

        assertTrue(service.addProduct(product));
    }

    @Test
    public void testSearchProduct() {

        Product product = service.searchProduct(1); // Change to an ID that exists in your DB
        assertNotNull(product);
    }

    @Test
    public void testUpdateProduct() {

        Product product = new Product(
                1,                      // Existing product ID
                "Laptop Updated",
                "Electronics",
<<<<<<< Updated upstream
                55000,
                10,
                "Updated gaming laptop"
        );
=======
                65000,
                8, null);
>>>>>>> Stashed changes

        assertTrue(service.editProduct(product));
    }

    @Test
    public void testViewProducts() {

        assertFalse(service.viewProducts().isEmpty());
    }

    @Test
    public void testDeleteProduct() {

        assertTrue(service.removeProduct(1)); // Existing product ID
    }
}