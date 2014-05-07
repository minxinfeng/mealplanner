<html>
<head>
  <title>Meal Planner</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, charset=utf-8">
    <!-- Bootstrap -->
    <#include "/base/base.ftl">
    <script type="text/javascript">
    $(document).ready(function () {
    	$('.dropdown-toggle').dropdown();
	    $(".deleteMenu").click(function(){
	    	var id = $(this).parent().parent().attr("id");
	    	$(this).parent().parent().remove(); 
	    	$.ajax({
	    		type:"POST",
	    		url:"${rc.contextPath}/web/menu/deleteMenu",
	    		data:{"menuid":id},
	    		success:function(msg){  	    						
	    			}
	    	});
	    	
	    });
    }) 
    
    
    </script>
</head>
<body>
    <#include "/base/header.ftl">
     <div class="container theme-showcase">
        <div class="page-header">
          <h1>Menus</h1>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <div class="panel panel-default">
              <!-- Default panel contents -->
              <div class="panel-heading">
                <button type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#addMenuModal">
                  <span class="glyphicon glyphicon-plus"></span>
                </button>
                <div id="addMenuModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                        <h4 class="modal-title" id="myModalLabel">New food</h4>
                      </div>
                      <div class="modal-body">
                        <div>
                          <p>Food name:</p>
                          <input type="text" id="foodName" name="foodName" class="form-control" placeholder="new name" required autofocus>
                          <p>Food price:</p>
                          <input type="text" id="foodPrice" name="foodPrice" class="form-control" placeholder="new price" required autofocus>
                          <p>Choose food type</p>                
                          <div class="btn-group">
                            <button class="btn dropdown-toggle" data-toggle="dropdown" >food type <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                              <li><a href="#">北京菜</a></li>
                              <li><a href="#">四川菜</a></li>
                              <li><a href="#">XX菜</a></li>
                              <li class="divider"></li>
                              <li><a href="#">Separated link</a></li>
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
              <table class="table">
                <thead>
                  <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Type</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                <#list menuInfos as menuInfo>
                  <tr id="${menuInfo.menuid}">
                    <td>${menuInfo.menuid}</td>
                    <td>${menuInfo.menuname}</td>
                    <td>${menuInfo.menuprice} $</td>
                    <td>${menuInfo.foodTypeName}</td>
                    <td>
                      <button type="button" class="btn btn-default btn-sm editMenu" data-toggle="modal" data-target="#editMenuModal">
                        <span class="glyphicon glyphicon-pencil"></span>
                      </button>
                      <div id="editMenuModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
		                  <div class="modal-dialog">
		                    <div class="modal-content">
		                      <div class="modal-header">
		                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
		                        <h4 class="modal-title" id="myModalLabel">Edit food</h4>
		                      </div>
		                      <div class="modal-body">
		                        <div>
		                          <p>Food name:</p>
		                          <input type="text" id="foodName" name="foodName" class="form-control" placeholder="${menuInfo.menuname}" required autofocus>
		                          <p>Food price:</p>
		                          <input type="text" id="foodPrice" name="foodPrice" class="form-control" placeholder="${menuInfo.menuprice}" required autofocus>
		                          <p>Choose food type</p>                
		                          <div class="btn-group">
		                            <button class="btn dropdown-toggle" data-toggle="dropdown">food type <span class="caret"></span>
		                            </button>
		                            <ul class="dropdown-menu">
		                              <li><a href="#">北京菜</a></li>
		                              <li><a href="#">四川菜</a></li>
		                              <li><a href="#">XX菜</a></li>
		                              <li class="divider"></li>
		                              <li><a href="#">Separated link</a></li>
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
                      <button type="button" class="deleteMenu btn btn-default btn-sm">
                        <span class="glyphicon glyphicon-trash"></span>
                      </button>
                    </td>
                  </tr>
                  </#list>
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