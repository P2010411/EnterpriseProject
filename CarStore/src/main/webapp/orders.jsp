<%@page import="com.mycompany.carstore.Beans.*"%>
<%@page import="com.mycompany.carstore.Connection.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
CustomerBean auth = (CustomerBean) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("auth", auth);
}
%>
<html lang="en">
  <head>
    <title>Home Page</title>
    <%@include file="HeaderFooter/header.jsp" %>
  </head>
  <body>
    <%@include file="HeaderFooter/navbar.jsp" %>
    <h1>Hello, world!</h1>
    <%@include file="HeaderFooter/footer.jsp" %>
  </body>
</html>