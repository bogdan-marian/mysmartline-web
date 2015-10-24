<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left"><spring:message code="linie.create.createnew" /></h1>
			</div>
		</div>
		<!-- /breadcrumbs -->

		<div class="container content">
			<div class="row">
			
				<!-- Basic Form -->
				<div class="panel panel-green margin-bottom-40">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="fa fa-tasks"></i><spring:message code="linie.create.newlinedetails" />
						</h3>
					</div>
					<div class="panel-body">
						<form:form class="margin-bottom-40" role="form" method="post" modelAttribute="line" action="createPost">
							<div class="form-group">
								<label for="lineName"><spring:message code="linie.create.linename" /></label> 
								<form:input path="lineName" class="form-control" placeholder="Enter line name"/>
							</div>
							<div class="form-group">
								<label for="lineLabel"><spring:message code="linie.create.linelabel" /></label> 
								<form:input path="lineLabel" class="form-control" placeholder="short label"/>
							</div>
							<button type="submit" class="btn-u pull-right rounded"><spring:message code="linie.create.createline" /></button>
						</form:form>
					</div>
				</div>
				<!-- End Basic Form -->

			</div>
			<!--/row-->
		</div>
		<!--/container-->
	</tiles:putAttribute>
</tiles:insertDefinition>