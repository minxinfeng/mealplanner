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
          <ul class="nav navbar-nav navbar-right">
          	<li> <span class='hello-name' style='line-height:20px;position:relative;display:block;padding:15px 15px;color:#fff'></span> </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
    
<script type="text/javascript">
    $(document).ready(function () {
    	var username = $.cookie("rest_username");
        $(".hello-name").html("Welcome " + username);
    });
</script>