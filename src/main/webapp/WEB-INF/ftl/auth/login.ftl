<html>
<head>
  <title>Meal Planner</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <#include "/base/base.ftl">
    <script type="text/javascript">
    $(document).ready(function(){  	
      $('#signin').click(function(){
        $.cookie("rest_username",$('#loginName').val());
      });
    })
    </script>
</head>
<body style>
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
            <h2>Sign in</h2>
            <div class="form-group">
                <label for="example1-email">Login name</label>
                <input type="text" id="loginName" name="loginName" class="form-control" placeholder="username/phoneNum/email" required autofocus>
            </div>
            <div class="form-group">
                <label for="example1-password">Password</label>
                <input type="password" name="password" class="form-control" placeholder="Password" required>
            </div>
            <div class="form-group">
                <label class="checkbox">
		          <input type="checkbox" value="remember-me"> Remember me
		        </label>
            </div>
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