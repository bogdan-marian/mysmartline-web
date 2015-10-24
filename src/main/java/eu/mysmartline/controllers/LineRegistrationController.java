package eu.mysmartline.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import eu.mysmartline.models.AddClientToLineResponce;
import eu.mysmartline.models.ApiPrintNumberRequest;
import eu.mysmartline.models.ClientDetailsModel;
import eu.mysmartline.models.MyKeys;
import eu.mysmartline.models.RegistrationResultModel;
import eu.mysmartline.services.ClientDetailsService;
import eu.mysmartline.services.LineService;
import eu.mysmartline.services.MyHttpService;

@Controller
public class LineRegistrationController {

	@RequestMapping(value = "LineRegistration/readClientDetails/{lineId}", method = RequestMethod.GET)
	public String readClientDetails(@PathVariable Long lineId, ModelMap model) {

		// notify all devices about resource accessed
		MyHttpService.notifyDevicesResourceAccessed(lineId);

		/*ClientDetailsModel clientDetailsModel = ClientDetailsService
				.getForNewClientByLineId(lineId);
		model.addAttribute(clientDetailsModel);
		return "LineRegistration/readClientDetails";*/
		throw new IllegalStateException("Please finish this");
	}

	@RequestMapping(value = "LineRegistration/readClientDetailsPost", method = RequestMethod.POST)
	public String readClientDetailsPost(
			@Valid ClientDetailsModel clientDetailsModel,
			BindingResult bindingResult, ModelMap model) {
		// TODO Create new number and notification item. Send client email
		if (bindingResult.hasErrors()) {
			return "redirect:readClientDetailsPostError";
		}
		// for the moment the form is only for email's
		clientDetailsModel.setNotifType("email");
/*
		RegistrationResultModel resultModel = LineService.registerNewClient(
				clientDetailsModel.getLineId(),
				clientDetailsModel.getNotifType(),
				clientDetailsModel.getEmail());
		if (!resultModel.isSuccesfullRegistration()) {
			switch (resultModel.getReasonNotSuccesfull()) {
			case ALREADY_IN_LINE:
				return "LineRegistration/allreadyInLine";
			default:
				return "LineRegistration/readClientDetailsPostError";
			}
		}
		model.addAttribute(resultModel);
		return "LineRegistration/readClientDetailsPost";*/
		throw new IllegalStateException("Please finish implementing this");
	}

	@RequestMapping(value = "LineRegistration/generatePrintNumber/{jsonPrintRequest}")
	public String generatePrintNumber(@PathVariable String jsonPrintRequest,
			ModelMap model) {
		Gson gson = new Gson();
		ApiPrintNumberRequest apiPrintNumberRequest = gson.fromJson(
				jsonPrintRequest, ApiPrintNumberRequest.class);
		AddClientToLineResponce addClientToLineResponce = LineService
				.registerNewPrintNumber(apiPrintNumberRequest);

		model.addAttribute("notificationId",
				addClientToLineResponce.getNotificationPanelId());

		model.addAttribute("vLineName", addClientToLineResponce.getLineName());
		model.addAttribute("vClientNumber",
				addClientToLineResponce.getClientDisplayNumber());
		model.addAttribute("qrText",
				MyKeys.PROPERTY_HOME + "ClientPanel/convertPrintTicket/"
						+ addClientToLineResponce.getNotificationPanelId());
		return "LineRegistration/generatePrintNumber";
	}
	
	
	
	
}
