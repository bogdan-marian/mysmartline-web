<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left">${line.lineName } - ${line.lineLabel }</h1>
			</div>
		</div>

		<div class="container content">
			<div class="row">
				<div class="shadow-wrapper ">
					<div class="tag-box tag-box-v1 box-shadow shadow-effect-2">
						<h2><spring:message code="linie.manage.Publish" /></h2>
						<p><spring:message code="linie.manage.In-order-to" />
							<ul>
								<li><spring:message code="linie.manage.Install-app" /></li>
								<li><spring:message code="linie.manage.PublishQR" /></li>
								<li><spring:message code="linie.manage.Publishprint" /></li>
							</ul>
						</p>
						
					</div>
				</div>
			</div>
			<div class="row">
				<div class="shadow-wrapper ">
					<div class="tag-box tag-box-v1 box-shadow shadow-effect-2">
						<h2><spring:message code="linie.manage.Line-manage" /></h2>
						<p>
							<a class="btn-u rounded"
								href="/LineRegistration/readClientDetails/${line.longPartId }"><i
								class="fa fa-mobile"></i> <spring:message code="linie.manage.Simulate-clients" /></a>
								<a
								class="btn-u btn-u-blue rounded"
								href="../resetNumbers/${line.longPartId }"><i
								class="fa fa-refresh"></i> <spring:message code="linie.manage.Reset" /></a>
								<a
								class="btn-u rounded"
								href="../edit/${line.longPartId }"><i
								class="fa fa-wrench"></i> <spring:message code="linie.manage.Edit-line" /></a>
								<a
								class="btn-u rounded"
								href="../extendLine/${line.longPartId }"><i
								class="fa fa-calendar"></i> <spring:message code="linie.manage.exendValidationDate" /></a>
								 <a
								class="btn-u btn-u-red rounded"
								href="../archive/${line.longPartId }"><i
								class="fa fa-exclamation-triangle"></i> <spring:message code="linie.manage.Archive" /></a>
						</p>
					</div>
				</div>
			</div>
			<!--/row-->
		</div>
		<!--/container-->
	</tiles:putAttribute>
</tiles:insertDefinition>