<%@page import="com.mycompany.carstore.DAO.ProductDAO"%>
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

int id = Integer.parseInt(request.getParameter("id"));
ProductDAO productDAO = new ProductDAO();
ProductBean product = productDAO.getSingleProduct(id);
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit the Product</title>
        <%@include file="HeaderFooter/header.jsp" %>
    </head>
    <body>
        <%@include file="HeaderFooter/navbar.jsp" %>
        <div class="container">
            <div class="card w-50 mx-auto my-5">
                <div class="card-header text-center">Edit The Product</div>
                    <div class="card-body">
                        <form action="edit-product" method="post">
                            <input type="hidden" name="id" value="<%= product.getId() %>" class="form-input">
                            <div class="mb-3">
                                <label class="form-label">Model Name:</label> 
                                <input type="text" name="model-name" class="form-control" value="<%= product.getModelName() %>" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Year:</label> 
                                <input type="text" name="year" class="form-control" value="<%= product.getYear() %>" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Options Package:</label>
                                <select class="form-select" name="options-package" required aria-label="select example">
                                    <option value="Standard" <%= product.getOptionsPackage().equals("Standard") ? "selected" : "" %>>Standard</option>
                                    <option value="Premium" <%= product.getOptionsPackage().equals("Premium") ? "selected" : "" %>>Premium</option>
                                    <option value="Optional" <%= product.getOptionsPackage().equals("Optional") ? "selected" : "" %>>Optional</option>
                                </select>
                                <div class="invalid-feedback">Example invalid select feedback</div>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Color:</label> 
                                <input type="text" name="color" class="form-control" value="<%= product.getColor() %>" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Motor Size:</label> 
                                <input type="text" name="motor-size" class="form-control" value="<%= product.getMotorSize() %>" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Price:</label> 
                                <input type="text" name="price" class="form-control" value="<%= product.getPrice() %>" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">0-60km Acceleration Time:</label> 
                                <input type="text" name="acceleration" class="form-control" value="<%= product.getAcceleration() %>" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Fuel Type:</label> 
                                <input type="text" name="fuel-type" class="form-control" value="<%= product.getFuelType() %>" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Branch:</label> 
                                <input type="text" name="branch" class="form-control" value="<%= product.getBranch() %>" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Image:</label>
                                <input type="file" name="image" class="form-control" value="<%= product.getImage() %> aria-label="file example" required>
                                <div class="invalid-feedback">Example invalid form file feedback</div>
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </div>
                        </form>
                    </div>
            </div>
        </div>
        <%@include file="HeaderFooter/footer.jsp" %>
    </body>
</html>