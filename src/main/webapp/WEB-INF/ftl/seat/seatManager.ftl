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
          <h1>Seat Manager</h1>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <div class="panel panel-default">
              <!-- Default panel contents -->
              <div class="panel-heading">
                <button type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#addSeatModal">
                  <span class="glyphicon glyphicon-plus"></span>
                </button>
                <div id="addSeatModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                        <h4 class="modal-title" id="myModalLabel">Add table</h4>
                      </div>
                      <div class="modal-body">
                        <div>
                          <p>Seat No:</p>
                          <input type="text" id="seatNo" name="seatNo" class="form-control" placeholder="1" required autofocus>
                          <p>Description:</p>
                          <input type="text" id="peopleNum" name="peopleNum" class="form-control" placeholder="you can say sth about this table!" required autofocus>
                          <p>Choose people num</p>                
                          <div class="btn-group">
                            <button class="btn dropdown-toggle" data-toggle="dropdown"> 2 <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                              <li><a href="#">4</a></li>
                              <li><a href="#">6</a></li>
                              <li><a href="#">8</a></li>
                              <li class="divider"></li>
                              <li><a href="#">20</a></li>
                            </ul>
                          </div>
                        </div>                   
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancle</button>
                        <button type="button" class="btn btn-primary">Save</button>
                      </div>

                    </div><!-- /.modal-content -->
                  </div><!-- /.modal-dialog -->
                </div>
              </div>
              <div class="panel-body">
                <p></p>
              </div>

              <!-- Table -->
              <table id="t1" class="table">
                <thead>
                  <tr>
                    <th>#</th>
                    <th>Seat No</th>
                    <th>People num</th>
                    <th>Description</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>1</td>
                    <td>1</td>
                    <td>4</td>
                    <td>summer palace</td>
                    <td>
                      <button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#editSeatModal">
                        <span class="glyphicon glyphicon-pencil"></span>
                      </button>
                      <div id="editSeatModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                        <div class="modal-dialog">
                          <div class="modal-content">
                            <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                              <h4 class="modal-title" id="myModalLabel">Edit table</h4>
                            </div>
                            <div class="modal-body">
                              <div>
                                <p>Seat No:</p>
                                <input type="text" id="seatNo" name="seatNo" class="form-control" placeholder="1" required autofocus>
                                <p>Description:</p>
                                <input type="text" id="peopleNum" name="peopleNum" class="form-control" placeholder="you can say sth about this table!" required autofocus>
                                <p>Choose people num</p>                
                                <div class="btn-group">
                                  <button class="btn dropdown-toggle" data-toggle="dropdown"> 2 <span class="caret"></span>
                                  </button>
                                  <ul class="dropdown-menu">
                                    <li><a href="#">4</a></li>
                                    <li><a href="#">6</a></li>
                                    <li><a href="#">8</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#">20</a></li>
                                  </ul>
                                </div>
                              </div>                   
                            </div>
                            <div class="modal-footer">
                              <button type="button" class="btn btn-default" data-dismiss="modal">Cancle</button>
                              <button type="button" class="btn btn-primary">Save</button>
                            </div>
                          </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                      </div>
                      <button type="button" class="btn btn-default btn-sm">
                        <span class="glyphicon glyphicon-trash"></span>
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
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
