<%@page import="java.sql.Connection"%>
<%@page import="com.mycompany.carstoretest.Connection.DBCon"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <title>Home Page</title>
    <%@include file="includes/head.jsp" %>
  </head>
  <body>
    <%@include file="includes/navbar.jsp" %>
    <% out.print(DBCon.getConnection()); %>
    <%@include file="includes/foot.jsp" %>
  </body>
</html>
