package com.mycompany.carstore.Servlets;

import com.mycompany.carstore.DAO.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/delete-product"})
public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try(PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");
            if(id != null) {
                ProductDAO productDAO = new ProductDAO();
                productDAO.deleteProduct(Integer.parseInt(id));
            }
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
