<%@ page import="project.ConnectionProvider" %>
<%@ page import="java.sql.*" %>
<%@ include file="adminHeader.jsp" %>
<%@ include file="./footer.jsp" %>
<html>
<head>
    <link rel="stylesheet" href="./css/addNewProduct-style.css">
    <title>Add New Product</title>
    <style>
        body {
            background-color: green;
        }
    </style>
</head>
<body>
    <%
        String msg = request.getParameter("msg");
        if ("done".equals(msg)) {
    %>
    <h3 class="alert">Product Added Successfully!</h3>
    <% } %>
    <%
        if ("wrong".equals(msg)) {
    %>
    <h3 class="alert">Some thing went wrong! Try Again!</h3>
    <% } %>
    <%
        int id = 1;
        try {
            ConnectionProvider conProvider = new ConnectionProvider();
            Connection con = conProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT MAX(id) FROM product");
            if (rs.next()) {
                id = rs.getInt(1) + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
    <form action="./addNewProductAction" method="post" enctype="multipart/form-data">
        <h3 style="color: yellow;">
            Product ID: <%= id %>
        </h3>
        <input type="hidden" name="id" value="<%= id %>">

        <div class="left-div">
            <h3>Enter Name</h3>
            <input class="input-style" type="text" name="name" placeholder="Enter Name" required>
            <hr>
        </div>

        <div class="right-div">
            <h3>Enter Category</h3>
            <input class="input-style" type="text" name="category" placeholder="Enter Category" required>
            <hr>
        </div>

        <div class="left-div">
            <h3>Enter Price</h3>
            <input class="input-style" type="number" name="price" placeholder="Enter Price" required>
            <hr>
        </div>

        <div class="left-div">
            <h3>Upload Image</h3>
            <input class="input-style" type="file" name="image" required>
            <hr>
        </div>

        <div class="right-div">
            <h3>Active</h3>
            <select class="input-style" name="active">
                <option value="Yes">Yes</option>
                <option value="No">No</option>
            </select>
            <hr>
        </div>

        <button class="button">
            Save <i class='far fa-arrow-alt-circle-right'></i>
        </button>
    </form>

    <%
        try {
            ConnectionProvider conProvider = new ConnectionProvider();
            Connection con = conProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM product WHERE active='Yes'");
            while (rs.next()) {
                String productId = rs.getString("id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                String price = rs.getString("price");
    %>
    <div>
        <h3>Product ID: <%= productId %></h3>
        <p>Name: <%= name %></p>
        <p>Category: <%= category %></p>
        <p>Price: <i class="fa fa-inr"></i> <%= price %></p>
        <img src="displayImage?id=<%= productId %>" width="100" height="100" alt="Product Image">
    </div>
    <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</body>
</html>
