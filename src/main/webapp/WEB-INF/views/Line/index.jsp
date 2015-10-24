<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tiles:insertDefinition name="introjsTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left"><spring:message code="line.index.my-lines" /></h1>
			</div>
		</div>
		<!-- /breadcrumbs -->

		<div class="container content">
			<div class="row">
				<!--Striped Rows-->
				<div class="panel panel-green margin-bottom-40">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="fa fa-edit"></i> <spring:message code="line.index.lines" />
						</h3>
					</div>
					<table class="table table-striped" id="linesTable">
						<thead>
							<tr>
								<th></th>
								<th><spring:message code="line.index.Line-name" /></th>
								<th><spring:message code="linie.index.Short-label" /></th>
								<th>Valid until</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="line" items="${line }">
								<tr>
									<td></td>
									<td><c:out value="${line.lineName }"></c:out></td>
									<td><c:out value="${line.lineLabel }"></c:out></td>
									<td><c:out value="${line.validUntil }"></c:out></td>
									<td><p class="pull-right">
										<a class="btn btn-success rounded webRegistration"
										href="/Line/webRegistrationPage/${line.id }"><i class="fa fa-pencil"></i><spring:message code="line.index.webRegistrationPage" /></a>
										<a class="btn btn-success rounded "
										href="/Line/details/${line.id }"><i class="fa fa-pencil"></i><spring:message code="linie.index.Notification-panel" /></a>
										<a class="btn btn-success rounded "
										href="/Line/managePanel/${line.id }"><i
											class="fa fa-cogs"></i><spring:message code="linie.index.Manage" /></a></p></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row pull-right">
				<a class="btn-u rounded createButon" href="/Line/create"><spring:message code = "linie.index.Create-line"></spring:message></a>
			</div>
			<!--/row-->
		</div>
		<!--/container-->
	</tiles:putAttribute>
	<tiles:putAttribute name="scripts">
		<script type="text/javascript">
			jQuery(document).ready(function() {
				setCookie("pageNameVal", "lines", 30);
				
				var rows = document.getElementById("linesTable").getElementsByTagName("tr").length;
				rows = rows - 1;
				setCookie("linesCount", rows, 30);
			});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>