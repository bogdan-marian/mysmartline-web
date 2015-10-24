<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- <spring:message code="" /> :)--%>
<tiles:insertDefinition name="introjsTemplate">
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
			<div class="row">
				<div class="headline">
					<h2>Tour home</h2>
				</div>
				<div>
					<button onclick="setRunGuide()"
						class="btn-u rounded startTourButon">Start the tour</button>
				</div>
				<div>
					<button onclick="endRunGuide()" class="btn-u rounded endTourButon">End
						the tour</button>
				</div>
				<div>
					<button onclick="setUpGuide()" class="btn-u rounded lastTourButon">Test
						the script</button>
				</div>
				<div>
					<button onclick="extraScript()" class="btn-u rounded extraButon">extraButon</button>
				</div>
				<div>Hello from tour home page</div>
			</div>

		</div>
		<!--/container-->

	</tiles:putAttribute>
	<tiles:putAttribute name="scripts">
		<script type="text/javascript">
			jQuery(document).ready(function() {
				setCookie("pageNameVal", "tourHome", 30);
			});
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>
