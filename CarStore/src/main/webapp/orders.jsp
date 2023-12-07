<%@page import="com.mycompany.carstore.DAO.*"%>
<%@page import="java.util.*"%>
<%@page import="com.mycompany.carstore.Beans.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
CustomerBean auth = (CustomerBean) request.getSession().getAttribute("auth");
List<OrderBean> orders = null;
if (auth != null) {
    request.setAttribute("auth", auth);
    OrderDAO orderDao  = new OrderDAO();
    orders = orderDao.userOrders(auth.getId());
} else {
    response.sendRedirect("login.jsp");
}

ArrayList<CartBean> cart_list = (ArrayList<CartBean>) session.getAttribute("cart-list");
if (cart_list != null) {
    request.setAttribute("cart_list", cart_list);
}
%>
<html lang="en">
  <head>
    <title>Order Page</title>
    <%@include file="HeaderFooter/header.jsp" %>
  </head>
  <body>
    <%@include file="HeaderFooter/navbar.jsp" %>
    <div class="container">
	<div class="card-header my-3" style="font-size: 40px; text-align: center">All Orders</div>
	<table class="table table-light">
            <thead>
                <tr>
                    <th scope="col">Date</th>
                    <th scope="col">Model Name</th>
                    <th scope="col">Branch</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Price</th>
                    <th scope="col">Cancel Order</th>
                </tr>
            </thead>
            <tbody>		
            <%
            if(orders != null){
                for(OrderBean o:orders){
            %>
            <tr>
                <td><%=o.getDate() %></td>
                <td><%=o.getModelName() %></td>
                <td><%=o.getBranch() %></td>
                <td><%=o.getQunatity() %></td>
                <td>$<%=o.getPrice() %></td>
                <td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%=o.getOrderId()%>">Cancel Order</a></td>
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