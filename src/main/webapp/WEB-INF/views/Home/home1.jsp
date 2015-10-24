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
				<div class="headline">
					<h2><spring:message code="home.guidedHeader" /></h2>
				</div>
				<div><spring:message code="home.guidedText" /> <button onclick="setRunGuide()" class="btn-u rounded"><spring:message code="home.guidedLink" /></button></div>
			</div>
			<div class="row">
				<div class="headline">
					<h2><spring:message code="home.message2" /></h2>
				</div>
				<div><spring:message code="home.message3" /> <a class="btn-u rounded" href="/Home/help1/1"><spring:message code="home.descriptionPage" /></a></div>
			</div>
			
		</div>
		<!--/container-->
	</tiles:putAttribute>
	<tiles:putAttribute name="scripts">
		<script type="text/javascript">
			jQuery(document).ready(function() {
				setCookie("pageNameVal", "home", 30);
			});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>
