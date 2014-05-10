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
            <li><a href="${rc.contextPath}/web/index">Home</a></li>
            <li class="active"><a class="menuHref" href="#">Menu</a></li>
            <li><a href="${rc.contextPath}/web/seat/seat">Seat</a></li>
            <li><a href="${rc.contextPath}/web/order/order">Order</a></li>
            <li><a href="${rc.contextPath}/web/sequence/sequence">Sequence</a></li>
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
<script type="text/javascript">
    $(document).ready(function () { 
      $(".menuHref").attr("href","${rc.contextPath}/web/menu/getMenuByUserId?userId=" +$.cookie("rest_userid"));
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

