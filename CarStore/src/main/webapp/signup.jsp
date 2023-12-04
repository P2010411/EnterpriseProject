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
    <h1>Sign Up</h1>
    <form action="signup" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>
        
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required><br><br>
        
        <input type="submit" value="Sign Up">
    </form>
    <%@include file="HeaderFooter/footer.jsp" %>
</body>
</html>
