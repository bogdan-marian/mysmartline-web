<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="smallHeaderTemplate">
	<tiles:putAttribute name="body">
		<div class="container content">


			<div class="row">
				<div class="col-md-7 ">
					<div class="shadow-wrapper margin-bottom-60">
						<div class="tag-box tag-box-v1 box-shadow shadow-effect-2">
							<h2>Success</h2>
							<p>You have successfully exteded the expiration date for 
								the existing lines
							</p>
							<p>
								<a class="btn btn-success rounded"
									href="/Line/index/"><i
									class="icon-settings"></i> go to the index page</a>
							</p>
						</div>
					</div>

				</div>

			</div>
			<!--/row-->
		</div>
		<!--/container-->
	</tiles:putAttribute>
</tiles:insertDefinition>


