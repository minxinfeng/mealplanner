<html>
<head>
  <title>Meal Planner</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, charset=utf-8">
    <!-- Bootstrap -->
    <#include "/base/base.ftl">
    <script type="text/javascript">
    $(document).ready(function () {    
      $('#userId.addFoodModal').val($.cookie("rest_userid"));
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
      $(".editMenu").click(function(){
        var mId = $(this).parent().parent().attr("id");
        $('#userId.editFoodModal').val($.cookie("rest_userid"));
        $('#menuId').val(mId);
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
                <button type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#addFoodModal">
                  <span class="glyphicon glyphicon-plus"></span>
                </button>
                <div id="addFoodModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <form class="form-addmenu" role="form" action="${rc.contextPath}/web/menu/addMenu" method="post">
                        <div class="modal-header">
                          <input id="userId" name="userId" class="form-control addFoodModal" type="hidden">
                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                          <h3 class="modal-title" id="myModalLabel">New food</h3>
                        </div>
                        <div class="modal-body">                        
                          <div class="form-group">
                            <h4>Food name</h4>
                            <input type="text" id="foodName" name="foodName" class="form-control" placeholder="fish chips" required autofocus>
                          </div>
                          <div class="form-group">
                            <h4>Food price</h4>
                            <div class="input-group">
                              <span class="input-group-addon">$</span>
                              <input type="text" id="foodPrice" name="foodPrice" class="form-control" placeholder="100" required autofocus>
                              <span class="input-group-addon">.00</span>
                            </div>
                          </div>
                          <div class="form-group">
                            <h4>Food type</h4>
                            <select name="foodType" class="form-control">
                              <#list foodTypes as foodType>
                                <option value=${foodType.resttypeid}>${foodType.resttypename}</option>
                              </#list>  
                            </select>
                          </div>
                          <div class="checkbox col-sm-offset-10 form-control">
                            <label>
                              <input name="recommand" type="checkbox" class="form-control"> recommand
                            </label>
                          </div>                                         
                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-default" data-dismiss="modal">Cancle</button>
                          <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                      </form>
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
                  <tr id="${menuInfo.menuid}" class="success menuItem">
                    <td>${menuInfo.menuid}</td>
                    <td>${menuInfo.menuname}</td>
                    <td>${menuInfo.menuprice} $</td>
                    <td>${menuInfo.foodTypeName}</td>
                    <td>
                      <button type="button" class="btn btn-default btn-sm editMenu" data-toggle="modal" data-target="#editFoodModal">
                        <span class="glyphicon glyphicon-pencil"></span>
                      </button>
                      <div id="editFoodModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                        <div class="modal-dialog">
                          <div class="modal-content">
                            <form class="form-addmenu" role="form" action="${rc.contextPath}/web/menu/updateMenuPart" method="get">
                              <div class="modal-header">
                                <input id="menuId" name="menuId" class="form-control" type="hidden">
                                <input id="userId" name="userId" class="form-control editFoodModal" type="hidden">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                                <h3 class="modal-title" id="myModalLabel">Edit menu</h3>
                              </div>
                              <div class="modal-body">                        
                                <div class="form-group">
                                  <h4>Food name</h4>
                                  <input type="text" id="foodName" name="foodName" class="form-control" placeholder="fish chips" required autofocus>
                                </div>
                                <div class="form-group">
                                  <h4>Food price</h4>
                                  <div class="input-group">
                                    <span class="input-group-addon">$</span>
                                    <input type="text" id="foodPrice" name="foodPrice" class="form-control" placeholder="100" required autofocus>
                                    <span class="input-group-addon">.00</span>
                                  </div>
                                </div>
                                <div class="form-group">
                                  <h4>Food type</h4>
                                  <select name="foodType" class="form-control">
                                    <#list foodTypes as foodType>
                                      <option value=${foodType.resttypeid}>${foodType.resttypename}</option>
                                    </#list>  
                                  </select>
                                </div>
                                <div class="checkbox col-sm-offset-10 form-control">
                                  <label>
                                    <input name="recommand" type="checkbox" class="form-control"> recommand
                                  </label>
                                </div>                                         
                              </div>
                              <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancle</button>
                                <button type="submit" class="btn btn-primary">Save</button>
                              </div>
                            </form>
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