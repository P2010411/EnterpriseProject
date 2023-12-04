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

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("signup-name");
            String email = request.getParameter("signup-email");
            String password = request.getParameter("signup-password");
            String address = request.getParameter("signup-address");

            CustomerBean customer = new CustomerBean(name, email, password, address);

            CustomerDAO cdao = new CustomerDAO();
            try {
                cdao.customerSignUp(customer);
                request.getSession().setAttribute("auth", customer);
                response.sendRedirect("index.jsp");
            } catch (Exception e) {
                out.println("Registration failed. Please try again.");
            }
        } 
    }
}
