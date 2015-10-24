<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left">Rename device</h1>
			</div>
		</div>

		<div class="container content">
			
			<div class="row">
				<!-- Basic Form -->
				<div class="panel panel-green margin-bottom-40">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="fa fa-tasks"></i>Assign a friendly name to this device
						</h3>
					</div>
					<div class="panel-body">
						<form:form class="margin-bottom-40" role="form" method="post"
							modelAttribute="device" action="../renameByUserPost">
							<form:hidden path="longPartId" />
							<div class="form-group">
								<label for="userFrendlyName">friendly name</label>
								<form:input path="userFrendlyName" class="form-control"
									placeholder="client console 1" />
							</div>
							<button type="submit" class="btn-u pull-right rounded">Rename</button>
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