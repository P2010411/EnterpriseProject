<%@page import="java.sql.Connection"%>
<%@page import="com.mycompany.carstore.Connection.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
  <head>
    <title>Home Page</title>
    <%@include file="HeaderFooter/header.jsp" %>
  </head>
  <body>
    <%@include file="HeaderFooter/navbar.jsp" %>
    
    <% Connection connection = DBConnection.getConnection();
       out.print(connection); %>
    
    <%@include file="HeaderFooter/footer.jsp" %>
  </body>
</html>
