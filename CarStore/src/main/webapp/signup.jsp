<%@page import="java.util.*"%>
<%@page import="com.mycompany.carstore.Beans.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
CustomerBean auth = (CustomerBean) request.getSession().getAttribute("auth");
if (auth != null) {
    response.sendRedirect("index.jsp");
}
ArrayList<CartBean> cart_list = (ArrayList<CartBean>) session.getAttribute("cart-list");
if (cart_list != null) {
    request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
    <%@include file="HeaderFooter/header.jsp" %>
</head>
<body>
    <%@include file="HeaderFooter/navbar.jsp" %>
    <div class="container">
        <div class="card w-50 mx-auto my-5">
            <div class="card-header text-center">Sign Up</div>
		<div class="card-body">
                    <form action="signup" method="post">
                        <div class="mb-3">
                            <label class="form-label">Username:</label> 
                            <input type="text" name="signup-name" class="form-control" placeholder="Enter Name" required>
			</div>
			<div class="mb-3">
                            <label class="form-label">Email:</label> 
                            <input type="email" name="signup-email" class="form-control" placeholder="Enter Email" required>
			</div>
			<div class="mb-3">
                            <label class="form-label">Password:</label> 
                            <input type="password" name="signup-password" class="form-control" placeholder="Password" required>
			</div>
                        <div class="mb-3">
                            <label class="form-label">Address:</label> 
                            <input type="text" name="signup-address" class="form-control" placeholder="Enter Address" required>
			</div>
			<div class="text-center">
                            <button type="submit" class="btn btn-primary">Sign Up</button>
                        </div>
                    </form>
		</div>
        </div>
    </div>
    <%@include file="HeaderFooter/footer.jsp" %>
</body>
</html>
