<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="smallHeaderTemplate">
	<tiles:putAttribute name="body">
		<div class="container content">


			<div class="row">
				<div class="col-md-5 col-sm-6">
					<div class="shadow-wrapper margin-bottom-60">
						<div class="tag-box tag-box-v1 box-shadow shadow-effect-2">
							<h2>Congratulations</h2>
							<p>
								You are a member of this line. Wee will send you important
								update messages about the status of this line. <b>Please
									visit your inbox for more details</b> You can always check the
								status of this line and evaluate the remaining waiting time by
								visiting your personal status page
							</p>
							<p>
								<a class="btn btn-success rounded"
									href="/ClientPanel/viewStatus/${registrationResultModel.longNotificationItemId }"><i
									class="icon-settings"></i> go to my status page</a>
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


