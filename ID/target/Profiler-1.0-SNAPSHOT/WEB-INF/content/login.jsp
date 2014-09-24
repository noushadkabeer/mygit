<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<link href="<s:url value="/css/main.css"/>" rel="stylesheet"
	type="text/css" />
</head>

<body>
	<center>
		<table width=600 align=center>
			<tr>
				<td>Login</td>
			</tr>
		</table>
		<table>
			<tr>
				<td align="left" style="font: bold; color: red"><s:fielderror />
					<s:actionerror /></td>
				<td align="left" style="font: bold; color: green">
					 <s:actionmessage /></td>
			</tr>
		</table>
		<s:form action="login.action" method="post">
			<table align="center" class="borderAll">
				<tr>
					<img src="<s:url value="/images/0 x600.jpg"/>" />
					<br />
					<br />
				</tr>
				<tr>
					Username :
					<s:textfield name="user.userName" key="label.userName" size="20" />
					Password :
					<s:password name="user.userPassword" key="label.userPassword"
						size="20" />
					<s:submit key="label.login" align="right" />
					<s:url id="insert" action="setUpForInsertOrUpdateUser" />
					<s:a href="%{insert}">New User Sign Up!</s:a>
					</td>
				</tr>
			</table>
		</s:form>
	</center>
</body>
</html>