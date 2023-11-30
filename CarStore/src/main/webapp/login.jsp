<%-- 
    Document   : login
    Created on : 2023年11月30日, 下午1:18:53
    Author     : 23956
--%>

<%@page import="com.mycompany.carstore.Beans.*"%>
<%@page import="com.mycompany.carstore.Connection.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
CustomerBean auth = (CustomerBean) request.getSession().getAttribute("auth");
if (auth != null) {
    response.sendRedirect("index.jsp");
}
%>
<html lang="en">
  <head>
    <title>Login Page</title>
    <%@include file="HeaderFooter/header.jsp" %>
  </head>
  <body>
    <%@include file="HeaderFooter/navbar.jsp" %>
    <div class="container">
        <div class="card w-50 mx-auto my-5">
            <div class="card-header text-center">User Login</div>
		<div class="card-body">
                    <form action="login" method="post">
			<div class="mb-3">
                            <label class="form-label">Email address</label> 
                            <input type="email" name="login-email" class="form-control" placeholder="Enter email" required>
			</div>
			<div class="mb-3">
                            <label class="form-label">Password</label> 
                            <input type="password" name="login-password" class="form-control" placeholder="Password" required>
			</div>
			<div class="text-center">
                            <button type="submit" class="btn btn-primary">Login</button>
                        </div>
                    </form>
		</div>
        </div>
    </div>
    <%@include file="HeaderFooter/footer.jsp" %>
  </body>
</html>
