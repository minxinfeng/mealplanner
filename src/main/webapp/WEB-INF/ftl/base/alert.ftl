<#-- alert  -->
<div class='alert-information'>
    <#comment>
    FAQ:
    Q: How to use it?
    A:
        Message message = new Message();
        message.warning("just a test");
        // add to model
        model.addAttribute("messages", message.getMessages());

        // or use the attribute
        redirectAttributes.addFlashAttribute("messages", message.getMessages());

    </#comment>
    <#if messages??>
        <#list messages as message>
            <div class="alert alert-${ message.level }">
                <a class="close">x</a> <span>${ message.message }</span>
            </div>
        </#list>
    </#if>
</div>

<script type="text/javascript">
$.extend({
	alert:function(message, type){
		var alertStyle = "alert-success";
		switch(type){
			case 'danger':
				alertStyle = "alert-danger";
				break;
			case 'info':
				alertStyle = "alert-info";
				break;
			case 'warning':
				alertStyle = "alert-warning";
				break;
			case 'success':
				alertStyle = "alert-success";
				break;
			default:
				break;
		}
		var content = '<div class="alert  ' + alertStyle + '">';
         	content +=  '<a class="close">x</a> <span>' + message  + '</span>';
          	content +=  '</div>';
		$(".alert-information").append(content);
		$(".alert").show();
	},
	
	closeAlert:function(){
	    $(".alert").removeClass("alert-success").removeClass("alert-danger").removeClass("alert-info").removeClass("alert-warning");
		$(".alert span").text("");
		$(".alert").hide();
	}
});

$(document).ready(function(){
	//close
	$(".alert .close").live("click",function(){
		$.closeAlert();
	});
});
</script>