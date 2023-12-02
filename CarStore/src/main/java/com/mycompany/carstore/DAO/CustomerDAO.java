package com.mycompany.carstore.DAO;

import com.mycompany.carstore.Beans.CustomerBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public CustomerDAO() {
    }
    
    public CustomerDAO(Connection con) {
        this.con = con;
    }
    
    public CustomerBean customerLogin(String email, String password) {
       CustomerBean customer = null;
       try {
           query = "select * from customers where email=? and password=?";
           pst = this.con.prepareStatement(query);
           pst.setString(1, email);
           pst.setString(2, password);
           rs = pst.executeQuery();
           if(rs.next()){
               customer = new CustomerBean();
               customer.setId(rs.getInt("id"));
               customer.setName(rs.getString("name"));
               customer.setEmail(rs.getString("email"));
               customer.setAddress(rs.getString("address"));
           }
       } catch (SQLException e) {
           System.out.print(e.getMessage());
       }
       return customer;
    }
    
    public List<CustomerBean> getAllCustomers() {
        List<CustomerBean> customers = new ArrayList<>();
        try {
            query = "SELECT * FROM customers";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                CustomerBean customer = new CustomerBean();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    
    public boolean createCustomer(CustomerBean customer) {
        boolean success = false;
        try {
            query = "INSERT INTO customers (name, email, password, address) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(query);
            pst.setString(1, customer.getName());
            pst.setString(2, customer.getEmail());
            pst.setString(3, customer.getPassword());
            pst.setString(4, customer.getAddress());

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
}
