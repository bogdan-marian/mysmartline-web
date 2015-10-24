<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="pricingTemplate">
	<tiles:putAttribute name="body">

		<!--=== Content Part ===-->
		<div class="container content">
			<div class="row">


				<!-- Begin Content -->
				<div class="col-md-6">

					<form:form role="form" method="post" class="sky-form label-rounded"
						action="/" modelAttribute="orderModel">
						<header>form header</header>
						
						<section>
							<form:input path="nrOfPricingUnits" type="range" 
							min="1" max="50"/>
							Computed value <span id="myResult">1</span>
						</section>
					</form:form>

				</div>
				<!-- End Content -->

			</div>
		</div>
		<!--/container-->
		<!--=== End Content Part ===-->

	</tiles:putAttribute>
</tiles:insertDefinition>