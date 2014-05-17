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
            <a type="button" class="btn btn-success" href="${rc.contextPath}/web/register">Sign up</a>
            <a type="button" class="btn btn-default" href="${rc.contextPath}/web/login">Sign in</a>
          </form>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1>Meal Planner</h1>
        <p>This is web app for restaurant user to management menu information, seat infomation, get order from where done with meal planner app and help you to deal with a lot people waiting in a sequence</p>
        <p><a class="btn btn-primary btn-lg" role="button">Learn more &raquo;</a></p>
      </div>
    </div>

    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-6">
          <h2>Notice</h2>
          <p>I could help to manage seat status between 10:00 - 22:00 of next 7 days!</p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div>
        <div class="col-md-6">
          <h2>Recommend</h2>
          <p>Now we have a friendly manner to deal with sequence and you can try it with sign up! </p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
       </div>
      </div>

      <hr>

      <footer>
        <p>&copy; ThreeOne 2014</p>
      </footer>
    </div> <!-- /container -->
</body>
</html>