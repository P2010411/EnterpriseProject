package com.mycompany.carstore.DAO;

import com.mycompany.carstore.Beans.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/carstore?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "123456";
    
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product" + "  (modelName, year, optionsPackage, color, motorSize, price, acceleration, fuelType, image, branch) VALUES "
        + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_PRODUCT_BY_ID = "select id, modelName, year, optionsPackage, color, motorSize, price, acceleration, fuelType, image, branch from product where id =?";
    private static final String SELECT_ALL_PRODUCTS = "select * from product";
    private static final String DELETE_PRODUCT_SQL = "delete from product where id = ?;";
    private static final String UPDATE_PRODUCT_SQL = "update product set modelName = ?, year = ?, optionsPackage = ?, color = ?, motorSize = ?, price = ?, acceleration = ?, fuelType = ?, image = ?, branch = ? where id = ?;";
    private static final String GET_CART_PRODUCTS_SQL = "select * from product where id=?";
    private static final String GET_CART_PRICE_SQL = "select price from product where id=?";
    private static final String GET_SINGLE_PRODUCTS_SQL = "select * from product where id=?";
    
    public ProductDAO() {
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
     
    public List<ProductBean> selectAllProducts() {
        List<ProductBean> products = new ArrayList<>();
        try (Connection connection = getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ProductBean product = new ProductBean();
                product.setId(rs.getInt("id"));
                product.setModelName(rs.getString("modelName"));
                product.setYear(rs.getInt("year"));
                product.setOptionsPackage(rs.getString("optionsPackage"));
                product.setColor(rs.getString("color"));
                product.setMotorSize(rs.getString("motorSize"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setAcceleration(rs.getDouble("acceleration"));
                product.setFuelType(rs.getString("fuelType"));
                product.setImage(rs.getString("image"));
                product.setBranch(rs.getString("branch"));
                products.add(product);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }
    
    public List<CartBean> getCartProducts(ArrayList<CartBean> cartList) {
        List<CartBean> products = new ArrayList<>();
        
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CART_PRODUCTS_SQL);){
            if (!cartList.isEmpty()) {
                for (CartBean item : cartList) {
                    preparedStatement.setInt(1, item.getId());
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        CartBean product = new CartBean();
                        product.setId(rs.getInt("id"));
                        product.setModelName(rs.getString("modelName"));
                        product.setYear(rs.getInt("year"));
                        product.setOptionsPackage(rs.getString("optionsPackage"));
                        product.setColor(rs.getString("color"));
                        product.setMotorSize(rs.getString("motorSize"));
                        product.setPrice(rs.getBigDecimal("price") .multiply(new BigDecimal(item.getQuantity())));
                        product.setAcceleration(rs.getDouble("acceleration"));
                        product.setFuelType(rs.getString("fuelType"));
                        product.setImage(rs.getString("image"));
                        product.setBranch(rs.getString("branch"));
                        product.setQuantity(item.getQuantity());
                        products.add(product);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return products;
    }
    
    public BigDecimal getTotalCartPrice(ArrayList<CartBean> cartList){
        BigDecimal sum = new BigDecimal("0");
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CART_PRICE_SQL);) {
            if (!cartList.isEmpty()) {
                for (CartBean item : cartList) {
                    preparedStatement.setInt(1, item.getId());
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        BigDecimal price = rs.getBigDecimal("price").multiply(new BigDecimal(item.getQuantity()));
                        sum = sum.add(price);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sum;
    }
    
    public ProductBean getSingleProduct(int id) {
        ProductBean product = null;
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SINGLE_PRODUCTS_SQL);){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                product = new ProductBean();
	        product.setId(rs.getInt("id"));
                product.setModelName(rs.getString("modelName"));
                product.setYear(rs.getInt("year"));
                product.setOptionsPackage(rs.getString("optionsPackage"));
                product.setColor(rs.getString("color"));
                product.setMotorSize(rs.getString("motorSize"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setAcceleration(rs.getDouble("acceleration"));
                product.setFuelType(rs.getString("fuelType"));
                product.setImage(rs.getString("image"));
                product.setBranch(rs.getString("branch"));
            }   
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } 
        return product;
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

//    public void insertProduct(ProductBean product) throws SQLException {
//        System.out.println(INSERT_PRODUCT_SQL);
//        // try-with-resource statement will auto close the connection.
//        try (Connection connection = getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
//            preparedStatement.setString(1, product.getModelName());
//            preparedStatement.setInt(2, product.getYear());
//            preparedStatement.setString(3, product.getOptionsPackage());
//            preparedStatement.setString(4, product.getColor());
//            preparedStatement.setString(5, product.getMotorSize());
//            preparedStatement.setBigDecimal(6, product.getPrice());
//            preparedStatement.setDouble(7, product.getAcceleration());
//            preparedStatement.setString(8, product.getFuelType());
//            preparedStatement.setString(9, product.getImage());
//            preparedStatement.setString(10, product.getBranch());
//            System.out.println(preparedStatement);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//    }
    
//    public ProductBean selectProduct(int id) {
//        ProductBean product = null;
//        try (Connection connection = getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
//            preparedStatement.setInt(1, id);
//            System.out.println(preparedStatement);
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                String modelName = rs.getString("modelName");
//                int year = rs.getInt("year");
//                String optionsPackage = rs.getString("optionsPackage");
//                String color = rs.getString("color");
//                String motorSize = rs.getString("motorSize");
//                BigDecimal price = rs.getBigDecimal("price");
//                double acceleration = rs.getDouble("acceleration");
//                String fuelType = rs.getString("fuelType");
//                String image = rs.getString("image");
//                String branch = rs.getString("branch");
//                product = new ProductBean(id, modelName, year, optionsPackage, color, motorSize, price, acceleration, fuelType, image, branch);
//            }
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//        return product;
//    }
    
//    public boolean deleteProduct(int id) throws SQLException {
//        boolean rowDeleted;
//        try (Connection connection = getConnection();
//            PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL);) {
//            statement.setInt(1, id);
//            rowDeleted = statement.executeUpdate() > 0;
//        }
//        return rowDeleted;
//    }
    
//    public boolean updateProduct(ProductBean product) throws SQLException {
//        boolean rowUpdated;
//        try (Connection connection = getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL);) {
//            preparedStatement.setString(1, product.getModelName());
//            preparedStatement.setInt(2, product.getYear());
//            preparedStatement.setString(3, product.getOptionsPackage());
//            preparedStatement.setString(4, product.getColor());
//            preparedStatement.setString(5, product.getMotorSize());
//            preparedStatement.setBigDecimal(6, product.getPrice());
//            preparedStatement.setDouble(7, product.getAcceleration());
//            preparedStatement.setString(8, product.getFuelType());
//            preparedStatement.setString(9, product.getImage());
//            preparedStatement.setString(10, product.getBranch());
//            preparedStatement.setInt(11, product.getId());
//            rowUpdated = preparedStatement.executeUpdate() > 0;
//        }
//        return rowUpdated;
//    } 
}
