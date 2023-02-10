<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Chatty-Chat</title>

<style>
<%@include file="/WEB-INF/css/indexChatStyle.css" %>
</style>
</head>

<body>

<%@include file="header.jsp"%>

<div style="display: flex; justify-content: center;">
	<h1>CHATS</h1>
</div>
	<div style="display: flex; justify-content: center;">
		<c:choose>

			<c:when test="${not empty currentUser}">
				<h2>Hello ${currentUser.nickName}</h2>
			</c:when>
			<c:otherwise>
				<h2 style="color: red;">You are currently logged out. Please log in to continue.</h2>
			</c:otherwise>
		</c:choose>
	</div>

	<div class="body-grid">

		<div class="friendList">
			<h3>Friend List</h3>
			<c:if test="${not empty friendMessage}">Friend request to ${friendMessage} was send</c:if>
			<form action="/addNewFriend" method="post">
				<input type="text" name="nickName" placeholder="nickName">
				<input type="hidden" name="currentChatID" value="${selectedChat.id}">
				<input type="submit" value="add new Friend">
			</form>
			<c:forEach items="${currentUser.friendList}" var="friendUser">
				<div class="displayFriend">
					<form action="/goToChatWith/${friendUser.id}" method="post">
						<input type="hidden" name="currentUserID" value="${currentUser.id}">
						<input type="submit" value="${friendUser.nickName}">
					</form>	
				</div>
			</c:forEach>
		</div>

		<div class="chat">
			<h3> chat with ${selectedChat.chatName}</h3>
			<div class="chatWindow">
				<div class=chatMessage>
					<c:forEach var="message" items="${chatMessages}">
						<c:choose>
							<c:when test="${message.sender.nickName eq currentUser.nickName}">
								<div class = outgoingMessage>
									<div>${message.timeString}   ${message.sender.nickName}</div>
									<div>${message.message}</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class = incommingMessage>
									<div>${message.timeString}    ${message.sender.nickName}</div>
									<div>${message.message}</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>	
				<div class = "sendingMessage">
					<form method="post" action="/sendMessage">
					<input class="text-input" type="text" name="message" />
					
					<input type="hidden" name="currentChatID" value="${selectedChat.id}">
				
					<input class="send-button" type="submit" value="send" />
					</form>				
				</div>
			</div>
		</div>

		<div class="groupChat">
			<h3>My Chats</h3>
				<form action="/createNewGroupChat" method="post">
				<input type="submit" value="create GroupChat">
			</form>
			<c:forEach items="${currentUser.chats}" var="groupChat">
				<div class="displayGroupChat">
					<form action="/goToGroupChat/${groupChat.id}" method="post">
						<input type="hidden" name="currentUserID" value="${currentUser.id}">
						<input type="submit" value="${groupChat.chatName}">
					</form>	
				</div>
			</c:forEach>
		</div>
	</div>




</body>
</html>