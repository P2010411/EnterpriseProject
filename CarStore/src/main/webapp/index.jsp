<%@page import="java.util.*"%>
<%@page import="com.mycompany.carstore.DAO.*"%>
<%@page import="com.mycompany.carstore.Beans.*"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
CustomerBean auth = (CustomerBean) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("auth", auth);
}

ProductDAO pd = new ProductDAO();
List<ProductBean> products = pd.selectAllProducts();

ArrayList<CartBean> cart_list = (ArrayList<CartBean>) session.getAttribute("cart-list");
if (cart_list != null) {
    request.setAttribute("cart_list", cart_list);
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
        <div class="card text-center" style="margin-top: 20px;">
            <div class="card-header text-center">
                <h1>All Products</h1>
                <a href="#" class="btn btn-primary">Add New Product</a>
            </div>
        </div>
        <div class="row">
            <%
                if (!products.isEmpty()){
                    for(ProductBean p:products){
            %>
                        <div class="col-md-3 my-3">
                            <div class="card" style="width: 18rem;">
                                <img src="product-image/<%= p.getImage() %>" class="card-img-top" alt="...">
                                <div class="card-body">
                                  <h5 class="card-title"><%= p.getModelName() %></h5>
                                  <h6 class="price">Price: $<%= p.getPrice() %></h6>
                                  <h6 class="year">Year: <%= p.getYear() %></h6>
                                  <h6 class="color">Color: <%= p.getColor() %></h6>
                                  <h6 class="options_package">Options Package: <%= p.getOptionsPackage() %></h6>
                                  <h6 class="motor_size">Motor Size: <%= p.getMotorSize() %></h6>
                                  <h6 class="acceleration">0-60km Acceleration Time: <%= p.getAcceleration() %>s</h6>
                                  <h6 class="fuel_type">Fuel Type: <%= p.getFuelType() %></h6>
                                  <h6 class="branch">Branch: <%= p.getBranch() %></h6>
                                  <div class="mt-3 d-flex justify-content-between">
                                      <a href="add-to-cart?id=<%= p.getId() %>" class="btn btn-primary">Add  to Cart</a>
                                      <a href="order-now?quantity=1&id=<%=p.getId()%>" class="btn btn-dark">Buy Now</a>
                                  </div>
                                </div>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">
                                        <div class="d-grid gap-2">
                                            <a href="#" class="btn btn-outline-success">Edit</a>
                                            <a href="#" class="btn btn-outline-danger">Delete</a>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
            <%}
                }
            %>
            
        </div>
    </div> 
    <%@include file="HeaderFooter/footer.jsp" %>
  </body>
</html>
