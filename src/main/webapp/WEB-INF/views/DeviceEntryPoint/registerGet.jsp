<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left">Create new device</h1>
			</div>
		</div>
		<!-- /breadcrumbs -->

		<div class="container content">
			<div class = "row">
			This form is intended for debug only.
			</div>
			<div class="row">
			
				<!-- Basic Form -->
				<div class="panel panel-green margin-bottom-40">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="fa fa-tasks"></i>New device details
						</h3>
					</div>
					<div class="panel-body">
						<form:form class="margin-bottom-40" role="form" method="post" modelAttribute="deviceRegistrationRequestModel" action="registerPost">
							<div class="form-group">
								<label for="gcmRegId">Device gcmRegId</label> 
								<form:input path="gcmRegId" class="form-control" placeholder="gcmRegId"/>
							</div>
							<button type="submit" class="btn-u pull-right rounded">Register device</button>
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