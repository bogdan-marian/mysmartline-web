<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="smallHeaderTemplate">
	<tiles:putAttribute name="body">
		<div class="container content">


			<div class="row">
				<div class="col-md-5 col-sm-6">
					<div class="shadow-wrapper margin-bottom-60">
						<div class="tag-box tag-box-v1 box-shadow shadow-effect-2">
							<h2>Error</h2>
							<p>Something went wrong. This application is still in alpha.
								Please do not let this discourage you from using it. Thank you</p>
						</div>
					</div>

				</div>

			</div>
			<!--/row-->
		</div>
		<!--/container-->
	</tiles:putAttribute>
</tiles:insertDefinition>

