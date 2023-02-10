<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chatty-Settings</title>
<style>
<%@include file="/WEB-INF/css/profileSettingStyle.css" %>
</style>
</head>
<body>

<%@include file="/WEB-INF/jsps/header.jsp" %>

<div style="display: flex; justify-content: center;">
<h1>SETTINGS</h1>
</div>
<div class="gridContainer">


		
		<div class="editingProfile">
		<h3>Editing Profile</h3>
		<form action="/editingProfil/${currentUser.id}" method="post">
		<div>
						<label> NickName: </label>
						<input type="text" value="${currentUser.nickName}">
		</div><div>
						<label> Real Name: </label>
						<input type="text" value="${currentUser.user.nickName}">
		</div><div>
						<label> Email Address: </label>
						<input type="text" value="${currentUser.user.emailAdress}">
		</div><div>
				<input type="submit" value="edit Profile">
		
		</div>
		</form><br>
		<form action="/changePassword/${currentUser.id}" method="post">
		<div>		
						<label> Old Password: </label>
						<input type="password" name="oldPassword"  placeholder="password">
		</div><div>
						<label> New Password: </label>
						<input type="password" name="newPassword" placeholder="new password">
		</div><div>
						<label> Confirm New Password: </label>
						<input type="password" name="confirmNewPassword" placeholder="new password">
		</div><div>
				<input type="submit" value="change Password">
		</div>
		
		</form>
		
		</div>
		<%-- Show error message if there is one --%>
<c:if test="${not empty error}">
  <div class="error-message">
    <h3><font color="red">${error}</font></h3>
  </div>
</c:if>

<%-- Show success message if there is one --%>
<c:if test="${not empty success}">
  <div class="success-message">
    <h3><font color="green">${success}</font></h3>
  </div>
</c:if>
		


		<div class="friendList">
			<h3>Friend List</h3>
			<c:forEach items="${currentUser.friendList}" var="friendUser">
				<div class="displayFriend">
					<form action="/deletFriend/${friendUser.id}" method="post">
						<label> ${friendUser.nickName}</label>
						<input type="button" value="delete">
					</form>	
				</div>
			</c:forEach>
		</div>
		
		<div class="blockedUser">
			<h3>Blocked User</h3>
			<c:forEach items="${currentUser.blockedUsers}" var="blockedUser">
				<div class="displayBlockedUser">
					<form action="/unBlock/${blockedUser.id}" method="post">
						<label> ${blockedUser.nickName}</label>
						<input type="button" value="unBlock">
					</form>	
				</div>
			</c:forEach>
		</div>

</div>





</body>
</html>