package com.ecomProject.EcommerceJdbc.CustomerTestCase;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.ecomProject.EcommerceJdbc.models.Customer;


public class CustomerTest {

    @Test
    public void testDefaultConstructor() {

        Customer customer = new Customer();

        assertNotNull(customer);
    }

    @Test
    public void testParameterizedConstructorWithoutId() {

        Customer customer = new Customer(
                "Rahul",
                "Sharma",
                "rahul@gmail.com",
                "9876543210",
                "rahul123",
                "Pune",
                "USER");

        assertEquals("Rahul", customer.getFirstName());
        assertEquals("Sharma", customer.getLastName());
        assertEquals("rahul@gmail.com", customer.getEmail());
        assertEquals("9876543210", customer.getPhone());
        assertEquals("rahul123", customer.getPassword());
        assertEquals("Pune", customer.getAddress());
        assertEquals("USER", customer.getCustomerType());
    }

    @Test
    public void testParameterizedConstructorWithId() {

        Customer customer = new Customer(
                10,
                "Amit",
                "Patel",
                "amit@gmail.com",
                "9876543211",
                "amit123",
                "Mumbai",
                "RESELLER");

        assertEquals(10, customer.getCustomerId());
        assertEquals("Amit", customer.getFirstName());
        assertEquals("Patel", customer.getLastName());
        assertEquals("amit@gmail.com", customer.getEmail());
        assertEquals("9876543211", customer.getPhone());
        assertEquals("amit123", customer.getPassword());
        assertEquals("Mumbai", customer.getAddress());
        assertEquals("RESELLER", customer.getCustomerType());
    }

    @Test
    public void testSettersAndGetters() {

        Customer customer = new Customer();

        customer.setCustomerId(100);
        customer.setFirstName("Karan");
        customer.setLastName("Singh");
        customer.setEmail("karan@gmail.com");
        customer.setPhone("9999999999");
        customer.setPassword("karan123");
        customer.setAddress("Delhi");
        customer.setCustomerType("USER");

        assertEquals(100, customer.getCustomerId());
        assertEquals("Karan", customer.getFirstName());
        assertEquals("Singh", customer.getLastName());
        assertEquals("karan@gmail.com", customer.getEmail());
        assertEquals("9999999999", customer.getPhone());
        assertEquals("karan123", customer.getPassword());
        assertEquals("Delhi", customer.getAddress());
        assertEquals("USER", customer.getCustomerType());
    }

    @Test
    public void testToString() {

        Customer customer = new Customer(
                1,
                "Rahul",
                "Sharma",
                "rahul@gmail.com",
                "9876543210",
                "rahul123",
                "Pune",
                "USER");

        String result = customer.toString();

        assertTrue(result.contains("Rahul"));
        assertTrue(result.contains("Sharma"));
        assertTrue(result.contains("USER"));
        assertTrue(result.contains("Pune"));
        assertTrue(result.contains("rahul@gmail.com"));
    }

    @Test
    public void testCustomerTypeUser() {

        Customer customer = new Customer();

        customer.setCustomerType("USER");

        assertEquals("USER", customer.getCustomerType());
    }

    @Test
    public void testCustomerTypeReseller() {

        Customer customer = new Customer();

        customer.setCustomerType("RESELLER");

        assertEquals("RESELLER", customer.getCustomerType());
    }
}