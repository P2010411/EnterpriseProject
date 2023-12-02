<%@page import="java.util.List"%>
<%@page import="com.mycompany.carstore.DAO.*"%>
<%@page import="com.mycompany.carstore.Beans.*"%>
<%@page import="java.sql.Connection"%>
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
    <div class="container">
        <div class="card" style="margin-top: 20px;">
            <div class="card-header">All Products</div>
        </div>
        <div class="row">
            <div class="col-md-3 my-3">
                <div class="card w-100" style="width: 18rem;">
                    <img src="product-image/Chevrolet-Camaro-ZL1-2023.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                      <h5 class="card-title">Card title</h5>
                      <h6 class="price">Price: $50000</h6>
                      <h6 class="branch">Branch: branch</h6>
                      <div class="mt-3 d-flex justify-content-between">
                          <a href="#" class="btn btn-primary">Add  to Cart</a>
                          <a href="#" class="btn btn-primary">Buy Now</a>
                      </div>
                    </div>
                </div>
            </div>
        </div>
    </div> 
    <%@include file="HeaderFooter/footer.jsp" %>
  </body>
</html>
