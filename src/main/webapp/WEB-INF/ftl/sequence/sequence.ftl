<html>
<head>
  <title>Meal Planner</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, charset=utf-8">
    <!-- Bootstrap -->
    <#include "/base/base.ftl">
</head>
<body>
	<#include "/base/header.ftl">
 
     <div class="container theme-showcase">
        <div class="page-header">
          <h1>Sequence</h1>
        </div>

        <div class="row">
        <section id="wrapper">		
		  <article id="dragSeq">
		    <p>Drag Sequence item to restautrant to reserved /p>
		    <div id="bin"></div>
		    <ul>		    
		    <#assign count = 1>
		    <#list sequenceDetailForRests as sequenceDetailForRest>
		    	<li><a id="${sequenceDetailForRest.getSequenceInfo().getSeqid()}" href="#">${count} user: ${sequenceDetailForRest.getUserInfo().getUsername()} ${sequenceDetailForRest.getSequenceInfo().getPeoplenum()} people</a></li>
		    	<#assign count = count + 1>
		    </#list>
		    </ul>
		  </article>
		  <script type="text/javascript">
		  var confirmSequence;

		  var actionText = ['Served!', 'Cancles'];
		  var yum = document.createElement('p');
		  var msie = /*@cc_on!@*/0;
		  yum.style.opacity = 1;
		
		  var links = document.querySelectorAll('li > a'), el = null;
		  for (var i = 0; i < links.length; i++) {
		    el = links[i];
		  
		    el.setAttribute('draggable', 'true');
		  
		    addEvent(el, 'dragstart', function (e) {
		      e.dataTransfer.effectAllowed = 'copy'; // only dropEffect='copy' will be dropable
		      e.dataTransfer.setData('Text', this.id); // required otherwise doesn't work
		    });
		  }
		
		  var bin = document.querySelector('#bin');
		
		  addEvent(bin, 'dragover', function (e) {
		    if (e.preventDefault) e.preventDefault(); // allows us to drop
		    this.className = 'over';
		    e.dataTransfer.dropEffect = 'copy';
		    return false;
		  });
		
		  // to get IE to work
		  addEvent(bin, 'dragenter', function (e) {
		    this.className = 'over';
		    return false;
		  });
		
		  addEvent(bin, 'dragleave', function () {
		    this.className = '';
		  });
		
		  addEvent(bin, 'drop', function (e) {
		    if (e.stopPropagation) e.stopPropagation(); // stops the browser from redirecting...why???
		
		    var el = document.getElementById(e.dataTransfer.getData('Text'));
		    
		    el.parentNode.removeChild(el);
		    confirmSequence(e.dataTransfer.getData('Text'));
		
		    // stupid nom text + fade effect
		    bin.className = '';
		    yum.innerHTML = actionText[0];
		
		    var y = yum.cloneNode(true);
		    bin.appendChild(y);
		
		    setTimeout(function () {
		      var t = setInterval(function () {
		        if (y.style.opacity <= 0) {
		          if (msie) { // don't bother with the animation
		            y.style.display = 'none';
		          }
		          clearInterval(t); 
		        } else {
		          y.style.opacity -= 0.1;
		        }
		      }, 50);
		    }, 250);
		
		    return false;
		  });
		
		  
		  $(document).ready(function(){
		  	confirmSequence = function changeStatus(id){
		  		$.ajax({
		  			type:"GET",
		          	url:"${rc.contextPath}/web/sequence/acceptSquenceBySeqId",
		          	data:{"seqId":id},
		          	success: function(msg){
		            }
		  		});//ajax
		  	}
		  });

		</script>
		</section>
        </div>


      <hr>

      <footer>
        <p class="text-center">&copy; ThreeOne 2014</p>
      </footer>
     </div>      
</body>
</html>