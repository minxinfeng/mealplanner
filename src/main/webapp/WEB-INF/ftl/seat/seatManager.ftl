<html>
<head>
  <title>Meal Planner</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, charset=utf-8">
    <!-- Bootstrap -->
    <#include "/base/base.ftl">
    <script type="text/javascript">
    $(document).ready(function(){
      $('#userId.addSeatModal').val($.cookie("rest_userid"));
      $(".deleteSeat").click(function(){
        var id = $(this).parent().parent().attr("id");
        $(this).parent().parent().remove(); 
        $.ajax({
          type:"POST",
          url:"${rc.contextPath}/web/seat/deleteSeat",
          data:{"seatId":id},
          success:function(msg){                    
            }
        });        
      });

     $(".editSeat").click(function(){
        $('#userId.editSeatModal').val($.cookie("rest_userid"));
        var seatId = $(this).parent().parent().attr("id");
        $('#seatId').val(seatId);      
      });
    })
    </script>
</head>
<body>
    <#include "/base/header.ftl">
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
                    <form class="form-addseat" role="form" action="${rc.contextPath}/web/seat/addSeat" method="get">
                      <div class="modal-header">
                        <input id="userId" name="userId" class="form-control addSeatModal" type="hidden">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                        <h4 class="modal-title" id="myModalLabel">Add table</h4>
                      </div>
                      <div class="modal-body">
                        <div>
                          <p>Seat No:</p>
                          <input type="text" id="seatNo" name="seatNo" class="form-control" placeholder="1" required autofocus>
                          <p>Description:</p>
                          <input type="text" id="description" name="description" class="form-control" placeholder="you can say sth about this table!" required autofocus>
                          <p>Choose people num</p>                
                          <div>
                            <select name="peopleNum" class="form-control">
                              <option value="2">2 people</option>
                              <option value="4">4 people</option>
                              <option value="6">6 people</option>
                              <option value="8">8 people</option>
                              <option value="10">10 people</option>
                            </select>                            
                          </div>
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
                <#assign count = 0/>
                <#list seatInfos as seatInfo>
                <#assign count = count + 1/>
                  <tr id="${seatInfo.getSeatid()}" class="seatItem">
                    <td>${count}</td>
                    <td>No ${seatInfo.getSeatno()}</td>
                    <td>${seatInfo.getPeoplenum()}</td>
                    <td>${seatInfo.getDescription()}</td>
                    <td>
                      <button type="button" class="btn btn-default btn-sm editSeat" data-toggle="modal" data-target="#editSeatModal">
                        <span class="glyphicon glyphicon-pencil"></span>
                      </button>
                      <div id="editSeatModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                        <div class="modal-dialog">
                          <div class="modal-content">
                          <form class="form-updateSeat" role="form" action="${rc.contextPath}/web/seat/updateSeat" method="get">
                            <div class="modal-header">
                              <input id="seatId" name="seatId" class="form-control" type="hidden">
                              <input id="userId" name="userId" class="form-control editSeatModal" type="hidden">
                              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                              <h4 class="modal-title" id="myModalLabel">Edit table</h4>
                            </div>
                            <div class="modal-body">
                              <div>
                                <p>Seat No:</p>
                                <input type="text" id="seatNo" name="seatNo" class="form-control" placeholder="1" required autofocus>
                                <p>Description:</p>
                                <input type="text" id="description" name="description" class="form-control" placeholder="you can say sth about this table!" required autofocus>
                                <p>Choose people num</p>                
                                <div>
                                  <select name="peopleNum" class="form-control">
                                    <option value="2">2 people</option>
                                    <option value="4">4 people</option>
                                    <option value="6">6 people</option>
                                    <option value="8">8 people</option>
                                    <option value="10">10 people</option>
                                  </select>                            
                                </div>
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
                      <button type="button" class="deleteSeat btn btn-default btn-sm">
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
