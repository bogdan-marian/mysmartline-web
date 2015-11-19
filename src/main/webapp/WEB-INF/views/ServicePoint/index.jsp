<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tiles:insertDefinition name="introjsTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left"><spring:message code="servicepoint.index.myservice" /></h1>
			</div>
		</div>
		<!-- /breadcrumbs -->

		<div class="container content">
			<div class="row">
				<!--Striped Rows-->
				<div class="panel panel-green margin-bottom-40">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="fa fa-edit"></i> <spring:message code="servicepoint.index.servicepoints" />
						</h3>
					</div>
					<table class="table table-striped" id="servicePointsTable">
						<thead>
							<tr>
								<th></th>
								<th><spring:message code="servicepoint.index.shortname" /></th>
								<th><spring:message code="servicepoint.index.description" /></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="servicePoint" items="${servicePoint }">
								<tr>
									<td></td>
									<td><c:out value="${servicePoint.shortName }"></c:out></td>
									<td><c:out value="${servicePoint.description }"></c:out></td>
									<td>
										<p class="pull-right">
											<a class="btn btn-success rounded"
												href="/ServicePoint/notificationPanel/${servicePoint.id }"><i
												class="fa fa-pencil"></i>
												<spring:message code="linie.index.Notification-panel" /></a>
											<a
												class="btn btn-success rounded "
												href="/ServicePoint/managePanel/${servicePoint.id }"><i
												class="fa fa-cogs"></i>
												<spring:message code="linie.index.Manage" /></a>
										</p></td>
										
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row pull-right">
				<a class="btn-u rounded createButon" href="./create"><spring:message code="servicepoint.index.createservice" /></a>
			</div>
			<!--/row-->
		</div>
		<!--/container-->
	</tiles:putAttribute>
	<tiles:putAttribute name="scripts">
		<script type="text/javascript">
			jQuery(document).ready(function() {
				setCookie("pageNameVal", "servicePoints", 30);
				
				var rows = document.getElementById("servicePointsTable").getElementsByTagName("tr").length;
				setCookie("servicePointRows", rows, 30);
			});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>