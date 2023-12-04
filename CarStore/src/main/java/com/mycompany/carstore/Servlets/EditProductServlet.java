package com.mycompany.carstore.Servlets;

import com.mycompany.carstore.Beans.*;
import com.mycompany.carstore.DAO.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditProductServlet", urlPatterns = {"/edit-product"})
public class EditProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            int id = Integer.parseInt(request.getParameter("id"));
            String modelName = request.getParameter("model-name");
            int year = Integer.parseInt(request.getParameter("year"));
            String optionsPackage = request.getParameter("options-package");
            String color = request.getParameter("color");
            String motorSize = request.getParameter("motor-size");
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            double acceleration = Double.parseDouble(request.getParameter("acceleration"));
            String fuelType = request.getParameter("fuel-type");
            String image = request.getParameter("image");
            String branch = request.getParameter("branch");

            ProductBean product = new ProductBean(id, modelName, year, optionsPackage, color, motorSize, price, acceleration, fuelType, image, branch);
            ProductDAO productDAO = new ProductDAO();   
            try {
                productDAO.editProduct(product);
                response.sendRedirect("index.jsp");
            } catch (Exception e) {
                out.println("Failed to add product. Please try again.");
            }
        }
    }
}
