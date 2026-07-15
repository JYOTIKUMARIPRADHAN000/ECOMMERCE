package com.ecomProject.EcommerceJdbc.CustomerTestCase;



import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ecomProject.EcommerceJdbc.dao.CustomerDAO;
import com.ecomProject.EcommerceJdbc.models.Customer;


public class CustomerDAOTest {

    private CustomerDAO customerDAO;

    @BeforeEach
    public void setup() {
        customerDAO = new CustomerDAO();
    }

    // ==========================
    // Add Customer
    // ==========================

    @Test
    public void testAddCustomer() {

        Customer customer = new Customer(
                "DAO",
                "Add",
                "daoadd" + System.currentTimeMillis() + "@gmail.com",
                "91" + (int)(Math.random() * 100000000),
                "password123",
                "Pune",
                "USER");

        assertTrue(customerDAO.addCustomer(customer));
    }

    // ==========================
    // Get Customer By ID
    // ==========================

    @Test
    public void testGetCustomerById() {

        Customer customer = customerDAO.getCustomerById(1);

        assertNotNull(customer);
        assertEquals(1, customer.getCustomerId());
    }

    @Test
    public void testGetCustomerById_Invalid() {

        Customer customer = customerDAO.getCustomerById(999999);

        assertNull(customer);
    }

    // ==========================
    // Get All Customers
    // ==========================

    @Test
    public void testGetAllCustomers() {

        ArrayList<Customer> customers = customerDAO.getAllCustomers();

        assertNotNull(customers);
        assertFalse(customers.isEmpty());
    }

    // ==========================
    // Update Customer
    // ==========================

    @Test
    public void testUpdateCustomer() {

        Customer customer = new Customer(
                "Update",
                "Test",
                "update" + System.currentTimeMillis() + "@gmail.com",
                "92" + (int)(Math.random() * 100000000),
                "password123",
                "Delhi",
                "USER");

        assertTrue(customerDAO.addCustomer(customer));

        ArrayList<Customer> customers = customerDAO.getAllCustomers();

        Customer lastCustomer = customers.get(customers.size() - 1);

        lastCustomer.setAddress("Mumbai");

        assertTrue(customerDAO.updateCustomer(lastCustomer));

        Customer updated =
                customerDAO.getCustomerById(lastCustomer.getCustomerId());

        assertEquals("Mumbai", updated.getAddress());
    }

    // ==========================
    // Delete Customer
    // ==========================

    @Test
    public void testDeleteCustomer() {

        Customer customer = new Customer(
                "Delete",
                "DAO",
                "delete" + System.currentTimeMillis() + "@gmail.com",
                "93" + (int)(Math.random() * 100000000),
                "password123",
                "Jaipur",
                "USER");

        assertTrue(customerDAO.addCustomer(customer));

        ArrayList<Customer> customers =
                customerDAO.getAllCustomers();

        int id = customers.get(customers.size() - 1).getCustomerId();

        assertTrue(customerDAO.deleteCustomer(id));

        assertNull(customerDAO.getCustomerById(id));
    }

    @Test
    public void testDeleteCustomer_InvalidId() {

        assertFalse(customerDAO.deleteCustomer(999999));
    }

}