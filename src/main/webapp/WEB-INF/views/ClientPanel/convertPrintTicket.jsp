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
								${clientDetailsModel.nextProbableNumber } <span>(${clientDetailsModel.lineName })</span>
							</h3>
							<h4>
								<span><spring:message code="clientPanel.convertPrintTicket.convertThisPaper" /></span>
							</h4>
						</div>
						<ul class="pricing-content list-unstyled">
							<li><i class="icon-users"></i> <spring:message code="clientPanel.viewStatus.clientsAhead" />:
								${clientDetailsModel.clientsAhead }</li>
							<li><i class="icon-info"></i> <spring:message code="clientPanel.viewStatus.currentNumber" />:
								${clientDetailsModel.currentNumber }</li>
							<li><i class="icon-clock"></i> <spring:message code="clientPanel.viewStatus.probableTime" />:
								${clientDetailsModel.probableWaitTime } <spring:message code="clientPanel.viewStatus.minutes" /></li>
						</ul>
						<div class="pricing-footer">
							<p>
								<form:form role="form" method="post"
									modelAttribute="clientDetailsModel"
									action="/ClientPanel/convertPrintTicketPost">

									<div class="form-group">
										<form:hidden path="lineId" />
										<form:label path="email"><spring:message code="lineRegistration.readClientDetails.emailAddress" /></form:label>
										<form:input path="email" class="form-control"
											placeholder="Enter email" type="email" />
									</div>
									<button type="submit" class="btn-u fa fa-thumbs-up  rounded">
										<spring:message code="clientPanel.convertPrintTicket.convertNumber" /></button>
								</form:form>
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

