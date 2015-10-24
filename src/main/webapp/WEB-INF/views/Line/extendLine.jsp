<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left">Extend validation date</h1>
			</div>
		</div>
		<!-- /breadcrumbs -->
		

		<div class="container content">
			<!-- Basic Form -->
				<div class="panel panel-green margin-bottom-40">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="fa fa-tasks"></i>
							${lineName }
						</h3>
					</div>
					<div class="panel-body">
						<form:form class="margin-bottom-40" role="form" method="post"
							modelAttribute="extentionModel" action="../extendLinePost">
							<form:hidden path="lineId"/>
							<div class="margin-bottom-40">
								<label for="line.lineLabel">Select date</label>
								<form:select path="activationItemId" class="form-control">
									<form:options items="${activationItems}"></form:options>
								</form:select>
							</div>
							
							<div>
								<button type="submit" class="btn-u pull-right rounded">
									Extend line
								</button>
							</div>

						</form:form>
					</div>
				</div>
				<!-- End Basic Form -->
		
			
		</div>
		<!--/container-->
	</tiles:putAttribute>
</tiles:insertDefinition>