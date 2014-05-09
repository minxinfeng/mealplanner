<html>
<head>
  <title>Meal Planner</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, charset=utf-8">
    <!-- Bootstrap -->
    <#include "/base/base.ftl">
    <script type="text/javascript">
		$(function () {
			$('#myTab a:last').tab('show')
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
              <ul class="nav nav-tabs" id="myTab">
				  <li><a href="#home" data-toggle="tab">May-11</a></li>
				  <li><a href="#profile" data-toggle="tab">May-12</a></li>
				  <li><a href="#messages" data-toggle="tab">May-13</a></li>
				  <li class="active"><a href="#settings" data-toggle="tab">May-14</a></li>
				</ul>

				<div class="tab-content">
				  <div class="tab-pane" id="home">home</div>
				  <div class="tab-pane" id="profile">because of you</div>
				  <div class="tab-pane" id="messages">I find hard</div>
				  <div class="tab-pane active" id="settings"><!-- grid -->
				  	<div class="bs-docs-grid">					    
					    <div class="row show-grid">
					      <div class="col-md-4"></div>
					      <div class="col-md-4"></div>
					      <div class="col-md-4"></div>
					    </div>
					    <div class="row show-grid">
					      <div class="col-md-6"></div>
					      <div class="col-md-6"></div>
					    </div>
					    <div class="row show-grid">
					      <hr>
					      <div class="col-md-3">
					      	<div class="container">
					  			 <button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="left" title="No 1, contain 4 people">Table 4</button>
							</div>
					      </div>
					      <div class="col-md-3">
					      	<div class="container">
					  			<button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="top" title="No 2, contain 3 people">Table 3</button>
							</div>
					      </div>
					      <div class="col-md-3">
					      	<div class="container">
					  			 <button type="button" class="btn btn-default" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">Table 2</button>
							</div>
					      </div>
					      <div class="col-md-3">
					      	<div class="container">
					  			 <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal" data-placement="right" title="No 4, contain 1 people">Table 1</button>
					  			 <div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
				                  <div class="modal-dialog">
				                    <div class="modal-content">
				                      <div class="modal-header">
				                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
				                        <h4 class="modal-title" id="myModalLabel">Choose Time</h4>
				                      </div>
				                      <div class="modal-body">
				                        <div class="bs-docs-grid">					    
										    <div class="row show-grid">
										      <div class="col-md-3">
										      	<button type="button" class="btn btn-success" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">10:00</button>
										      </div>
										      <div class="col-md-3">
										      	<button type="button" class="btn btn-warning" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">11:00</button>
										      </div>
										      <div class="col-md-3">
										      <button type="button" class="btn btn-waring" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">12:00</button>
										      </div>
										      <div class="col-md-3">
										      	<button type="button" class="btn btn-success" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">13:00</button>
										      </div>
										    </div>
										    <hr>
										    <div class="row show-grid">
										      <div class="col-md-3">
										      	<button type="button" class="btn btn-success" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">14:00</button>
										      </div>
										      <div class="col-md-3">
										      	<button type="button" class="btn btn-warning" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">15:00</button>
										      </div>
										      <div class="col-md-3">
										      <button type="button" class="btn btn-waring" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">16:00</button>
										      </div>
										      <div class="col-md-3">
										      	<button type="button" class="btn btn-success" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">17:00</button>
										      </div>
										    </div>
										    <hr>
										    <div class="row show-grid">
										      <div class="col-md-3">
										      	<button type="button" class="btn btn-success" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">18:00</button>
										      </div>
										      <div class="col-md-3">
										      	<button type="button" class="btn btn-success" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">19:00</button>
										      </div>
										      <div class="col-md-3">
										      <button type="button" class="btn btn-error" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">20:00</button>
										      </div>
										      <div class="col-md-3">
										      	<button type="button" class="btn btn-error" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">21:00</button>
										      </div>
										    </div>
										    <hr>
										    <div class="row show-grid">
										      <div class="col-md-3">
										      	<button type="button" class="btn btn-error" data-toggle="tooltip" data-placement="bottom" title="No 3, contain 2 people">22:00</button>
										      </div>										      
										    </div>
									    </div>              
				                      </div>
				                      <div class="modal-footer">
				                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancle</button>
				                        <button type="button" class="btn btn-primary">Save</button>
				                      </div>

				                    </div><!-- /.modal-content -->
				                  </div><!-- /.modal-dialog -->
				                </div><!-- /.modal -->
							</div>
					      </div>
					    </div>
					  </div>
					  <hr>
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

      <footer>
        <p class="text-center">&copy; ThreeOne 2014</p>
      </footer>
     </div>

      
</body>
</html>
