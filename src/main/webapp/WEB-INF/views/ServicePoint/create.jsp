<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left">Create new service point</h1>
			</div>
		</div>
		<!-- /breadcrumbs -->

		<div class="container content">
			<div class="row">
			
				<!-- Basic Form -->
				<div class="panel panel-green margin-bottom-40">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="fa fa-tasks"></i>New service point
						</h3>
					</div>
					<div class="panel-body">
						<form:form class="margin-bottom-40" role="form" method="post" modelAttribute="servicePoint" action="createPost">
							<div class="form-group">
								<label for="shortName">Short name</label> 
								<form:input path="shortName" class="form-control" placeholder="short name (ex: sp1)"/>
							</div>
							<div class="form-group">
								<label for="description">Description</label> 
								<form:input path="description" class="form-control" placeholder="Service point description"/>
							</div>
							<button type="submit" class="btn-u pull-right rounded">Create service point</button>
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