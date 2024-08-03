<%@page import="project.ConnectionProvider"%>
<%@page import="java.sql.*"%>
<%@include file="header.jsp"%>
<%@include file="footer.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<style>
h3 {
	color: yellow;
	text-align: center;
}

.alert {
	color: red;
	text-align: center;
}

body {
	font-family: Arial, Helvetica, sans-serif;
	background-color: #f2f2f2;
}

table {
	border-collapse: collapse;
	width: 100%;
}

table th, table td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

table th {
	background-color: #4CAF50;
	color: white;
}

table tr:nth-child(even) {
	background-color: #f2f2f2;
}

table tr:hover {
	background-color: #ddd;
}
</style>
</head>
<body>
	<div style="text-align: center; font-size: 30px;">
		Home <i class='fas fa-comment-alt'></i>
	</div>
	<%
String msg=request.getParameter("msg");
if("added".equals(msg)){
%>
	<h3 class="alert">Product added successfully!</h3>
	<%} %>
	<%

if("exist".equals(msg)){
%>
	<h3 class="alert">Product already exists in your cart! Quantity increased!</h3>
	<%} %>
	<%

if("invalid".equals(msg)){
%>
	<h3 class="alert">Something went wrong, try again!</h3>
	<%} %>
	<table>
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Image</th>
				<th scope="col">Name</th>
				<th scope="col">Category</th>
				
				<th scope="col"><i class="fa fa-inr"></i> Price</th>
				<th scope="col">Add to cart <i class='fas fa-cart-plus'></i></th>
			</tr>
		</thead>
		<tbody>
			<%
try {
	ConnectionProvider conProvider = new ConnectionProvider();
	Connection con = conProvider.getCon();
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("SELECT * FROM product WHERE active='Yes'");
	while (rs.next()) {
		String id = rs.getString("id");
		String name = rs.getString("name");
		String category = rs.getString("category");
		String price = rs.getString("price");
%>
			<tr>
				<td><%= id %></td>
				<td>
					<img src="./DisplayImage?id=<%= id %>" width="100" height="100" alt="Product Image">
				</td>
				<td><%= name %></td>
				<td><%= category %></td>
				
				<td><i class="fa fa-inr"></i> <%= price %></td>
				<td>
					<a href="./addToCartAction?id=<%= id %>">Add to cart <i class='fas fa-cart-plus'></i></a>
				</td>
			</tr>
			<%
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
		</tbody>
	</table>
	<br>
	<br>
	<br>
</body>
</html>
