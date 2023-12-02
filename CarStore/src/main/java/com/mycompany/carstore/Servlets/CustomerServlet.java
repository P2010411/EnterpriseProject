package com.mycompany.carstore.Servlets;

import com.mycompany.carstore.Beans.CustomerBean;
import com.mycompany.carstore.DAO.CustomerDAO;
import com.google.gson.Gson;
import com.mycompany.carstore.Connection.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/customers"})
public class CustomerServlet extends HttpServlet {
    
    private CustomerDAO customerDAO;
   
    @Override
    public void init() {
        try {
            customerDAO = new CustomerDAO(DBConnection.getConnection());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CustomerBean> customers = customerDAO.getAllCustomers();
        Gson gson = new Gson();
        String json = gson.toJson(customers);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");

        CustomerBean customer = new CustomerBean(name, email, password, address);

        boolean success = customerDAO.createCustomer(customer);

        if (success) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("Customer created successfully");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to create customer");
        }
    }
}
