/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.carstore;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author 23956
 */
public class TestConnection {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            // 通过JDBC获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carstore", "root", "123456");

            // 连接成功
            System.out.println("Database connection successful!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        } catch (SQLException e) {
            // 连接失败
            System.out.println("Database connection failed: " + e.getMessage());
        } finally {
            // 关闭连接
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close database connection: " + e.getMessage());
                }
            }
        }
    }
}
