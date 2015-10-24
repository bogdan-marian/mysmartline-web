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
				<div class="alert alert-info fade in">
					<strong><spring:message code="pricing.index.stillInAlpha" /></strong>  <spring:message code="pricing.index.youWillNotBeCharged" />
				</div>
			</div>


			<div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="pricing hover-effect">
						<div class="pricing-head">
							<h3>
								10 &#x20AC <span><spring:message code="pricing.index.perLinePerMonths" /></span>
							</h3>
							<h4>
								<span><spring:message code="pricing.index.or" /> 110 &#x20AC <spring:message code="pricing.index.perLinePerYear" /></span>
							</h4>
						</div>
						<ul class="pricing-content list-unstyled">
							<li><i class="icon-users"></i> <spring:message code="pricing.index.unlimitedNumbers" /></li>
							<li><i class="icon-info"></i> <spring:message code="pricing.index.unlimitedServicePoints" /></li>
							<li><i class="icon-clock"></i> <spring:message code="pricing.index.unlimitedDevices" /></li>
						</ul>
						<div class="pricing-footer">
							<p>
								<a class="btn-u fa fa-thumbs-up  rounded"
									href="/Pricing/oneLine"><spring:message code="pricing.index.getStarted" /></a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="pricing hover-effect">
						<div class="pricing-head">
							<h3>
								40 &#x20AC <span><spring:message code="pricing.index.per5LinesPerMonth" /></span>
							</h3>
							<h4>
								<span>or 440 &#x20AC <spring:message code="pricing.index.per5LinesPerYear" /></span>
							</h4>
						</div>
						<ul class="pricing-content list-unstyled">
							<li><i class="icon-users"></i> <spring:message code="pricing.index.unlimitedNumbers" /></li>
							<li><i class="icon-info"></i> <spring:message code="pricing.index.unlimitedServicePoints" /></li>
							<li><i class="icon-clock"></i> <spring:message code="pricing.index.unlimitedDevices" /></li>
						</ul>
						<div class="pricing-footer">
							<p>

								<form:form role="form" method="get" action="/Pricing/fiveLines">


									<button type="submit" class="btn-u fa fa-thumbs-up  rounded">
										<spring:message code="pricing.index.getStarted" /></button>
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

