package com.mycompany.carstore.Servlets;

import com.google.gson.Gson;
import com.mycompany.carstore.Beans.ProductBean;
import com.mycompany.carstore.DAO.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductServlet", urlPatterns = {"/products"})
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;
    
    @Override
    public void init() {
        productDAO = new ProductDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        List<ProductBean> products = productDAO.selectAllProducts();
//        request.setAttribute("products", products);
//        request.getRequestDispatcher("index.jsp").forward(request, response);
//        Gson gson = new Gson();
//        String json = gson.toJson(products);
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        out.print(json);
//        out.flush();
    }
}
