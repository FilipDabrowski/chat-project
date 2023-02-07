<%@ page language="java" contentType="text/html; charset=ISO-8859-15"
	pageEncoding="ISO-8859-15"%>

<!DOCTYPE html>
<html>
<head>
<meta charset=ISO-8859-15>
<title>Login</title> 
<style>
body {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

form {
	background-color: #f2f2f2;
	padding: 40px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px #ccc;
	text-align: center;
}

h1 {
	margin-bottom: 20px;
	font-size: 36px;
	text-align: center;
}

label {
	display: block;
	margin-bottom: 10px;
	font-size: 20px;
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 10px;
	margin-bottom: 20px;
	font-size: 18px;
	border-radius: 5px;
	border: none;
	box-sizing: border-box;
}

button[type="submit"] {
	width: 100%;
	padding: 10px;
	background-color: #4CAF50;;
	color: #fff;
	font-size: 22px;
	border-radius: 5px;
	border: none;
	cursor: pointer;
	margin-top: 20px;
}

button[type="submit"]:hover {
	background-color: #444;
}

div.error {
	margin-top: 20px;
}

div.error p {
	font-size: 18px;
	text-align: center;
}

section {
	margin-top: 20px;
	font-size: 22px;
	text-align: center;
}
</style>
</head>
<main>
	<form action="/login" method="post">
		<label for="username">Username:</label> <input type="text"
			name="username" placeholder="Username"> <br> <label
			for="password">Password:</label> <input type="password"
			name="password" placeholder="Password"> <br>
		<button type="submit">Log in</button>
		<div class="error">
			<p th:if="${error}" th:text="${error}"></p>
		</div>
	</form>
	<section>
		<a href="/forgotPassword">Forgot Password</a> <br> <br> 
			<div class="container signin">
	<p>Don't have an account yet?</p>
	<a href="/register">Register here</a>
	</section>
</main>
</body>
</html>

