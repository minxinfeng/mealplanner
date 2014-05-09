<html>
<head>
  <title>Meal Planner</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, charset=utf-8">
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
            <li class="active"><a href="home.html">Home</a></li>
            <li><a href="menu.html">Menu</a></li>
            <li><a href="seat.html">Seat</a></li>
            <li><a href="order.html">Order</a></li>
            <li><a href="sequence.html">Sequence</a></li>
          </ul>  
          <ul id="navBar-right" class="nav navbar-nav navbar-right">
            <li class="dropdown">
            <a href="#" class="dropdown-toggle" style="margin-bottom:-5px" data-toggle="dropdown">
              <img src="http://www.gravatar.com/avatar/626ea913a31dadcfa8e27ec663fca996?s=25" />&nbsp&nbspUsername
            </a>
            <ul class="dropdown-menu">
              <li><a href="/discuss/user/kylewang1005@gmail.com">My Discuss</a></li>
              <li><a href="/profile/">Profile</a></li>
              <!-- TODO:  Dashboard  -->
              <li><a href="/accounts/password/change/">Change Password</a></li>
              <li class="divider"></li>
              <li><a href="/accounts/logout/">Sign out</a></li>
            </ul>
            </li>
          </ul>     
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1>Hans</h1>
        <p>Address:New York </p>
        <p>Tel:(555)55555555 </p>
        <p><a class="btn btn-primary btn-lg" role="button">Learn more &raquo;</a></p>
      </div>
    </div>

    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md-6">
          <h2>Notice</h2>
          <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div>
        <div class="col-md-6">
          <h2>Heading</h2>
          <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
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