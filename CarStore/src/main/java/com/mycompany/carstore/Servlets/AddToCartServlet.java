package com.mycompany.carstore.Servlets;

import com.mycompany.carstore.Beans.CartBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddToCartServlet", urlPatterns = {"/add-to-cart"})
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            ArrayList<CartBean> cartList = new ArrayList<>(); 
            //get id from url
            int id = Integer.parseInt(request.getParameter("id"));
            CartBean cm = new CartBean();
            cm.setId(id);
            cm.setQuantity(1);
            HttpSession session = request.getSession();
            ArrayList<CartBean> cart_list = (ArrayList<CartBean>) session.getAttribute("cart-list");
            
            if (cart_list == null) {
                cartList.add(cm);
                session.setAttribute("cart-list", cartList);
                response.sendRedirect("index.jsp");
            } else {
                cartList = cart_list;
                boolean exist = false;
                for (CartBean c : cart_list) {
                    if (c.getId() == id) {
                        exist = true;
                        response.sendRedirect("exist.jsp");
                    } 
                }
                if (!exist) {
                    cartList.add(cm);
                    response.sendRedirect("index.jsp");
                }
            }
        }
    }
}
