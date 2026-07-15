package com.ecomProject.EcommerceJdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ecomProject.EcommerceJdbc.models.Customer;
import com.ecomProject.EcommerceJdbc.utils.DBConnection;

public class CustomerDAO {

    // ==========================
    // Add Customer
    // ==========================
    public boolean addCustomer(Customer customer) {

        String sql = "INSERT INTO customer(first_name,last_name,email,phone,password,address,customer_type) "
                + "VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhone());
            ps.setString(5, customer.getPassword());
            ps.setString(6, customer.getAddress());
            ps.setString(7, customer.getCustomerType());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println("Error while adding customer.");
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // Update Customer
    // ==========================
    public boolean updateCustomer(Customer customer) {

        String sql = "UPDATE customer SET first_name=?,last_name=?,email=?,phone=?,password=?,address=?,customer_type=? WHERE customer_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhone());
            ps.setString(5, customer.getPassword());
            ps.setString(6, customer.getAddress());
            ps.setString(7, customer.getCustomerType());
            ps.setInt(8, customer.getCustomerId());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println("Error while updating customer.");
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // Delete Customer
    // ==========================
    public boolean deleteCustomer(int customerId) {

        String sql = "DELETE FROM customer WHERE customer_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println("Error while deleting customer.");
            e.printStackTrace();
        }

        return false;
    }

    // ==========================
    // Get Customer By Id
    // ==========================
    public Customer getCustomerById(int customerId) {

        String sql = "SELECT * FROM customer WHERE customer_id=?";

        Customer customer = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                customer = new Customer();

                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setPassword(rs.getString("password"));
                customer.setAddress(rs.getString("address"));
                customer.setCustomerType(rs.getString("customer_type"));
            }

        } catch (SQLException e) {

            System.out.println("Error while fetching customer.");
            e.printStackTrace();
        }

        return customer;
    }

    // ==========================
    // Get All Customers
    // ==========================
    public ArrayList<Customer> getAllCustomers() {

        ArrayList<Customer> customers = new ArrayList<>();

        String sql = "SELECT * FROM customer ORDER BY customer_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Customer customer = new Customer();

                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setPassword(rs.getString("password"));
                customer.setAddress(rs.getString("address"));
                customer.setCustomerType(rs.getString("customer_type"));

                customers.add(customer);
            }

        } catch (SQLException e) {

            System.out.println("Error while fetching customers.");
            e.printStackTrace();
        }

        return customers;
    }

}