<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="smallHeaderTemplate">
	<tiles:putAttribute name="body">
		<div class="container content">


			<div class="row">
				<div class="col-md-7 ">
					<div class="shadow-wrapper margin-bottom-60">
						<div class="tag-box tag-box-v1 box-shadow shadow-effect-2">
							<h2>Congratulations</h2>
							<p>You have successfully purchased the following package.
								<ul>
									<li>item description: ${order.pricingName }</li>
									<li>total items: ${order.nrOfPricingUnits }</li>
									<li>order value: ${order.orderValue }</li>
									<li>order id: ${order.longPartId }</li>
								</ul>
							</p>
							<p>
								You can go to the Lines index to use the purchased items one by one
								or you can choose to automatically extend all lines with the purchased items
							</p>
							<p>
								<a class="btn btn-success rounded"
									href="/Line/index/"><i
									class="icon-settings"></i> go to the index page</a>
								<a class="btn btn-success rounded"
									href="/Pay/useAllItems/${order.longPartId }"><i
									class="icon-settings"></i> use all items</a>
							</p>
						</div>
					</div>

				</div>

			</div>
			<!--/row-->
		</div>
		<!--/container-->
	</tiles:putAttribute>
</tiles:insertDefinition>


