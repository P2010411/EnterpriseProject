<%@page import="java.math.BigDecimal"%>
<%@page import="com.mycompany.carstore.DAO.*"%>
<%@page import="java.util.*"%>
<%@page import="com.mycompany.carstore.Beans.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
CustomerBean auth = (CustomerBean) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("auth", auth);
}

ArrayList<CartBean> cart_list = (ArrayList<CartBean>) session.getAttribute("cart-list");
List<CartBean> cartProduct = null;
if (cart_list != null) {
    ProductDAO pDao = new ProductDAO();
    cartProduct = pDao.getCartProducts(cart_list);
    BigDecimal total = pDao.getTotalCartPrice(cart_list);
    request.setAttribute("cart_list", cart_list);
    request.setAttribute("total", total);
}

%>
<html lang="en">
  <head>
    <title>Cart Page</title>
    <%@include file="HeaderFooter/header.jsp" %>
    <style type="text/css">
        .table tbody td{
            vertical-align: middle;
        }
    </style>
  </head>
  <body>
    <%@include file="HeaderFooter/navbar.jsp" %>
    <div class="container my-3">
        <div class="d-flex py-3">
            <h3>Total Price: $${ (cart_list.size() > 0) ? total : 0 } </h3> 
            <a class="mx-3 btn btn-primary" href="cart-check-out">Check Out</a></div>
        <table class="table table-light">
            <thead>
                <tr>
                    <th scope="col">Model Name</th>
                    <th scope="col">Branch</th>
                    <th scope="col">Price</th>
                    <th scope="col">Color</th>
                    <th scope="col">Options Package</th>
                    <th scope="col">Buy Now</th>
                    <th scope="col">Cancel</th>
                </tr>
            </thead>
            <tbody>
            <% 
            if (cart_list != null) {
                for (CartBean c : cartProduct) {
            %>
                <tr>
                    <td><%= c.getModelName()%></td>
                    <td><%= c.getBranch()%></td>
                    <td>$<%= c.getPrice()%></td>
                    <td><%= c.getColor()%></td>
                    <td><%= c.getOptionsPackage()%></td>
                    <td>
                        <form action="order-now" method="post" class="form-inline" style="margin-bottom: 0px">
                            <input type="hidden" name="id" value="<%= c.getId() %>" class="form-input">
                            <div class="form-group d-flex justify-content-between" style="width: 250px">
                                <a class="btn btn-outline-secondary" href="quantity-inc-dec?action=inc&id=<%=c.getId()%>">+</a>
                                <input type="text" name="quantity" class="form-control" value="<%=c.getQuantity()%>" readonly>
                                <a class="btn btn-outline-secondary" href="quantity-inc-dec?action=dec&id=<%=c.getId()%>">-</a>
                                <button type="submit" class="btn btn-dark" style="margin-left: 20px">Buy</button>
                            </div>
                        </form>
                    </td>
                    <td><a href="remove-from-cart?id=<%=c.getId() %>" class="btn btn-sm btn-danger">Remove</a></td>
                </tr>  
            <%
                }
            }
            %>
                
            </tbody>
        </table>
    </div>
    <%@include file="HeaderFooter/footer.jsp" %>
  </body>
</html>