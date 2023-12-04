package com.mycompany.carstore.Servlets;

import com.mycompany.carstore.Beans.CustomerBean;
import com.mycompany.carstore.DAO.CustomerDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
        customerDAO = new CustomerDAO();
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
    
}
