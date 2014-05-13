
<div id="seatInfoModal_${seatInfo.getSeatid()}_${dateOffset}" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="seatInfoModalLabel" aria-hidden="true" style="display: none;">
	  	<div class="modal-dialog">
			<div class="modal-content">
			<form class="form-seatModal" role="form" action="${rc.contextPath}/web/seat/changeSeatStatus" method="post">
				<div class="modal-header">
				    <input id="submitClockState" name="submitClockState" class="form-control" type="hidden">
				    <input id="seatId" name="seatId" class="form-control" type="hidden">
				    <input id="userId" name="userId" class="form-control" type="hidden">
				    <input id="dateDay" name="dateDay" class="form-control" type="hidden">
				    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
				     <h4 class="modal-title" id="seatInfoModalLabel">Choose Time</h4>
				</div>
				<div class="modal-body">
				    <div class="bs-docs-grid">					    
						<div class="row show-grid">
							<div class="col-md-3">
								<button id="10_${seatInfo.getSeatid()}_${dateOffset}" type="button" class="btn chooseTimeBtn" data-toggle="tooltip" data-placement="bottom" >10:00</button>
							</div>
							<div class="col-md-3">
								<button id="11_${seatInfo.getSeatid()}_${dateOffset}" type="button" class="btn chooseTimeBtn" data-toggle="tooltip" data-placement="bottom" >11:00</button>
							</div>
							<div class="col-md-3">
								<button id="12_${seatInfo.getSeatid()}_${dateOffset}" type="button" class="btn chooseTimeBtn" data-toggle="tooltip" data-placement="bottom" >12:00</button>
							</div>
							<div class="col-md-3">
								<button id="13_${seatInfo.getSeatid()}_${dateOffset}" type="button" class="btn chooseTimeBtn" data-toggle="tooltip" data-placement="bottom" >13:00</button>
							</div>
						</div>
						<hr>
						<div class="row show-grid">
							<div class="col-md-3"> 
								<button id="14_${seatInfo.getSeatid()}_${dateOffset}" type="button" class="btn chooseTimeBtn" data-toggle="tooltip" data-placement="bottom" >14:00</button>
							</div>
							<div class="col-md-3">
								<button id="15_${seatInfo.getSeatid()}_${dateOffset}" type="button" class="btn chooseTimeBtn" data-toggle="tooltip" data-placement="bottom" >15:00</button>
							</div>
							<div class="col-md-3">
								<button id="16_${seatInfo.getSeatid()}_${dateOffset}" type="button" class="btn chooseTimeBtn" data-toggle="tooltip" data-placement="bottom" >16:00</button>
							</div>
							<div class="col-md-3">
								<button id="17_${seatInfo.getSeatid()}_${dateOffset}" type="button" class="btn chooseTimeBtn" data-toggle="tooltip" data-placement="bottom" >17:00</button>
							</div>
						</div>
						<hr>
						<div class="row show-grid">
							<div class="col-md-3">
								<button id="18_${seatInfo.getSeatid()}_${dateOffset}" type="button" class="btn chooseTimeBtn" data-toggle="tooltip" data-placement="bottom" >18:00</button>
							</div>
							<div class="col-md-3">
								<button id="19_${seatInfo.getSeatid()}_${dateOffset}" type="button" class="btn chooseTimeBtn" data-toggle="tooltip" data-placement="bottom" >19:00</button>
							</div>
							<div class="col-md-3">
								 <button id="20_${seatInfo.getSeatid()}_${dateOffset}" type="button" class="btn chooseTimeBtn" data-toggle="tooltip" data-placement="bottom" >20:00</button>
							</div>
							<div class="col-md-3">
								<button id="21_${seatInfo.getSeatid()}_${dateOffset}" type="button" class="btn chooseTimeBtn" data-toggle="tooltip" data-placement="bottom" >21:00</button>
							</div>
						</div>
						<hr>
						<div class="row show-grid">
							<div class="col-md-3">
								<button id="22_${seatInfo.getSeatid()}_${dateOffset}" type="button" class="btn chooseTimeBtn" data-toggle="tooltip" data-placement="bottom" >22:00</button>
							</div>										      
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
	</div><!-- /.modal -->

