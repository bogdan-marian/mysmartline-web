<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="smallHeaderTemplate">
	<tiles:putAttribute name="body">
		<div class="container content">


			<div class="row">
				<div class="col-md-5 col-sm-6">
					<div class="shadow-wrapper margin-bottom-60">
						<div class="tag-box tag-box-v1 box-shadow shadow-effect-2">
							<h2>Something went wrong</h2>
							<p>Wee are terribly sorry for this but the system was not
								able to add you to the line. Please try again by using the back
								button or contact the business owner directly. If you would like
								for help at improving the service please send a message to
								bogdan.oloeriu@gmail.com explaining what ware you doing when
								this error occurred</p>
						</div>
					</div>
				</div>

			</div>
			<!--/row-->
		</div>
		<!--/container-->
	</tiles:putAttribute>
</tiles:insertDefinition>

