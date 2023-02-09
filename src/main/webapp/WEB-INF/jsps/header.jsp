<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chatty</title>
<style>
<%@include file="/WEB-INF/css/headerStyle.css"%>
</style>
</head>
<body>

	<div class="header-main">
		<div>
			<img class="head-img" alt="" src='<c:url value="resources/logo.jpg"/>'>
		</div>
		<div>
			<h1 class="header-heading">|-|_Chatty_|-|</h1>
		</div>
	</div>

	<div class="header-forms">
				<div>
					<form action="/indexChat" method="get">
						<input class="header-button" type="submit" value="home">
					</form>
				</div>
		<c:choose>
			<c:when test="${not empty currentUser}">
				<div>
					<form action="/settings" method="post">
						<input class="header-button" type="submit" value="settings">
					</form>
				</div>
				<div>
					<form action="/logout" method="post">
						<input class="header-button" type="submit" value="logout">
					</form>
				</div>

						</c:when>	
			<c:otherwise>

								<div>
					<form action="/login" method="get">
						<input class="header-button" type="submit" value="login">

					</form>
				</div>


			<div>
				<form action="/register" method="get">
					<input class="header-button" type="submit" value="register">
				</form>
			</div>
			</c:otherwise>
					</c:choose>
	</div>




</body>
</html>