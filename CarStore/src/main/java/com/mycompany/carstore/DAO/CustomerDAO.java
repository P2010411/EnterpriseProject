package com.mycompany.carstore.DAO;

import com.mycompany.carstore.Beans.CustomerBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    
    private String jdbcURL = "jdbc:mysql://localhost:3306/carstore?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
    
    private static final String INSERT_USERS_SQL = "INSERT INTO customers" + "  (name, email, password, address) VALUES "
			+ " (?, ?, ?, ?);";
    
    private static final String SELECT_USER_BY_ID = "select id,name,email,password,address from customers where id =?";
    private static final String SELECT_ALL_USERS = "select * from customers";
    private static final String DELETE_USERS_SQL = "delete from customers where id = ?;";
    private static final String UPDATE_USERS_SQL = "update customers set name = ?,email= ?, password =?,address= ? where id = ?;";
    private static final String LOGIN_USES_SQL = "select * from customers where email=? and password=?";

    public CustomerDAO() {
    }
    
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	} catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	}
        // TODO Auto-generated catch block
	return connection;
    }
    
    public CustomerBean customerLogin(String email, String password) {
       CustomerBean customer = null;
       try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_USES_SQL)){
           preparedStatement.setString(1, email);
           preparedStatement.setString(2, password);
           ResultSet rs = preparedStatement.executeQuery();
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
    
    public void customerSignUp(CustomerBean customer) throws SQLException {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPassword());
            preparedStatement.setString(4, customer.getAddress());
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted <= 0) {
                throw new SQLException("Registration failed. Please try again.");
            }
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public List<CustomerBean> getAllCustomers() {
        List<CustomerBean> customers = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
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
}
