<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- <spring:message code="" /> --%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">

		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left"><spring:message code="dashboard.overview.dashboardOverview" /></h1>
			</div>
		</div>
		<!-- /breadcrumbs -->

		<div class="container content">
			<div class="row">
				<div class="col-md-4">
					<div class="bg-light">
						<h4>
							<i class="fa fa-align-justify"></i><spring:message code="dashboard.overview.account" />
						</h4>
						<p>
							<ul>
								<li><spring:message code="dashboard.overview.nume" />: ${nickName }</li>
								<li><spring:message code="dashboard.overview.email" />: ${email }</li>
							</ul>
						</p>
					</div>
				</div>
				<div class="col-md-4">
					<div class="bg-light">
						<h4>
							<i class="fa fa-align-justify"></i><spring:message code="dashboard.overview.emails" />
						</h4>
						<p><spring:message code="dashboard.overview.totalSent" /> = ${totalEmails }</p>
					</div>
				</div>
				<div class="col-md-4">
					<div class="bg-light">
						<h4>
							<i class="fa fa-align-justify"></i><spring:message code="dashboard.overview.clients" />
						</h4>
						<p><spring:message code="dashboard.overview.totalServed" /> = ??</p>
					</div>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="bg-light">
						<h4>
							<i class="fa fa-align-justify"></i><spring:message code="dashboard.overview.activationItems" />
						</h4>
						<p><spring:message code="dashboard.overview.totalActivationItems" /> = ${nrOfItems }</p>
						<p><spring:message code="dashboard.overview.theseItemsAreUsedWhen" /></p>
					</div>
				</div>
			</div>
			<!--/row-->
		</div>
		<!--/container-->
	</tiles:putAttribute>
</tiles:insertDefinition>