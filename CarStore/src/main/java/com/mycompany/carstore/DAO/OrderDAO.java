package com.mycompany.carstore.DAO;

import com.mycompany.carstore.Beans.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class OrderDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/carstore?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "123456";

    private static final String INSERT_ORDER_SQL = "insert into orders (p_id, u_id, o_quantity, o_date) values(?,?,?,?)";
    private static final String ORDER_LIST_SQL = "select * from orders where u_id=? order by orders.o_id desc";
    private static final String CANCEL_ORDER_SQL = "delete from orders where o_id=?";
    public OrderDAO() {
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
    
    public boolean insertOrder(OrderBean order){
        boolean result = false;
        try (Connection connection = getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_SQL);) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, order.getUid());
            preparedStatement.setInt(3, order.getQunatity());
            preparedStatement.setString(4, order.getDate());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return result;
    }
    
    public List<OrderBean> userOrders(int id) {
        List<OrderBean> list = new ArrayList<>();
        try (Connection connection = getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(ORDER_LIST_SQL);){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                OrderBean order = new OrderBean();
                ProductDAO productDao = new ProductDAO();
                int pId = rs.getInt("p_id");
                
                ProductBean product = productDao.getSingleProduct(pId);
                order.setOrderId(rs.getInt("o_id"));
                order.setId(pId);
                order.setModelName(product.getModelName());
                order.setBranch(product.getBranch());
                order.setPrice(product.getPrice().multiply(new BigDecimal(rs.getInt("o_quantity"))));
                order.setQunatity(rs.getInt("o_quantity"));
                order.setDate(rs.getString("o_date"));
                list.add(order);
            }    
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public void cancelOrder(int id) {
        try (Connection connection = getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(CANCEL_ORDER_SQL);){
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }
    
    private void printSQLException(SQLException ex) {
	for (Throwable e : ex) {
            if (e instanceof SQLException) {
		e.printStackTrace(System.err);
		System.err.println("SQLState: " + ((SQLException) e).getSQLState());
		System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
		System.err.println("Message: " + e.getMessage());
		Throwable t = ex.getCause();
		while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
		}
            }
        }
    }
}
