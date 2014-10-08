<div class="well well-sm" style="margin-bottom:10px;">
	<div class="row">
		<div class="col-md-12">
			<g:link event="editPurchaseOrder" class="btn btn-xs btn-block btn-primary">
				Editar orden #${purchaseOrder?.invoiceNumber} a ${purchaseOrder.provider}
			</g:link>
		</div>
	</div>

	<div class="row" style="padding:10px 0 5px 0;">
		<div class="col-md-6">
			<span class="glyphicon glyphicon-calendar"></span>
			<g:formatDate date="${purchaseOrder?.dutyDate}" formatName="custom.date.format"/>
		</div>
		<div class="col-md-6">
			<span class="glyphicon glyphicon-th"></span>
			${purchaseOrder?.typeOfPurchase}
		</div>
	</div>
</div>
