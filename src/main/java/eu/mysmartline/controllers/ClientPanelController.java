package eu.mysmartline.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.mysmartline.entities.NotificationItem;
import eu.mysmartline.models.ClientDetailsModel;
import eu.mysmartline.models.ClientStatusModel;
import eu.mysmartline.services.ClientStatusService;
import eu.mysmartline.services.LineService;
import eu.mysmartline.services.MySecurity;
import eu.mysmartline.services.NotificationItemService;

@Controller
public class ClientPanelController {

	@RequestMapping(value = "ClientPanel/viewStatus/{notificationId}", method = RequestMethod.GET)
	public String viewStatus(@PathVariable String notificationId, ModelMap model) {
		ClientStatusModel clientStatusModel = ClientStatusService
				.getStatusByNotificationId(notificationId);
		model.addAttribute(clientStatusModel);
		return "ClientPanel/viewStatus";
	}

	// TODO implement convertPrintTicket function
	@RequestMapping(value = "ClientPanel/convertPrintTicket/{notificationId}", method = RequestMethod.GET)
	public String convertPrintTicket(@PathVariable String notificationId,
			ModelMap model) {

		/**
		 * if already converted return This number has already bean converted by
		 * somebody else. Please check your email for more details.
		 */
		NotificationItem notificationItem = NotificationItemService
				.getById(notificationId);
		if (!notificationItem.getNotificationType().equals("print")
				&& notificationItem.getNotificationType() != null) {
			System.out.println("Bogdan Debug notificationType: "
					+ notificationItem.getNotificationType());
			return "forward:/Error/numberAllreadyConverted";
		}
		ClientDetailsModel clientDetailsModel = new ClientDetailsModel();
		ClientStatusModel clientStatusModel = ClientStatusService
				.getStatusByNotificationId(notificationId);
		clientDetailsModel.setNextProbableNumber(clientStatusModel
				.getClientNumber());
		clientDetailsModel.setLineName(clientStatusModel.getLineName());
		clientDetailsModel.setClientsAhead(clientStatusModel.getClientsAhead());
		clientDetailsModel.setCurrentNumber(clientStatusModel
				.getCurrentNumber());
		clientDetailsModel.setProbableWaitTime(clientStatusModel
				.getProbableWaitMinutes());

		// line id will be used as notificationId
		clientDetailsModel.setLineId(notificationId);
		clientDetailsModel.setEmail(MySecurity.getCurrentUserEmail());

		model.addAttribute(clientDetailsModel);

		return "ClientPanel/convertPrintTicket";
	}

	@RequestMapping(value = "ClientPanel/convertPrintTicketPost", method = RequestMethod.POST)
	public String convertPrintTicketPost(
			@Valid ClientDetailsModel clientDetailsModel,
			BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors()) {
			return "forward:/Error/securityViolation";
		}
		// i'm using the lineId as notificationId
		String notificationId = clientDetailsModel.getLineId();
		// for the moment only email
		String notificationType = "email";
		String notificationValue = clientDetailsModel.getEmail();
		boolean succesfullConvertion = LineService.convertNumber(
				notificationId, notificationType, notificationValue);
		if (!succesfullConvertion) {
			return "forward:/Error/securityViolation";
		}

		return "redirect:/ClientPanel/viewStatus/" + notificationId;
	}
}
