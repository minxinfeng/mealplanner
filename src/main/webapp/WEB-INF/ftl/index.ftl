<html>
<head>
<#include "/base/base.ftl">
</head>
<body>
	<#include "/base/header.ftl">
	<h2>Hello World!</h2>
	<br />
	Username: ${userInfo.username!''}
	<br />
	User Phone:${userInfo.phonenum}
</body>
</html>