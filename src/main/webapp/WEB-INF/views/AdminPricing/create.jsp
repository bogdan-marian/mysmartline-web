<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tiles:insertDefinition name="adminTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left">Create PricingDefinition</h1>
			</div>
		</div>
		<!-- /breadcrumbs -->

		<div class="container content">
			<div class="row">
			
				<!-- Basic Form -->
				<div class="panel panel-green margin-bottom-40">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="fa fa-tasks"></i>New pricing definition
						</h3>
					</div>
					<div class="panel-body">
						<form:form class="margin-bottom-40" role="form" method="post" modelAttribute="pricingDefinition" action="createPost">
							
							<div class="form-group">
								<label for="valid">isValid</label> 
								<form:checkbox path="valid" />
							</div>
							<div class="form-group">
								<label for="pricingName">pricingName</label> 
								<form:input path="pricingName" class="form-control" placeholder="Enter pricing definition name"/>
							</div>
							<div class="form-group">
								<label for="nrOfLines">nrOfLines</label> 
								<form:input path="nrOfLines" class="form-control" type="number"/>
							</div>
							
							<div class="form-group">
								<label for="priceInEuro">priceInEuro</label> 
								<form:input path="priceInEuro" class="form-control" placeholder="Enter integer in euro"/>
							</div>
							<div class="form-group">
								<label for="monthsValid">monthsValid</label> 
								<form:input path="monthsValid" class="form-control" placeholder="Enter 1 or 12 monts"/>
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