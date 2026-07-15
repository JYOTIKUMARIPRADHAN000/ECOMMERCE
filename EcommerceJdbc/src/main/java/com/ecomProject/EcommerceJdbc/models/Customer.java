package com.ecomProject.EcommerceJdbc.models;



public class Customer {

    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String address;
    private String customerType; // USER or RESELLER

    // Default Constructor
    public Customer() {

    }

    // Constructor without customerId
    public Customer(String firstName, String lastName, String email,
                    String phone, String password,
                    String address, String customerType) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.customerType = customerType;
    }

    // Constructor with customerId
    public Customer(int customerId, String firstName, String lastName,
                    String email, String phone, String password,
                    String address, String customerType) {

        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.customerType = customerType;
    }

    // Getters and Setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    @Override
    public String toString() {

        return "Customer ID : " + customerId +
                "\nName        : " + firstName + " " + lastName +
                "\nEmail       : " + email +
                "\nPhone       : " + phone +
                "\nAddress     : " + address +
                "\nType        : " + customerType +
                "\n--------------------------------------";
    }

}