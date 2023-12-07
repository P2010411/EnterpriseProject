package com.mycompany.carstore.Servlets;

import com.mycompany.carstore.Beans.CustomerBean;
import com.mycompany.carstore.DAO.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");

            CustomerDAO cdao = new CustomerDAO();
            CustomerBean customer = cdao.customerLogin(email, password);
            if (customer != null) {
                request.getSession().setAttribute("auth", customer);
		response.sendRedirect("index.jsp");
            } else {
		response.sendRedirect("error.jsp");
            }
        } 
    }
}
