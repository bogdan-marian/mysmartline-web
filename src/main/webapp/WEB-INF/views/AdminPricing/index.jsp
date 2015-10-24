<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="adminTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left">PricingDefinition index</h1>
			</div>
		</div>
		<!-- /breadcrumbs -->

		<div class="container content">
			<div class="row">

				<table class="table table-striped">
					<thead>
						<tr>
							<th></th>
							<th>pricingName</th>
							<th>nrOfLines</th>
							<th>priceInEuro</th>
							<th>monthsValid</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="definition" items="${definition }">
							<tr>
								<td></td>
								<td><c:out value="${definition.pricingName }"></c:out></td>
								<td><c:out value="${definition.nrOfLines }"></c:out></td>
								<td><c:out value="${definition.priceInEuro }"></c:out></td>
								<td><c:out value="${definition.monthsValid }"></c:out></td>
								<td><p class="pull-right">
										<a class="btn btn-success rounded "
											href="./edit/${definition.id.id }"><i class="fa fa-cogs"></i>
											Edit</a>
									</p></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!--/row-->
			<div class="row pull-right">
				<a class="btn-u rounded" href="./create">Create pricing definition </a>
			</div>
			<!--/row-->
		</div>
		<!--/container-->
	</tiles:putAttribute>
</tiles:insertDefinition>