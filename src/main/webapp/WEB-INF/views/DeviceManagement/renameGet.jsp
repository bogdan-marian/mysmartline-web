<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="smallHeaderTemplate">
	<tiles:putAttribute name="body">
		

		<div class="container content">
			<div class="row">
				<div class="shadow-wrapper ">
					<div class="tag-box tag-box-v1 box-shadow shadow-effect-2">
						<h2>Activation was successful</h2>
						<p>Wee recommend that you assign a frierndly name to this device
						and then return to the device to finish the device configuration</p>
					</div>
				</div>
			</div>
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
							modelAttribute="device" action="../renamePost">
							<form:hidden path="longPartId" />
							<div class="form-group">
								<label for="userFrendlyName">friendly name</label>
								<form:input path="userFrendlyName" class="form-control"
									placeholder="client console 1" />
							</div>
							<button type="submit" class="btn-u pull-right rounded">Save</button>
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