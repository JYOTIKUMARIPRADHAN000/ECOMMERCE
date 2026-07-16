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
                101,
                "Laptop",
                "Electronics",
                55000,
                5);

        assertTrue(service.addProduct(product));
    }

    @Test
    public void testSearchProduct() {

        assertNotNull(service.searchProduct(101));
    }

    @Test
    public void testUpdateProduct() {

        Product product = new Product(
                101,
                "Gaming Laptop",
                "Electronics",
                65000,
                8);

        assertTrue(service.editProduct(product));
    }

    @Test
    public void testViewProducts() {

        assertFalse(service.viewProducts().isEmpty());
    }

    @Test
    public void testDeleteProduct() {

        assertTrue(service.removeProduct(101));
    }
}