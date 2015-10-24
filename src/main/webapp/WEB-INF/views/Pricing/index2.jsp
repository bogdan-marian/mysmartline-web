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
					<a class="btn-u rounded" href="/Line/create"><spring:message code = "linie.index.Create-line"></spring:message></a>
				</div>
			</div>
		</div>
		<!--/container-->
	</tiles:putAttribute>
</tiles:insertDefinition>

