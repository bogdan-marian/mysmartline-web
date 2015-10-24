<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<tiles:insertDefinition name="adminTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left">Admin home</h1>
			</div>
		</div>
		<!-- /breadcrumbs -->

		<div class="container content">
			<div class="row">
				Available tools:
				<ul>
					<li><a href="/Admin/usersPanel">Users panel</a> </li>
					<li><a href="/AdminPricing/index">Pricing index</a> </li>
					
				</ul>
			</div>
			<!--/row-->
		</div>
		<!--/container-->
	</tiles:putAttribute>
</tiles:insertDefinition>