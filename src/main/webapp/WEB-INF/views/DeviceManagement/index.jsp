<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left"><spring:message code="devicemanagement.index.mydevices" /></h1>
			</div>
		</div>
		<!-- /breadcrumbs -->

		<div class="container content">
			<div class="row">
				<!--Striped Rows-->
				<div class="panel panel-green margin-bottom-40">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="fa fa-edit"></i> <spring:message code="devicemanagement.index.devices" />
						</h3>
					</div>
					<table class="table table-striped">
						<thead>
							<tr>
								<th></th>
								<th><spring:message code="devicemanagement.index.devicefriendly" /></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="device" items="${device }">
								<tr>
									<td></td>
									<td><c:out value="${device.userFrendlyName }"></c:out></td>
									<td><a class="btn btn-success btn-xs rounded pull-right"
										href="./details/${device.id.id }"><i class="fa fa-pencil"></i>Details</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			
			<!--/row-->
		</div>
		<!--/container-->
	</tiles:putAttribute>
</tiles:insertDefinition>