<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<style>
body {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

form {
	background-color: #f2f2f2;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px #ccc;
	text-align: center;
}

h3 {
	margin-bottom: 10px;
	font-size: 30px;
	text-align: center;
}

label {
	display: block;
	margin-bottom: 10px;
	font-size: 20px;
}

input[type="text"], input[type="password"] {
	padding: 5px;
	margin-bottom: 20px;
	width: 100%;
	font-size: 18px;
	text-align: center;
}

.address-container {
	display: block;
	width: 300px;
	margin-bottom: 20px;
}

.address-container input[type="text"] {
	width: 120px;
}

input[type="submit"] {
	padding: 10px 20px;
	background-color: #4CAF50;
	color: #fff;
	font-size: 18px;
	cursor: pointer;
	width: 100%;
	border-radius: 5px;
}
</style>
</head>
<body>
		<div class="container">

			<h3>Register</h3>
			<p>Please fill in this form to create an account.</p>

			<div class="form-container">
				<form action="/register" method="post">
					<div>
						<label for="nickName">NickName:</label> <input type="text"
							name="nickName" placeholder="nick name">
					</div>
										<div>
						<label for="emailAdress">Email:</label> <input type="text"
							name="emailAdress" placeholder="Email">
					</div>
					<div>
						<label for="password">Password:</label> <input type="password"
							name="password" placeholder="Password">
					</div>
					<div>
						<label for="confirm-password">Confirm Password:</label> <input type="password"
							name="confirmPassword" placeholder="Password">
					</div>
					<div>
						<label for="Name">Name and Surname:</label> <input type="text"
							name="Name" placeholder="Name and Surname">
					</div>

					<div>
						<input type="submit" value="Submit">
					</div>
					<div class="container signin">
						<p>
							Already have an account? <a href="/login">login here</a>
						</p>
					</div>
				</form>
			</div>
		</div>
</body>
</html>


