<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left">Edit line</h1>
			</div>
		</div>
		<!-- /breadcrumbs -->

		<div class="container content">
			<div class="row">
			
				<!-- Basic Form -->
				<div class="panel panel-green margin-bottom-40">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="fa fa-tasks"></i>Line details
						</h3>
					</div>
					<div class="panel-body">
						<form:form class="margin-bottom-40" role="form" method="post" modelAttribute="line" action="../editPost">
							<form:hidden path="longPartId"/>
							<div class="form-group">
								<label for="lineName">Line name</label> 
								<form:input path="lineName" class="form-control" placeholder="Enter line name"/>
							</div>
							<div class="form-group">
								<label for="lineLabel">Line label</label> 
								<form:input path="lineLabel" class="form-control" placeholder="short label"/>
							</div>
							
							<div class="form-group">
								<label for="notifyBefore">Notify before</label> 
								<form:input path="notifyBefore" class="form-control" placeholder="notify before number"/>
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