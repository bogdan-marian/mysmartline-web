<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- <spring:message code="" /> :)--%>
<tiles:insertDefinition name="smallHeaderTemplate">
	<tiles:putAttribute name="body">
		<div class="container content">


			<div class="row">
				<div class="col-md-5 col-sm-6">
					<div class="pricing hover-effect">
						<div class="pricing-head">
							<h3>
								${clientStatusModel.clientNumber } <span>(<spring:message code="clientPanel.viewStatus.myNumber" />)</span>
							</h3>
							<h4>
								<span>${clientStatusModel.lineName }</span>
							</h4>
						</div>
						<ul class="pricing-content list-unstyled">
							<li><i class="icon-users"></i> <spring:message code="clientPanel.viewStatus.clientsAhead" />:
								${clientStatusModel.clientsAhead }</li>
							<li><i class="icon-info"></i> <spring:message code="clientPanel.viewStatus.currentNumber" />:
								${clientStatusModel.currentNumber }</li>
							<li><i class="icon-clock"></i> <spring:message code="clientPanel.viewStatus.probableTime" />:
								${clientStatusModel.probableWaitMinutes } <spring:message code="clientPanel.viewStatus.minutes" /></li>
						</ul>
						<div class="pricing-footer">
							<p>
								<a href="/ClientPanel/viewStatus/${clientStatusModel.notificationId }" class="btn-u rounded"> <spring:message code="clientPanel.viewStatus.refresh" /></a>
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


