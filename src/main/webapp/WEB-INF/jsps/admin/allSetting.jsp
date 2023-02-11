<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chatty Admin-Settings</title>
<style>
<%@include file="/WEB-INF/css/allSettingStyle.css"%>
</style>
</head>
<body>

<%@include file="/WEB-INF/jsps/header.jsp" %>

<div style="display: flex; justify-content: center;">
<h1>ADMIN SETTINGS</h1>
</div>

<div class="gridContainer">


		
		<div class="editingProfile">
		<h3>Editing Profile</h3>
		<form action="/editProfile/${currentUser.id}" method="post">
		<div>
						<label> NickName: </label>
						<input type="text" name= "nickName" value="${currentUser.nickName}">
		</div><div>
						<label> Real Name: </label>
						<input type="text" name="name" value="${currentUser.user.name}">
		</div><div>
						<label> Email Address: </label>
						<input type="text" name= "emailAdress" value="${currentUser.user.emailAdress}">
		</div><div>
				<input type="submit" value="edit Profile">
		
		</div>
		</form><br>
		<form action="/changePassword/${currentUser.id}" method="post">
		<div>		
						<label> Old Password: </label>
						<input type="password" name = "oldPassword" placeholder="password">
		</div><div>
						<label> New Password: </label>
						<input type="password" name = "newPassword"  placeholder="new password">
		</div><div>
						<label> Confirm New Password: </label>
						<input type="password" name = "confirmNewPassword"  placeholder="new password">
		</div><div>
				<input type="submit" value="change Password">
		</div>
		
		</form>
		
		</div>

		<div class="friendList">
			<h3>Friend List</h3>
			<c:forEach items="${currentUser.friendList}" var="friendUser">
				<div class="displayFriend">
					<form action="/deletFriend/${friendUser.id}" method="post">
						<label> ${friendUser.nickName}</label>
						<input type="submit" value="delete">
					</form>	
				</div>
			</c:forEach>
		</div>
		
		<div class="blockedUser">
			<h3>Blocked User</h3>
			<form action="/blockUser" method="post">
				<input type="text" name="nickName" placeholder="nickName">
				<input type="submit" value="Block User">
			</form>
			<c:forEach items="${currentUser.blockedUsers}" var="blockedUser">
				<div class="displayBlockedUser">
					<form action="/unBlock/${blockedUser.id}" method="post">
						<label> ${blockedUser.nickName}</label>
						<input type="submit" value="unBlock">
					</form>	
				</div>
			</c:forEach>
		</div>

		<div class="bannedUser">
			<h3>Banned User</h3>
			<form action="/banUser" method="post">
				<input type="text" name="nickName" placeholder="nickName">
				<input type="submit" value="Ban User">
			</form>
			<br>
			<c:forEach items="${bannedUsers}" var="bannedUserVar">
				<div class="displayBannedUser">
					<form action="/unBan/${bannedUserVar.id}" method="post">
						<label> ${bannedUserVar.bannedUser.nickName}</label>
						<input type="submit" value="unBan">
					</form>	
				</div>
			</c:forEach>
		</div>

</div>



</body>
</html>