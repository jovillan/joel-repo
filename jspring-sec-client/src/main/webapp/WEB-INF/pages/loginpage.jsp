<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@page session="true"%> --%>
<html>
<head>
	<title>Application Security Login Page</title>
	<style>
	.error {
		padding: 15px;
		margin-bottom: 20px;
		border: 1px solid transparent;
		border-radius: 4px;
		color: #a94442;
		background-color: #f2dede;
		border-color: #ebccd1;
	}
	
	.msg {
		padding: 15px;
		margin-bottom: 20px;
		border: 1px solid transparent;
		border-radius: 4px;
		color: #31708f;
		background-color: #d9edf7;
		border-color: #bce8f1;
	}
	
	#login-box {
		width: 300px;
		padding: 20px;
		margin: 100px auto;
		background: #fff;
		-webkit-border-radius: 2px;
		-moz-border-radius: 2px;
		border: 1px solid #000;
	}
	</style>
</head>
<body onload='document.loginForm.username.focus();'>

	<h2>Login Page</h2>

	<div id="login-box">

		<h3>Login with Username and Password</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form  name='loginForm' action="/jspring-sec-client/client/auth" method="post">
			<label for="username">Username: </label><input name="username" type="text" /><br />
			<label for="password">Password: </label><input name="password" type="password" /><br />
			<!-- add hidden csrf parameters -->
			<input type="submit" value="Submit" />
		</form>
	</div>

</body>
</html>
