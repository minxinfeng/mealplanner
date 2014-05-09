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
            <li><a href="home.html">Home</a></li>
            <li><a href="menu.html">Menu</a></li>
            <li><a href="seat.html">Seat</a></li>
            <li class="active"><a href="order.html">Order</a></li>
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
     <div class="container theme-showcase">
        <div class="page-header">
          <h1>Orders</h1>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <a href="#" class="list-group-item active">
              <h4 class="list-group-item-heading">List group item heading</h4>
              <p class="list-group-item-text">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
              <button type="button" class="btn btn-sm btn-success">Accept</button>
              <!--<button type="button" class="btn btn-sm btn-warning">Cancle</button>-->
            </a>
            <a href="#" class="list-group-item">
              <h4 class="list-group-item-heading">List group item heading</h4>
              <p class="list-group-item-text">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
            <div class="dd"> 
              <!--<button type="button" class="btn btn-sm btn-success">Accept</button>-->
              <button type="button" class="btn btn-sm btn-warning">Cancle</button>
            </div>
            </a>
            <a href="#" class="list-group-item">
              <h4 class="list-group-item-heading">List group item heading</h4>
              <p class="list-group-item-text">Donec id elit non mi porta gravida at eget metus. Maecenas sed diam eget risus varius blandit.</p>
              <!--<button type="button" class="btn btn-sm btn-success">Accept</button>-->
              <button type="button" class="btn btn-sm btn-warning">Cancle</button>
            </a>
        </div><!-- /.col-sm-12 -->
      </div>
         <div class="col-sm-12">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#">&laquo;</a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">&raquo;</a></li>           
          </ul>
      </div>
      <hr>

      <footer>
        <p class="text-center">&copy; ThreeOne 2014</p>
      </footer>
     </div>      
</body>
</html>