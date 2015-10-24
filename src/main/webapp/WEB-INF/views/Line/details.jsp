<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left">${line.lineName }-${line.lineLabel }</h1>
			</div>
		</div>
		<!-- /breadcrumbs -->

		<div class="container content">
			<div class="row">
				<div class="bg-success rounded">
					<c:choose>
						<c:when test="${currentClient.active }">
						<spring:message code="linie.details.Curent-client" />: ${currentClient.clientNumber } (${currentClient.notificationValue })
					</c:when>

						<c:otherwise>
						<spring:message code="linie.details.There-is-no-client" />
					</c:otherwise>
					</c:choose>
				</div>
			</div>

			<div class="row">
				<!-- TODO: implement form for the next customer -->
				<br />
				<div class="panel panel-green margin-bottom-40">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="fa fa-tasks"></i><spring:message code="linie.details.Choose-next" />
						</h3>
					</div>
					<div class="panel-body">
						<form:form class="margin-bottom-40" role="form" method="post"
							modelAttribute="activateNextNumberModel"
							action="/Line/activateNextCustomer">
							<form:hidden path="lineId" />
							
							<div class="form-group">
								<label for="notificationId"><spring:message code="linie.details.Next-customer" /></label>
								<form:select path="notificationId" class="form-control">
									<form:options items="${clients }"  />
								</form:select>
							</div>
							<div class="form-group">
								<label for="servicePointId"><spring:message code="linie.details.Service-point" /></label>
								<form:select path="servicePointId" class="form-control">
									<form:options items="${newServicePoints }"   />
								</form:select>
							</div>
							<button type="submit" class="btn-u pull-right rounded"><spring:message code="linie.details.Notify-next" /></button>
						</form:form>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="headline">
					<h2><spring:message code="linie.details.Wating-clients" /></h2>
				</div>
			</div>
			<div class="row">
				<!-- current waiting clients -->

				<ul class="list-group">
					<c:forEach items="${clients }" var="client">
						<li class="list-group-item">${client.value}</li>
					</c:forEach>
				</ul>

			</div>

		</div>
		<!--/container-->
	</tiles:putAttribute>
</tiles:insertDefinition>