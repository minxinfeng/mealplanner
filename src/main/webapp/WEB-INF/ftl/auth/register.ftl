<html>
<head>
  <title>Meal Planner</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <#include "/base/base.ftl">
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
            <a class="btn btn-success" href="${rc.contextPath}/web/login">Sign in</a>
          </form>
        </div><!--/.nav-collapse -->
      </div>
    </div>


    <div class="container">
		<#include "base/alert.ftl">
      <form class="form-signin" role="form" action="${rc.contextPath}/web/register"  method="post">
        <h2 class="form-signin-heading">Sign up</h2>
        
        <div class="form-group">
          <input type="text" name="username" class="form-control" placeholder="User name" required autofocus>        
        </div>
        <div class="form-group">
           <input type="text" name="phonenum" class="form-control" placeholder="Phone number" required>
        </div>
        <div class="form-group">
           <input type="text" name="email" class="form-control" placeholder="Email address" required>       
        </div>
        <div class="form-group">
           <input type="password" name="password" class="form-control" placeholder="Password" required>        
        </div>
        <div class="form-group">
           <input type="password" name="confirmPassword" class="form-control" placeholder="Confirm Password" required>        
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
      </form>

    </div> <!-- /container -->
    
      <footer>
        <p class="text-center">&copy; ThreeOne 2014</p>
      </footer>
    </div> <!-- /container -->
</body>
</html>