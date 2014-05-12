<html>
<head>
<#include "/base/base.ftl">
    <script type="text/javascript">
    $(document).ready(function(){
      var userId = ${userInfo.getUserid()};
      $.cookie("rest_userid", userId);
      $.cookie("login", "yes");
    })
    </script>
<body>
	<#include "/base/header.ftl">
	<h2>Hello World!</h2>
	<br />
	Username: ${userInfo.username!''}
	<br />
	User Phone:${userInfo.phonenum}	
</body>
</html>