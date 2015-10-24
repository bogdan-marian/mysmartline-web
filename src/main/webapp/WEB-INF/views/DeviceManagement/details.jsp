<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left">Details for: ${vipDevice.userFrendlyName }</h1>
			</div>
		</div>

		<div class="container content">
			<div class="row">
				<div class="shadow-wrapper ">
					<div class="tag-box tag-box-v1 box-shadow shadow-effect-2">
						<h2>Device management options</h2>
						<p>
							<a class="btn-u rounded"
								href="../renameByUser/${vipDevice.id.id }"><i
								class="fa fa-edit"></i> Rename</a> <a
								class="btn-u btn-u-red rounded"
								href="../delete/${vipDevice.id.id }"><i
								class="fa fa-exclamation-triangle"></i> Delete</a>
						</p>
					</div>
				</div>
			</div>
			<!--/row-->
		</div>
		<!--/container-->
	</tiles:putAttribute>
</tiles:insertDefinition>