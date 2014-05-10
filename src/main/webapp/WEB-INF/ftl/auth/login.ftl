<html>
<head>
  <title>Meal Planner</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <#include "/base/base.ftl">
    <script type="text/javascript">
    $(document).ready(function(){
      $('.getRestName').click(function(){
        $.ajax({
          type:"POST",
          url:"${rc.contextPath}/web/getRestauntName",
          data:{"loginName":$('#loginName').val()},
          contentType:"text/html; charset=utf-8", 
          dataType:"json",
          success:function(result){
              if(result.success){
              var rest_name = result.data;
              console.log("rest_name:", rest_name);
              $.cookie("rest_name",rest_name);
            }
          }
        });
      });

      $('#signin').click(function(){
          $.cookie("rest_username",$('#loginName').val());                 
        });
    })
    </script>
</head>
<body>
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse">
            <span class="sr-only">Toggle navigation</span>
          </button>
          <a class="navbar-brand" href="#">Plan your meal</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
          </ul>         
          <form class="navbar-form navbar-right" role="form">
            <a class="btn btn-success" href="${rc.contextPath}/web/register">Sign up</a>
          </form>
        </div><!--/.nav-collapse -->
      </div>
    </div>


    <div class="container">
    <#include "base/alert.ftl">
      <form class="form-signin" role="form" action="${rc.contextPath}/web/login"  method="post">
        <h2 class="form-signin-heading">Sign in</h2>
        <input type="text" id="loginName" name="loginName" class="form-control" placeholder="username/phoneNum/email" required autofocus>
        <input type="password" name="password" class="form-control" placeholder="Password" required>
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> Remember me
        </label>
        <button class="btn btn-lg btn-primary btn-block getRestName" type="submit" id="signin">Sign in</button>
      </form>
    </div> <!-- /container -->    
      <footer>
        <p class="text-center">&copy; ThreeOne 2014</p>
      </footer>
    </div> <!-- /container -->
	</script>
</body>
</html>