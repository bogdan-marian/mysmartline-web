<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- <spring:message code="" /> :)--%>
<tiles:insertDefinition name="introjsTemplate">
	<tiles:putAttribute name="body">
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left"><spring:message code="home.message1" /></h1>
			</div>
		</div>
		<!-- /breadcrumbs -->

		<div class="container content">
			<div class="row">
				<div class="col-md-5">
					<div class="servive-block servive-block-default">
                            <i class="icon-custom icon-color-dark rounded-x fa fa-lightbulb-o"></i>
                            <p><img class="qrcode" alt="qrcode"
									src="http://chart.apis.google.com/chart?chs=150x150&cht=qr&chl=${qrText }&choe=UTF-8"></p>
                            <p><spring:message code="line.webRegistration.pleaseScan" /></p> 
                                                
                     </div>
				</div>
				<div class="col-md-7">
					<div class="row">
                        <h2 class="heading-sm">
                            <i class="icon-custom rounded-x icon-color-dark fa fa-bullhorn"></i> 
                            <span>Useful resources</span>
                        </h2>
                        <p>
                        	<a class="btn-u rounded simulateClients"
								href="/LineRegistration/readClientDetails/${lineId }"><i
								class="fa fa-mobile"></i> <spring:message code="linie.manage.Simulate-clients" /></a>
							<a class="btn btn-success rounded notificationPanel"
								href="/Line/details/${lineId }"><i 
								class="fa fa-pencil"></i><spring:message code="linie.index.Notification-panel" /></a> 
							<a class="androidLink"
								href="https://play.google.com/store/apps/details?id=eu.mysmartline.appv3">
								<img alt="Android app on Google Play"
								src="https://developer.android.com/images/brand/en_app_rgb_wo_60.png" />
							</a>

						</p>
                    </div>
                    <div class="row">
                        <h2 class="heading-sm">
                            <i class="icon-custom rounded-x icon-color-dark fa fa-bullhorn"></i> 
                            <span>Registration device: </span>
                        </h2>
						<p>
							Install mysmartline on a tablet and allow to you clients to use
							it as a registration device. All you need to do is set the app
							into the registration mode and then mount it in a place where
							would be obvious for your clients that you would like for them to
							use it.
						</p>
					</div>
					 <div class="row">
                        <h2 class="heading-sm">
                            <i class="icon-custom rounded-x icon-color-dark fa fa-bullhorn"></i> 
                            <span>Display panel: </span>
                        </h2>
						<p>
							If you install mysmartline on a android device and you connect it
							to a monitor then you can set the app in display mode and all the
							status from your lines will instantly be updated to all the
							displays that are connected to any of you devices.
						</p>
					</div>
					<div class="row">
						<h2 class="heading-sm">
							<i class="icon-custom rounded-x icon-color-dark fa fa-bullhorn"></i>
							<span>Recommended apps for scanning QR codes: </span>
						</h2>
						<p>There are hundreds of good apps that can scan QR
							codes. Wee recommend this ones but any of the available ones for
							you device will work just fine.</p>
						<p><a class="btn-u rounded scanApp"
								href="https://play.google.com/store/apps/details?id=com.google.zxing.client.android"><i
								class="fa fa-mobile"></i> Barcode Scanner</a></p>
					</div>
				</div>
				
			</div>
			<!--/row-->
			
		</div>
		<!--/container-->
	</tiles:putAttribute>
	<tiles:putAttribute name="scripts">
		<script type="text/javascript">
			jQuery(document).ready(function() {
				setCookie("pageNameVal", "webRegistrationPage", 30);
			});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>
