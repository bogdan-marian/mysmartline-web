<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<tiles:insertDefinition name="pricingTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left">
					<spring:message code="pricing.index.simpleAndFlexible" />
				</h1>
			</div>
		</div>
		<!-- /breadcrumbs -->
		<div class="container content">


			<div class="row">
				<div class="col-md-3 "></div>
				<div class="col-md-6 col-sm-6">
					<div class="pricing hover-effect">
						<div class="pricing-head">
							<h3>
								<spring:message code="pricing.oneLine" />
							</h3>
						</div>
						
						<ul class="pricing-content list-unstyled">
							<li><i class="icon-users"></i> <spring:message code="pricing.index.unlimitedNumbers" /></li>
							<li><i class="icon-info"></i> <spring:message code="pricing.index.unlimitedServicePoints" /></li>
							<li><i class="icon-clock"></i> <spring:message code="pricing.index.unlimitedDevices" /></li>
						</ul>
						<div class="pricing-footer">
							<p>
								<a class="btn-u fa fa-thumbs-up  rounded" href="/Pay/oneLineOneMonth">10 &#x20AC / <spring:message code="pricing.month" /></a>
								<a class="btn-u fa fa-thumbs-up  rounded" href="/Pay/oneLineOneYear">110 &#x20AC / <spring:message code="pricing.year" /></a>
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

