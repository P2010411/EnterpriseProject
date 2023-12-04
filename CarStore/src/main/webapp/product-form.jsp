<%@page import="com.mycompany.carstore.Beans.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
CustomerBean auth = (CustomerBean) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("auth", auth);
}
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Product From Page</title>
        <%@include file="HeaderFooter/header.jsp" %>
    </head>
    <body>
        <%@include file="HeaderFooter/navbar.jsp" %>
        <h1>Product Form</h1>
        <%@include file="HeaderFooter/footer.jsp" %>
    </body>
</html>
