<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="dsfsdv.png" type="image/png" sizes="20x20">
<title>Login</title>
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

.whysignLogin {
	text-align: center;
	margin-left: 20px;
}
</style>
</head>
<body>
	<div id='container'>

		<div class='signup'>
			<h2>E-Khedut website</h2>
			<form action="./loginAction" method="post">
				<input type="email" name="email" placeholder="Enter Email" required>
				<input type="password" name="password" placeholder="Enter Password"
					required> <input type="submit" value="Login">
			</form>
			<h2>
				<a href="signup.jsp">SignUp</a>
			</h2>
			<h2>
				<a href="forgotPassword.jsp">Forgot Password?</a>
			</h2>
		</div>
		<div class='whysignLogin'>
			<% String msg=request.getParameter("msg"); if("notexist".equals(msg)){ %>
			<h1>Incorrect Username or Password</h1>
			<% } %>
			<% if("invalid".equals(msg)){ %>
			<h1>Something Went Wrong! Try Again !</h1>
			<% } %>

		</div>
	</div>
</body>
</html>
