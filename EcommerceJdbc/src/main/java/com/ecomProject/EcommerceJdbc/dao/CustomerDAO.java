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
    
    
    
    //helper method
    
 // ==========================
 // Helper Method
 // Maps ResultSet to Customer
 // ==========================
 private Customer mapCustomer(ResultSet rs) throws SQLException {

     Customer customer = new Customer();

     customer.setCustomerId(rs.getInt("customer_id"));
     customer.setFirstName(rs.getString("first_name"));
     customer.setLastName(rs.getString("last_name"));
     customer.setEmail(rs.getString("email"));
     customer.setPhone(rs.getString("phone"));
     customer.setPassword(rs.getString("password"));
     customer.setAddress(rs.getString("address"));
     customer.setCustomerType(rs.getString("customer_type"));

     return customer;
 }
 
 
//==========================
//Login Customer
//==========================
public Customer login(String email, String password) {

  String sql =
          "SELECT * FROM customer WHERE email = ? AND password = ?";

  try (Connection conn = DBConnection.getConnection();
       PreparedStatement ps = conn.prepareStatement(sql)) {

      ps.setString(1, email);
      ps.setString(2, password);

      ResultSet rs = ps.executeQuery();

      if (rs.next()) {

          return mapCustomer(rs);

      }

  } catch (SQLException e) {

      System.out.println("Error while logging in.");
      e.printStackTrace();
  }

  return null;
}


//==========================
//Get Customers By Page
//==========================
public ArrayList<Customer> getCustomersByPage(int page, int pageSize) {

 ArrayList<Customer> customers = new ArrayList<>();

 String sql =
         "SELECT * FROM customer ORDER BY customer_id LIMIT ? OFFSET ?";

 int offset = (page - 1) * pageSize;

 try (Connection conn = DBConnection.getConnection();
      PreparedStatement ps = conn.prepareStatement(sql)) {

     ps.setInt(1, pageSize);
     ps.setInt(2, offset);

     ResultSet rs = ps.executeQuery();

     while (rs.next()) {

         customers.add(mapCustomer(rs));

     }

 } catch (SQLException e) {

     System.out.println("Error while fetching paginated customers.");
     e.printStackTrace();
 }

 return customers;
}



//==========================
//Get Customer Count
//==========================
public int getCustomerCount() {

 String sql =
         "SELECT COUNT(*) FROM customer";

 try (Connection conn = DBConnection.getConnection();
      PreparedStatement ps = conn.prepareStatement(sql);
      ResultSet rs = ps.executeQuery()) {

     if (rs.next()) {

         return rs.getInt(1);

     }

 } catch (SQLException e) {

     System.out.println("Error while counting customers.");
     e.printStackTrace();
 }

 return 0;
}


//==========================
//Get Customer By Email
//==========================
public Customer getCustomerByEmail(String email) {

 String sql =
         "SELECT * FROM customer WHERE email = ?";

 try (Connection conn = DBConnection.getConnection();
      PreparedStatement ps = conn.prepareStatement(sql)) {

     ps.setString(1, email);

     ResultSet rs = ps.executeQuery();

     if (rs.next()) {

         return mapCustomer(rs);

     }

 } catch (SQLException e) {

     System.out.println("Error while fetching customer by email.");
     e.printStackTrace();
 }

 return null;
}



//==========================
//Search Customer
//==========================
public ArrayList<Customer> searchCustomer(String keyword) {

 ArrayList<Customer> customers = new ArrayList<>();

 String sql =
         "SELECT * FROM customer WHERE first_name LIKE ? OR last_name LIKE ?";

 try (Connection conn = DBConnection.getConnection();
      PreparedStatement ps = conn.prepareStatement(sql)) {

     String search = "%" + keyword + "%";

     ps.setString(1, search);
     ps.setString(2, search);

     ResultSet rs = ps.executeQuery();

     while (rs.next()) {

         customers.add(mapCustomer(rs));

     }

 } catch (SQLException e) {

     System.out.println("Error while searching customers.");
     e.printStackTrace();
 }

 return customers;
}


}