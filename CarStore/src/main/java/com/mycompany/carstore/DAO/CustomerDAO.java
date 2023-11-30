/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.carstore.DAO;

import com.mycompany.carstore.Beans.CustomerBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 23956
 */
public class CustomerDAO {
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

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
}
