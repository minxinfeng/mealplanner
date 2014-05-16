<html>
<head>
  <title>Meal Planner</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, charset=utf-8">
    <!-- Bootstrap -->
    <#include "/base/base.ftl">
    <script type="text/javascript">
    $(document).ready(function(){
	    $(".orderAction").click(function(){
	    	var userId = $.cookie("rest_userid");
	    	var orderId = $(this).attr("id").split('_').pop();
	    	var classes = $(this).attr('class').split(' ');
	    	var btnStyle = classes[3];
	    	if(btnStyle == "btn-success"){
	    		$(this).removeClass(btnStyle);
	    		$(this).addClass("btn-warning");
	    		$(this).html("cancle");	    		
	    		$.ajax({
	    			type:"GET",
	    			url:"${rc.contextPath}/web/order/confirmByRest",
	    			data:{"orderId":orderId,"userId":userId},
	    			success:function(){	    				
	    			}
	    		});
	    	}else if(btnStyle == "btn-warning"){
	    		$(this).removeClass(btnStyle);
			    $(this).addClass("btn-error");
			    $(this).html("done");
	    		$.ajax({
	    			type:"GET",
	    			url:"${rc.contextPath}/web/order/cancleByRest",
	    			data:{"orderId":orderId,"userId":userId},
	    			success:function(){	    				
	    			}
	    		});
	    	}
	    })
    })
    </script>
</head>
<body>
    <#include "/base/header.ftl">
     <div class="container theme-showcase">
        <div class="page-header">
          <h1>Orders</h1>
        </div>

        <div class="row">
        	<div class="col-sm-12">
              <div class="panel panel-default">
              <!-- Default panel contents -->
              <div class="panel-heading">                              
              </div>
              <div class="panel-body">
              <table id="orderTable" class="table">
                <thead>
                  <tr>
                    <th>#</th>
                    <th>Order No</th>
                    <th>People num</th>
                    <th>Menus</th>
                    <th>phone num</th>
                    <th>meal time</th>
                    <th>Action</th>
                  </tr>
                </thead>
                <tbody>
                <#assign count = 0/>
                <#list orderDetails as orderDetail>
                <#assign count = count + 1/>
                  <tr id="${orderDetail.getOrderInfo().getOrderid()}" class="orderItem">
                    <td>${count}</td>
                    <td>No ${orderDetail.getOrderInfo().getOrderid()}</td>
                    <td>${orderDetail.getOrderInfo().getActualpeoplenum()} people</td>
                    <td>
                    	<#assign menuCount = 1/>
                    	<#list orderDetail.getMenuInfos() as menuInfo>
                    	${menuCount}.${menuInfo.getMenuname()}
                    	<#assign menuCount = menuCount + 1/>
                    	<br>
                    	</#list>
                    </td>
                    <td>Tel: ${orderDetail.getOrderInfo().getContactinfo()}</td>
                    <td>${orderDetail.getOrderInfo().getMealtime()?string("yyyy-MM-dd HH:mm")}</td>
                    <td>                        
                      <#if orderDetail.getOrderInfo().getStatus() == 0>
                      <button id="orderAction_${orderDetail.getOrderInfo().getOrderid()}" type="button" class="orderAction btn btn-sm btn-success">
                        Accept
                      </button>
                      <#elseif orderDetail.getOrderInfo().getStatus() == 2>
                      <button id="orderAction_${orderDetail.getOrderInfo().getOrderid()}" type="button" class="orderAction btn btn-sm btn-warning">
                        cancle
                      </button>
                      <#else>
                      <button id="orderAction_${orderDetail.getOrderInfo().getOrderid()}" type="button" class="orderAction btn btn-sm btn-default">
                        done
                      </button>
                      </#if>
                    </td>
                  </tr>
                  </#list>
                </tbody>
              </table>
              </div>              
              </div>
        	</div>
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