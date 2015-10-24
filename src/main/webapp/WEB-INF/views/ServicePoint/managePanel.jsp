<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left">${servicePoint.shortName } - ${servicePoint.description }</h1>
			</div>
		</div>

		<div class="container content">
			
			<div class="row">
				<div class="shadow-wrapper ">
					<div class="tag-box tag-box-v1 box-shadow shadow-effect-2">
						<p>
							<a class="btn-u btn-u-red rounded"
								href="/ServicePoint/archive/${servicePoint.longPartId }"><i
								class="fa fa-exclamation-triangle"></i> <spring:message
									code="linie.manage.Archive" /></a>
						</p>
					</div>
				</div>
			</div>
			<!--/row-->
		</div>
		<!--/container-->
	</tiles:putAttribute>
</tiles:insertDefinition>