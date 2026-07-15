package com.ecomProject.EcommerceJdbc.CustomerTestCase;



import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.ecomProject.EcommerceJdbc.exception.CustomerNotFoundException;
import com.ecomProject.EcommerceJdbc.models.Customer;
import com.ecomProject.EcommerceJdbc.services.CustomerService;


public class CustomerServiceTest {

    private CustomerService service = new CustomerService();

    // ==========================
    // Validation Test Cases
    // ==========================

    @Test
    public void testValidateCustomer_ValidCustomer() {

        Customer customer = new Customer(
                "Rahul",
                "Sharma",
                "rahul123@gmail.com",
                "9876543210",
                "rahul123",
                "Pune",
                "USER");

        assertTrue(service.validateCustomer(customer));
    }

    @Test
    public void testValidateCustomer_EmptyFirstName() {

        Customer customer = new Customer(
                "",
                "Sharma",
                "rahul@gmail.com",
                "9876543210",
                "rahul123",
                "Pune",
                "USER");

        assertFalse(service.validateCustomer(customer));
    }

    @Test
    public void testValidateCustomer_EmptyLastName() {

        Customer customer = new Customer(
                "Rahul",
                "",
                "rahul@gmail.com",
                "9876543210",
                "rahul123",
                "Pune",
                "USER");

        assertFalse(service.validateCustomer(customer));
    }

    @Test
    public void testValidateCustomer_InvalidEmail() {

        Customer customer = new Customer(
                "Rahul",
                "Sharma",
                "rahulgmail.com",
                "9876543210",
                "rahul123",
                "Pune",
                "USER");

        assertFalse(service.validateCustomer(customer));
    }

    @Test
    public void testValidateCustomer_InvalidPhone() {

        Customer customer = new Customer(
                "Rahul",
                "Sharma",
                "rahul@gmail.com",
                "98765",
                "rahul123",
                "Pune",
                "USER");

        assertFalse(service.validateCustomer(customer));
    }

    @Test
    public void testValidateCustomer_InvalidPassword() {

        Customer customer = new Customer(
                "Rahul",
                "Sharma",
                "rahul@gmail.com",
                "9876543210",
                "123",
                "Pune",
                "USER");

        assertFalse(service.validateCustomer(customer));
    }

    @Test
    public void testValidateCustomer_EmptyAddress() {

        Customer customer = new Customer(
                "Rahul",
                "Sharma",
                "rahul@gmail.com",
                "9876543210",
                "rahul123",
                "",
                "USER");

        assertFalse(service.validateCustomer(customer));
    }

    @Test
    public void testValidateCustomer_InvalidCustomerType() {

        Customer customer = new Customer(
                "Rahul",
                "Sharma",
                "rahul@gmail.com",
                "9876543210",
                "rahul123",
                "Pune",
                "ADMIN");

        assertFalse(service.validateCustomer(customer));
    }

    // ==========================
    // Register Customer
    // ==========================

    @Test
    public void testRegisterCustomer() {

        Customer customer = new Customer(
                "JUnit",
                "Register",
                "register" + System.currentTimeMillis() + "@gmail.com",
                "98" + (int)(Math.random() * 100000000),
                "password123",
                "Bangalore",
                "USER");

        assertTrue(service.registerCustomer(customer));
    }

    @Test
    public void testRegisterCustomer_InvalidCustomer() {

        Customer customer = new Customer(
                "",
                "",
                "wrong",
                "123",
                "1",
                "",
                "ABC");

        assertFalse(service.registerCustomer(customer));
    }

    // ==========================
    // View Customer
    // ==========================

    @Test
    public void testViewCustomer() throws CustomerNotFoundException {

        Customer customer = service.viewCustomer(1);

        assertNotNull(customer);
    }

    @Test
    public void testViewCustomer_InvalidId() throws CustomerNotFoundException {

        Customer customer = service.viewCustomer(999999);

        assertNull(customer);
    }

    // ==========================
    // View All Customers
    // ==========================

    @Test
    public void testViewAllCustomers() {

        ArrayList<Customer> list = service.viewAllCustomers();

        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    // ==========================
    // Update Customer
    // ==========================

    @Test
    public void testUpdateCustomer() throws CustomerNotFoundException {

        Customer customer = service.viewCustomer(1);

        assertNotNull(customer);

        customer.setAddress("Updated Address");

        assertTrue(service.updateCustomer(customer));
    }

    // ==========================
    // Delete Customer
    // ==========================

    @Test
    public void testDeleteCustomer() throws CustomerNotFoundException {

        Customer customer = new Customer(
                "Delete",
                "Test",
                "delete" + System.currentTimeMillis() + "@gmail.com",
                "97" + (int)(Math.random() * 100000000),
                "delete123",
                "Delhi",
                "USER");

        service.registerCustomer(customer);

        ArrayList<Customer> list = service.viewAllCustomers();

        int id = list.get(list.size() - 1).getCustomerId();

        assertTrue(service.deleteCustomer(id));
    }

    @Test
    public void testDeleteCustomer_InvalidId() throws CustomerNotFoundException {

        assertFalse(service.deleteCustomer(999999));
    }

}