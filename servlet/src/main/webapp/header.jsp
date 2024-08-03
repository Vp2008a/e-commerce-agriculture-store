<%@page errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="dsfsdv.png" type="image/png" sizes="20x20">
<link rel="stylesheet" href="css/home-style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>

</head>
<!--Header-->
<br>
<div class="topnav sticky">
	<%String email=session.getAttribute("email").toString(); %>
	<center>
		<h2>E-Khedut website</h2>
	</center>
	<h2>
		<a href=""> <%out.println(email); %> <i class='fas fa-user-alt'></i>
		</a>
	</h2>
	<a href="home.jsp">Home<i class='fas fa-comment-alt'></i></a> <a
		href="myCart.jsp">My Cart<i class='fas fa-comment-alt'></i></a> <a
		href="myOrders.jsp">My Orders <i class='fab fa-elementor'></i></a> <a
		href="changeDetails.jsp">Change Details <i
		class='fas fa-comment-alt'></i></a> <a href="messageUs.jsp">Message Us
		<i class='fas fa-comment-alt'></i>
	</a> <a href="about.jsp">About <i class='fas fa-comment-alt'></i></a> <a
		href="logout.jsp">Logout <i class='fas fa-comment-alt'></i></a>

</div>
<br>
<!--table-->