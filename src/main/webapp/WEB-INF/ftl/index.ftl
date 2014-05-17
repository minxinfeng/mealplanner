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
  <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1>${restaurantInfo.getRestname()}</h1>
        <p>Address:${restaurantInfo.getRestaddress()} </p>
        <p>Tel:${restaurantInfo.getRestphone()}</p>
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