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
            <li><a class="menuHref" href="#">Home</a></li>
            <li><a class="menuHref" href="#">Menu</a></li>
            <li><a class="dropdown-toggle seatHref" href="#"  style="margin-bottom:-5px" data-toggle="dropdown">Seat</a>
            	 <ul class="dropdown-menu">
	              <li><a class="seatHref" href="seat.html">Seat</a></li>
	              <li><a class="seatManagerHref" href="seatManager.html">Seat Manager</a></li>
	            </ul></li>
            <li><a class="orderHref" href="#">Order</a></li>
            <li><a class="sequenceHref" href="#">Sequence</a></li>
          </ul>          
          <ul id="navBar-right" class="nav navbar-nav navbar-right">
            <li class="dropdown">
            <a href="#" class="dropdown-toggle hello-name" style="margin-bottom:-5px" data-toggle="dropdown">
              <img src="http://www.gravatar.com/avatar/626ea913a31dadcfa8e27ec663fca996?s=25" />&nbsp&nbspUsername
            </a>
            <ul class="dropdown-menu">
              <li><a href="/accounts/logout/">Sign out</a></li>
            </ul>
            </li>
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
<script type="text/javascript">
    $(document).ready(function () { 
      $('.dropdown-toggle').dropdown();
      $(".menuHref").attr("href","${rc.contextPath}/web/menu/getMenuByUserId?userId=" +$.cookie("rest_userid"));
      $(".seatHref").attr("href","${rc.contextPath}/web/seat/getSeatByUserId?userId=" +$.cookie("rest_userid"));
      $(".seatManagerHref").attr("href","${rc.contextPath}/web/seat/getSeatInfosByUserId?userId=" +$.cookie("rest_userid"));
      $(".orderHref").attr("href","${rc.contextPath}/web/order/getOrderByUserId?userId=" +$.cookie("rest_userid"));
      $(".sequenceHref").attr("href","${rc.contextPath}/web/sequence/getSequenceByUserId?userId=" +$.cookie("rest_userid"));
      
      $.ajax({
          type:"POST",
          url:"${rc.contextPath}/web/getUserId",
          data:{"userName": $.cookie("rest_userid")},
          success:function(result){
            if(result.success){
              
            }
          }                    
        });      
    })   
    </script>
</head>

