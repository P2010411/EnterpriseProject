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
        <title>Add New Product</title>
        <%@include file="HeaderFooter/header.jsp" %>
    </head>
    <body>
        <%@include file="HeaderFooter/navbar.jsp" %>
        <div class="container">
            <div class="card w-50 mx-auto my-5">
                <div class="card-header text-center">Create a New Product</div>
                    <div class="card-body">
                        <form action="add-product" method="post">
                            <div class="mb-3">
                                <label class="form-label">Model Name:</label> 
                                <input type="text" name="model-name" class="form-control" placeholder="Enter Model Name" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Year:</label> 
                                <input type="text" name="year" class="form-control" placeholder="Enter Year" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Options Package:</label>
                                <select class="form-select" name="options-package" required aria-label="select example">
                                    <option value="Standard">Standard</option>
                                    <option value="Premium">Premium</option>
                                    <option value="Optional">Optional</option>
                                </select>
                                <div class="invalid-feedback">Example invalid select feedback</div>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Color:</label> 
                                <input type="text" name="color" class="form-control" placeholder="Enter Color" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Motor Size:</label> 
                                <input type="text" name="motor-size" class="form-control" placeholder="Enter Motor Size" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Price:</label> 
                                <input type="text" name="price" class="form-control" placeholder="Enter Price" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">0-60km Acceleration Time:</label> 
                                <input type="text" name="acceleration" class="form-control" placeholder="Enter 0-60km Acceleration Time" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Fuel Type:</label> 
                                <input type="text" name="fuel-type" class="form-control" placeholder="Enter Fuel Type" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Branch:</label> 
                                <input type="text" name="branch" class="form-control" placeholder="Enter Branch" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Image:</label>
                                <input type="file" name="image" class="form-control" aria-label="file example" required>
                                <div class="invalid-feedback">Example invalid form file feedback</div>
                            </div>
                            <div class="text-center">
                                <button type="submit" class="btn btn-primary">Add The Product</button>
                            </div>
                        </form>
                    </div>
            </div>
        </div>
        <%@include file="HeaderFooter/footer.jsp" %>
    </body>
</html>
