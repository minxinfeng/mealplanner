<html>
<head>
  <title>Meal Planner</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, charset=utf-8">
    <!-- Bootstrap -->
    <#include "/base/base.ftl">
    <script type="text/javascript">
		$(function () {
			$('#dateTab a:last').tab('show')
		})
	</script>
	<script type="text/javascript">
    $(document).ready(function () {    
      $('#userId').val($.cookie("rest_userid"));
      $('.dropdown-toggle').dropdown();
      
      //get 7 tabs for next 7 dates 
      var curDate = new Date();
      curDate.setDate(curDate.getDate() + 30);
      var tabDate = curDate;
      var dateActive = tabDate.getFullYear() + '-' + tabDate.getMonth() + '-' + tabDate.getDate();
      for(var i = 0; i < 7; i++){
      	tabDate.setDate(curDate.getDate() + 1);
      	if( tabDate.getMonth() < 10){
      		dateActive = tabDate.getFullYear() + '-0' + tabDate.getMonth() + '-' + tabDate.getDate();
      	}else{
      		dateActive = tabDate.getFullYear() + '-' + tabDate.getMonth() + '-' + tabDate.getDate();
      	}
      	
      	if(i == 0){
      		$('#dateTab').append(
	      		$('<li>').attr('class','active dateTabLi').append(
	      			$('<a>').attr({
	      				'id':dateActive,
	      				'href':'#tables_' + dateActive,
	      				'data-toggle':'tab'
	      				}).append(tabDate.getMonth() + '-' + tabDate.getDate()
	      			)
	      		)
	      	);
      	}
      	else{
      		$('#dateTab').append(
	      		$('<li>').attr('class','dateTabLi').append(
	      			$('<a>').attr({
	      				'id':dateActive,
	      				'href':'#tables_' + dateActive,
	      				'data-toggle':'tab'
	      				}).append(tabDate.getMonth() + '-' + tabDate.getDate()
	      			)
	      		)
	      	);
      	}
      	
      } 
	  //complete 7 tabs for next 7 dates 

	  //set click event to get staus of a seat
      $(".seat-grid").click(function(){
      	var strArrayId = $(this).attr("id").split("_");
      	var seatId = strArrayId[0].substr(2);
      	var userId = $.cookie("rest_userid");
      	var dateDay = strArrayId[1];
      	$.ajax({
      		type:"GET",
      		url:"${rc.contextPath}/web/seat/getSeatStatusBySeatId",
      		data:{"seatId":seatId, "userId":userId, "dateDay":dateDay},      		
      		success:function(result){
      			if(result.success){
      				var clockState = result.data;
      				var count = 10;
      				var state;
      				for(var key in clockState){
      					state = clockState[key];
      					switch(state){
      						case 0: $('#'+ count + '_' + seatId + '_' + dateDay + '.btn').addClass("btn-error");// AVAILABLE
      							break;
      						case 1: $('#'+ count + '_' + seatId + '_' + dateDay + '.btn').addClass("btn-success");// RESERVED
      							break;
      						case 2: $('#'+ count + '_' + seatId + '_' + dateDay + '.btn').addClass("btn-warning");// OCCUPIED
      							break;
      						
      					}
      					count++;
      				}
      			}

      		}
      	});//ajax
      });//click

	var dateCopy = new Date();   
    dateCopy.setDate(dateCopy.getDate() + 31); 
    if(dateCopy.getMonth() < 10){
    	dateTemp = dateCopy.getFullYear() + '-0' + dateCopy.getMonth() + '-' + dateCopy.getDate();
    }else{
    	dateTemp = dateCopy.getFullYear() + '-' + dateCopy.getMonth() + '-' + dateCopy.getDate();
    }    
	$('#tables_' + dateTemp).addClass("active");
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
		        	<li><a href="home.html">Home</a></li>
		            <li><a href="menu.html">Menu</a></li>
		            <li class="active"><a href="seat.html" class="dropdown-toggle" style="margin-bottom:-5px" data-toggle="dropdown">Seat</a>
		            	<ul class="dropdown-menu">
				            <li><a href="seat.html">Seat</a></li>
				            <li><a href="seatManager.html">Seat Manager</a></li>
			            </ul>
		            </li>
		            <li><a href="order.html">Order</a></li>
		            <li><a href="squence.html">Sequence</a></li>
		        </ul>  
		        <ul id="navBar-right" class="nav navbar-nav navbar-right">
		            <li class="dropdown">
		            <a href="#" class="dropdown-toggle" style="margin-bottom:-5px" data-toggle="dropdown">
		              <img src="http://www.gravatar.com/avatar/626ea913a31dadcfa8e27ec663fca996?s=25" />&nbsp&nbspUsername
		            </a>
		            <ul class="dropdown-menu">
			            <li><a href="/discuss/user/username">My Discuss</a></li>
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
          <h1>Seats</h1>
        </div>
        <div class="row">
          	<div class="col-sm-12">
            	<div class="panel panel-default">
	              	<ul id="dateTab" class="nav nav-tabs">
	              						 
					</ul>
					<div class="tab-content">
						<#list nextWeeks as dateOffset>
						<!--set 7 days tab -->
						<div class="tab-pane" id="tables_${dateOffset}"><!-- grid -->
					  	<div class="bs-docs-grid">				    
						    <div class="row show-grid">					  		
					  		<#assign column = 1/>
					  		<#assign testseatInfos = seatInfos/>
					  		<#list testseatInfos as seatInfo>					  				
				          		<#if (column % 5) != 0>
				          		<div class="col-md-3">
							      	<div class="container">	
							      		<button id="No${seatInfo.getSeatid()}_${dateOffset}" 
							      		type="button" class="btn btn-default btn-lg seat-grid" 
							      		data-toggle="modal" 
							      		data-target="#seatInfoModal_${seatInfo.getSeatid()}_${dateOffset}" 
							      		data-placement="left" 
							      		title="contain ${seatInfo.getPeoplenum()} people"}>No ${seatInfo.getSeatid()}</button>
							      		<#include "/base/seatModal.ftl">						      			
									</div>
							    </div>
							    <#else>
							</div>
							<div class="row show-grid">
							<hr>
							    <div class="col-md-3">
							      	<div class="container">
							      		<button id="No${seatInfo.getSeatid()}_${dateOffset}}" 
							      		type="button" class="btn btn-default btn-lg seat-grid" 
							      		data-toggle="modal" data-target="#seatInfoModal_${seatInfo.getSeatid()} ${dateOffset}" 
							      		data-placement="left" title="contain ${seatInfo.getPeoplenum()} people"}>No ${seatInfo.getSeatid()}</button>
							      		<#include "/base/seatModal.ftl">
									</div>
							    </div>
							    </#if>
							    <#assign column = column + 1/>
				          	</#list> 
				          	</div>
				          	<hr>
						</div>
						</div><!-- /grid -->				
						</#list>
					</div>			
	            </div>
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
