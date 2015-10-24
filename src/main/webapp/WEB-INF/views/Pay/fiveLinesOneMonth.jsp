<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- <spring:message code="" /> --%>

<tiles:insertDefinition name="pricingTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left"><spring:message code="pricing.index.simpleAndFlexible" /></h1>
			</div>
		</div>
		<!-- /breadcrumbs -->
		<div class="container content">
			<div class="row">
				<div class="col-md-3 "></div>
				<div class="col-md-6 col-sm-6">
					<div class="pricing hover-effect">
						<div class="pricing-head">
							<h3><spring:message code="pay.oneLineOneMonth.adjustOrder" /></h3>
							<h4>
								<span><spring:message code="pay.fiveLinesOneMonth.fiveLinesForOneMonth" /> = ${orderModel.pricingValue } &#x20AC </span>
							</h4>
							<h4>
								<span><spring:message code="pay.oneLineOneMonth.stillInAlpha" /></span>
							</h4>

						</div>

						<ul class="pricing-content list-unstyled">
							<li><i class="icon-users"></i> <spring:message code="pay.fiveLinesOneMonth.nrOfLineGroups" /> <span
								id="totalUnits">1</span></li>
							<li><i class="icon-info"></i> <spring:message code="pay.oneLineOneMonth.orderValue" /> <span
								id="totalValue">${orderModel.pricingValue } </span> &#x20AC</li>
						</ul>

						<div class="pricing-footer">
							<p>
								<form:form role="form" method="post" modelAttribute="orderModel"
									action="/Pay/orderPost">
									<form:hidden path="pricingValue" />
									<form:hidden path="priceDefinitionId" />


									<div class="form-group">
										<form:label path="nrOfPricingUnits"><spring:message code="pay.oneLineOneMonth.slideToAdjustValues" /></form:label>
										<form:input path="nrOfPricingUnits" type="range" min="1"
											max="50" />
									</div>


									<button type="submit" class="btn-u fa fa-thumbs-up  rounded">
										<spring:message code="pay.oneLineOneMonth.placeOrder" /></button>
								</form:form>
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

