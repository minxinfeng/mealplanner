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
      var tabDate = curDate;
      for(var i = 0; i < 7; i++){
      	tabDate.setDate(curDate.getDate() + 1);
      	if(i == 0){
      		$('#dateTab').append(
	      		$('<li>').attr('class','active dateTabLi').append(
	      			$('<a>').attr({
	      				'id':tabDate.getFullYear() + '-' + tabDate.getMonth() + '-' + tabDate.getDate(),
	      				'href':'#tables',
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
	      				'id':tabDate.getFullYear() + '-' + tabDate.getMonth() + '-' + tabDate.getDate(),
	      				'href':'#tables',
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
      	var seatId = $(this).attr("id").substr(2);
      	var userId = $.cookie("rest_userid");
      	var dateDay = $(".dateTabLi.active").children("a").attr("id");
      	$.ajax({
      		type:"GET",
      		url:"${rc.contextPath}/web/seat/getSeatStatusBySeatId",
      		data:{"seatId":seatId, "userId":userId, "dateDay":dateDay}, 
      		// data:{"seatId":$(this).attr("id"), "userId":$.cookie("rest_userid"), "dateDay": $('#dateTab .active').attr("id")},       		
      		success:function(result){
      			if(result.success){
      				var clockState = result.data;
      				var count = 10;
      				var state;
      				for(var key in clockState){
      					state = clockState[key];
      					switch(state){
      						case 0: $('#'+ count + '.btn').addClass("btn-error");// AVAILABLE
      							console.log("0");
      							break;
      						case 1: $('#'+ count + '.btn').addClass("btn-success");// RESERVED
      							console.log("1");
      							break;
      						case 2: $('#'+ count + '.btn').addClass("btn-error");// OCCUPIED
      							console.log("2");
      							break;
      						
      					}
      					count++;
      				}
      			}

      		}
      	});//ajax
      });//click

	  
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
						<div class="tab-pane active" id="tables"><!-- grid -->
					  	<div class="bs-docs-grid">				    
						    <div class="row show-grid">					  		
					  		<#assign column = 1/>
					  		<#list seatInfos as seatInfo>					  				
				          		<#if (column % 5) != 0>
				          		<div class="col-md-3">
							      	<div class="container">
							      		<button id="No${seatInfo.getSeatid()}" type="button" class="btn btn-default btn-lg seat-grid" data-toggle="modal" data-target="#seatInfoModal" data-placement="left" title="contain ${seatInfo.getPeoplenum()} people"}>No ${seatInfo.getSeatid()}</button>								      			
									</div>
							    </div>
							    <#else>
							</div>
							<div class="row show-grid">
							<hr>
							    <div class="col-md-3">
							      	<div class="container">
							      		<button id="No${seatInfo.getSeatid()}" type="button" class="btn btn-default btn-lg seat-grid" data-toggle="modal" data-target="#seatInfoModal" data-placement="left" title="contain ${seatInfo.getPeoplenum()} people"}>No ${seatInfo.getSeatid()}</button>		
									</div>
							    </div>
							    </#if>
							    <#assign column = column + 1/>
				          	</#list> 
				          	</div>
				          	<hr>
						</div>
						</div><!-- /grid -->
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
	  
	  <div id="seatInfoModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="seatInfoModalLabel" aria-hidden="true" style="display: none;">
	  	<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
				     <h4 class="modal-title" id="seatInfoModalLabel">Choose Time</h4>
				</div>
				<div class="modal-body">
				    <div class="bs-docs-grid">					    
						<div class="row show-grid">
							<div class="col-md-3">
								<button id="10" type="button" class="btn" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">10:00</button>
							</div>
							<div class="col-md-3">
								<button id="11" type="button" class="btn" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">11:00</button>
							</div>
							<div class="col-md-3">
								<button id="12" type="button" class="btn" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">12:00</button>
							</div>
							<div class="col-md-3">
								<button id="13" type="button" class="btn" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">13:00</button>
							</div>
						</div>
						<hr>
						<div class="row show-grid">
							<div class="col-md-3">
								<button id="14" type="button" class="btn" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">14:00</button>
							</div>
							<div class="col-md-3">
								<button id="15" type="button" class="btn" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">15:00</button>
							</div>
							<div class="col-md-3">
								<button id="16" type="button" class="btn" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">16:00</button>
							</div>
							<div class="col-md-3">
								<button id="17" type="button" class="btn" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">17:00</button>
							</div>
						</div>
						<hr>
						<div class="row show-grid">
							<div class="col-md-3">
								<button id="18" type="button" class="btn" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">18:00</button>
							</div>
							<div class="col-md-3">
								<button id="19" type="button" class="btn" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">19:00</button>
							</div>
							<div class="col-md-3">
								 <button id="20" type="button" class="btn" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">20:00</button>
							</div>
							<div class="col-md-3">
								<button id="21" type="button" class="btn" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">21:00</button>
							</div>
						</div>
						<hr>
						<div class="row show-grid">
							<div class="col-md-3">
								<button id="22" type="button" class="btn" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">22:00</button>
							</div>										      
						</div>
					</div>              
				</div>
				<div class="modal-footer">
				    <button type="button" class="btn btn-default" data-dismiss="modal">Cancle</button>
				    <button type="submit" class="btn btn-primary">Save</button>
				</div>

			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

				                  
	  
      <footer>
        <p class="text-center">&copy; ThreeOne 2014</p>
      </footer>
     </div>

      
</body>
</html>
