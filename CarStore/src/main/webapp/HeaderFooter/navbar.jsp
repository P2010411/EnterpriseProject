<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Car Store</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="cart.jsp">
                        Cart
                        <span class="badge bg-danger top-0 start-100 translate-middle px-1" style="margin-left: 5px; margin-right: -15px">${cart_list.size()}</span>
                    </a>
                </li>
                <%
		if (auth != null) {
		%>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="orders.jsp">Order</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="log-out">Logout</a>
                </li>
                <%
		} else {
		%>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="login.jsp">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="signup.jsp">Sign Up</a>
                </li>
                <%
		}
		%>
            </ul>
        </div>
    </div>
</nav>