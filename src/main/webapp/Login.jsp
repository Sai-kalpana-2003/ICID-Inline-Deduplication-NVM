<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Form</title>
<style>
body {
	margin: 0px;
	padding: 0px;
	background: linear-gradient(135deg,#0f2027,#203a43,#2c5364);
	background-size: cover;
	bacground-position: center;
	font-family: sans-serif;
	background-repeat:no-repeat;
	padding:;
}

.loginbox {
	width: 320px;
	height: 420px;
	background: rgba(255, 255, 255, 0.3);
	color: #fff;
	top: 52%;
	left: 50%;
	position: absolute;
	transform: translate(-50%, -50%);
	box-sizing: border-box;
	padding: 70px 30px;
	margin-top: 4%;
}

.avatar {
	width: 100px;
	height: 100px;
	border-radius: 50%;
	position: absolute;
	top: -50px;
	left: 100px;
	left: calc(50% -50px);
}

h1 {
	margin: 0;
	padding: 0 0 20px;
	text-aligh: center;
	font-size: 22px;
	text-align: center;
}

.loginbox p {
	margin: 0;
	padding: 0;
	font-weight: bold;
}

.loginbox input {
	width: 100%;
	margin-bottom: 20px;
}

.loginbox input[type="text"], input[type="password"] {
	border: none;
	border-bottom: 1px;
	background: transparent;
	outline: none;
	height: 40px;
	color: #fff;
	font-size: 16px;
}

.loginbox input[type="submit"] {
	border: none;
	outline: none;
	height: 40px;
	background: #fb2525;
	color: #fff;
	font-size: 18px;
	border-radius: 20px;
}

.loginbox input[type="submit"]:hover {
	cursor: pointer;
	background: gray;
	color: #000;
}

.loginbox a {
	text-decoration: none;
	font-size: 12px;
	line-height: 20px;
	color: darkgrey;
}

.side {
	float: left;
	width:898px	;
	height:425px;
	margin-top: 2%;
	margin-left: 1%;
}
</style>
</head>
<body>
	<jsp:include page="common/header1.jsp"></jsp:include>
	<div class="loginbox">
		<center style="color: yellow">${alert }</center>
		<br>
		<h1 style="color: red">User Login </h1>
		<form action="./login" method="post">
			<p>User Name</p>
			<input type="text" name="uname" placeholder="Enter  Username">
			<p>Password</p>
		<input type="password" name="password" placeholder="Enter Password">
			<input type="Submit" name="submit" value="Login"> 
			<a  href="register.jsp" style="color:white">Don't Have an Account</a>
		</form>
	</div>

</body>
</html>
