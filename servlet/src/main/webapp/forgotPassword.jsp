<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/signup-style.css">
<link rel="icon" href="dsfsdv.png" type="image/png" sizes="20x20">
<title>ForgotPassword</title>
<style>
body {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
}

#container {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	height: 100vh;
}

.signup {
	text-align: center;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	padding: 20px;
	margin-top: 20px;
	max-width: 300px;
}

.signup input, .signup select {
	width: 100%;
	margin-bottom: 10px;
	padding: 10px;
	box-sizing: border-box;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.signup input[type="submit"] {
	background-color: #4CAF50;
	color: white;
	border: none;
	cursor: pointer;
}

.whysign {
	text-align: center;
	margin-top: 20px;
}
</style>
</head>
<body>
	<div id='container'>
		<div class='signup'>
			<form action="./forgotPasswordAction" method="post">
				<input type="email" name="email" placeholder="Enter Email" required>
				<input type="number" name="mobileNumber"
					placeholder="Enter Mobile Number" required> <select
					name="securityQuestion">
					<option value="What was your first car?">What was your
						first car?</option>
					<option value="What is the name of your first pet?">What
						is the name of your first pet?</option>
					<option value="What elementary school did you attended?">What
						elementary school did you attended?</option>
					<option value="What is the name of the town where you where born?">What
						is the name of the town where you where born?</option>

				</select> <input type="text" name="answer" placeholder="Enter Answer"
					required> <input type="password" name="newPassword"
					placeholder="Enter your new password to set" required> <input
					type="submit" value="Save">
			</form>
			<h2>
				<a href="login.jsp">Login</a>
			</h2>
		</div>
		<div>
			<% 
   String msg=request.getParameter("msg");
   if("done".equals(msg)){
   %>
			<h1>Password Changed Successfully!</h1>
			<%} %>
			<%
if("invalid".equals(msg)){
%>
			<h1>Some thing Went Wrong! Try Again !</h1>
			<%} %>
		</div>
	</div>
</body>
</html>