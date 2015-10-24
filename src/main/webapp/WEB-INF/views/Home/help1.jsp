<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%-- <%
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		String logInOutUrl = userService.createLoginURL("/");
%> --%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		
		<div class="breadcrumbs">
			<div class="container">
				<h1 class="pull-left">
					<spring:message code="home.message1" />
				</h1>
			</div>
		</div>
		<!-- /breadcrumbs -->

		<div class="container content">
			<div class="row row margin-bottom-10">
				<div class="col-md-9">
					<p><spring:message code="home.help1.isACloudBasedService" /></p>
					<p>${utubeUrl }</p>
				</div>
				<div class="col-md-3">
					<div class="list-group">
						<a href="/Home/help1/1" class="list-group-item ">
							<h4 class="list-group-item-heading"><spring:message code="home.help1.clip1" /></h4>
							<p class="list-group-item-text"><spring:message code="home.help1.clip1Description" /></p>
						</a> <a href="/Home/help1/2" class="list-group-item ">
							<h4 class="list-group-item-heading"><spring:message code="home.help1.clip2" /></h4>
							<p class="list-group-item-text"><spring:message code="home.help1.clip2Description" /></p>
						</a> 
						
						</a> 
						<a href="#descriptionService" class="list-group-item ">
							<h4 class="list-group-item-heading"><spring:message code="home.help1.descriptionService" /></h4>
							<p class="list-group-item-text"><spring:message code="home.help1.descriptionLongService" /></p>
						</a>
						<a href="#isnstruction1" class="list-group-item ">
							<h4 class="list-group-item-heading"><spring:message code="home.help1.instructions" /></h4>
							<p class="list-group-item-text"><spring:message code="home.help1.description3" /></p>
						</a> </a> <a href="#isnstruction2" class="list-group-item ">
							<h4 class="list-group-item-heading"><spring:message code="home.help1.instructions" /></h4>
							<p class="list-group-item-text"><spring:message code="home.help1.description4" /></p>
						</a>
					</div>
				</div>
			</div>
			<div class="row margin-bottom-10">

				<div class="bg-light">
					<!-- You can delete "bg-light" class. It is just to make background color -->
					<h4>
						<i class="fa fa-align-justify" id="descriptionService"></i><spring:message code="home.help1.doYouProvideVersion2" />
					</h4>
					<p>
						<spring:message code="home.help1.longDescriptionPartA" />
						<a class="btn-u rounded" href="${logInUrl }"> <spring:message code="home.help1.login" /></a>
						<spring:message code="home.help1.longDescriptionPartB" />
					</p>
				</div>
			</div>
			<div class="row margin-bottom-10">

				<div class="bg-light">
					<!-- You can delete "bg-light" class. It is just to make background color -->
					<h4>
						<i class="fa fa-align-justify" id="isnstruction1"></i><spring:message code="home.help1.description3" />
					</h4>
					<p><spring:message code="home.help1.antet1" /></p>
					<p>
					<%-- <a class="btn-u rounded" href=""><spring:message code="" /></a> --%>
					<ol>
						<li><spring:message code="home.help1.createOneLine" /><a class="btn-u rounded" href="/Line/index"><spring:message code="home.help1.lineIndex" /></a></li>
						<li><spring:message code="home.help1.createOneServicePoint" /><a class="btn-u rounded" href="/ServicePoint/index"><spring:message code="home.help1.serviceIndex" /></a></li>
						<li><spring:message code="home.help1.joinOur" /><a class="btn-u rounded" href="https://groups.google.com/forum/#!forum/my-smart-line-alpha"><spring:message code="home.help1.googleGroup" /></a></li>
						<li><spring:message code="home.help1.becomeATester" /><a class="btn-u rounded" href="https://play.google.com/apps/testing/eu.mysmartline.appv3"><spring:message code="home.help1.testerLink" /></a></li>
						<li><spring:message code="home.help1.installTheApp" /><a class="btn-u rounded" href="https://play.google.com/store/apps/details?id=eu.mysmartline.appv3"><spring:message code="home.help1.instalationUrl" /></a></li>
						<li><spring:message code="home.help1.startTheApp" /></li>
						<li><spring:message code="home.help1.configurePrinter" /></li>
						<li><spring:message code="home.help1.showAllLines" /></li>
						<li><spring:message code="home.help1.mountTheTablet" /></li>
						<li><spring:message code="home.help1.ifYouNeedHelp" /><a class="btn-u rounded" href="https://groups.google.com/forum/#!forum/my-smart-line-alpha"><spring:message code="home.help1.googleGroup" /></a></li>
					</ol>
					</p>
					<p>
						<spring:message code="home.Help1.done" />
					</p>
				</div>
			</div>
			<div class="row margin-bottom-10">

				<div class="bg-light">
					<!-- You can delete "bg-light" class. It is just to make background color -->
					<h4>
						<i class="fa fa-align-justify" id="isnstruction2"></i><spring:message code="home.help1.description4" />
					</h4>
					<p><spring:message code="home.help1.antet2" /></p>
					<p>
						<%-- <a class="btn-u rounded" href=""><spring:message code="" /></a> --%>
						<ol>
							<li><spring:message code="home.help1.createOneLine" /><a class="btn-u rounded" href="/Line/index"><spring:message code="home.help1.lineIndex" /></a></li>
							<li><spring:message code="home.help1.createOneServicePoint" /><a class="btn-u rounded" href="/ServicePoint/index"><spring:message code="home.help1.serviceIndex" /></a></li>
							<li><spring:message code="home.help1.joinOur" /><a class="btn-u rounded" href="https://groups.google.com/forum/#!forum/my-smart-line-alpha"><spring:message code="home.help1.googleGroup" /></a></li>
							<li><spring:message code="home.help1.becomeATester" /><a class="btn-u rounded" href="https://play.google.com/apps/testing/eu.mysmartline.appv3"><spring:message code="home.help1.testerLink" /></a></li>
							<li><spring:message code="home.help1.connectTheDeviceToAMonitor" /></li>
							<li><spring:message code="home.help1.installOnTheDevice" /><a class="btn-u rounded" href="https://play.google.com/store/apps/details?id=eu.mysmartline.appv3"><spring:message code="home.help1.instalationUrl" /></a></li>
							<li><spring:message code="home.help1.startTheAppOnTheDevice" /></li>
							<li><spring:message code="home.help1.showDisplayPanel" /></li>
						</ol>
					</p>
					<p>
						<spring:message code="home.Help1.done" />
					</p>
				</div>
			</div>
			<div class="row margin-bottom-10">

				<div class="bg-light">
					<!-- You can delete "bg-light" class. It is just to make background color -->
					<h4>
						<i class="fa fa-align-justify"></i><spring:message code="home.help1.pendingPatent" />
					</h4>
					<p><spring:message code="home.help1.isACloudBasedService" /></p>
					<p><spring:message code="home.help1.weeHaveAPendingPatent" /></p>
				</div>
			</div>
			<!--/row-->
		</div>
		<!--/container-->
	</tiles:putAttribute>
</tiles:insertDefinition>