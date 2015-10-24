<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="noHeaderTemplate">
	<tiles:putAttribute name="body">
		<div class="container content">


			<div class="row">
				<div class="col-md-5 col-sm-6">
					<div class="margin-bottom-60">
						<div class="tag-box tag-box-v1 ">
							<h2>${vLineName }</h2>
							<h1>- ${vClientNumber } -</h1>
							<p>
								
								
								You can convert this ticket into a
								virtual one by scanning the QR code<br /> 
								<img alt="qrcode"
									src="http://chart.apis.google.com/chart?chs=150x150&cht=qr&chl=${qrText }&choe=UTF-8">
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