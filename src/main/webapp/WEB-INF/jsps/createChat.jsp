<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chatty create GroupChat</title>
<style>
<%@include file="/WEB-INF/css/createChatStyle.css"%>
</style>
</head>
<body>
<%@include file="/WEB-INF/jsps/header.jsp"%>

<div style="display: flex; justify-content: center;">
<h1>CREATE GROUP CHAT</h1>
</div>

	<div class="body-grid">
		<div class="friendList">
			<h3>Friend List</h3>
			<form action="/createChat" method="post">
			<input type="text" name="chatName" placeholder="groupName">
				<c:forEach items="${currentUser.friendList}" var="friendUser">
					<div class="displayFriend">
						<label>${friendUser.nickName}</label>
						<input type="checkbox" name="params" value="${friendUser.nickName}">
					</div>
				</c:forEach>
				<input type="submit" value="create Chat">
			</form>	
		</div>
	</div>	






</body>
</html>