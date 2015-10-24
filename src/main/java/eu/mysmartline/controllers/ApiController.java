package eu.mysmartline.controllers;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import eu.mysmartline.entities.Device;
import eu.mysmartline.entities.SettingsUser;
import eu.mysmartline.models.AddClientToLineResponce;
import eu.mysmartline.models.ApiActivateValidateRequest;
import eu.mysmartline.models.ApiActivateValidateResponce;
import eu.mysmartline.models.ApiPrintNumberRequest;
import eu.mysmartline.models.ApiPrintNumberResponce;
import eu.mysmartline.models.ApiRequestLinesModel;
import eu.mysmartline.models.GcmWarmUp;
import eu.mysmartline.models.LineDetailsModel;
import eu.mysmartline.models.MyKeys;
import eu.mysmartline.services.DeviceRegistrationService;
import eu.mysmartline.services.LineService;
import eu.mysmartline.services.MyHttpService;
import eu.mysmartline.services.SettingsUserService;

@Controller
public class ApiController {
	private static final Logger log = Logger.getLogger(ApiController.class
			.getName());

	@RequestMapping(value = "Api/getLines", method = RequestMethod.POST)
	@ResponseBody
	public String getLines(@Valid ApiRequestLinesModel apiRequestLinesModel,
			BindingResult bindingResult) {
		if (apiRequestLinesModel == null) {
			return "nodata";
		}
		String deviceGcmId = apiRequestLinesModel.getDeviceGcmId();
		List<LineDetailsModel> lines = LineService
				.getLineDetailsModelListByDeviceGcmId(deviceGcmId);
		Gson gson = new Gson();
		String json = gson.toJson(lines);
		return json;
	}

	@RequestMapping(value = "Api/resetDevice/{deviceGcmId}")
	@ResponseBody
	public String resetDevice(@PathVariable String deviceGcmId) {
		boolean response = DeviceRegistrationService.resetDevice(deviceGcmId);
		Gson gson = new Gson();
		String json = gson.toJson(response);
		return json;
	}

	/**
	 * this vestion returns the json responce of the generated number;
	 * 
	 * @param jsonPrintRequest
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "Api/generatePrintNumberV2/{jsonPrintRequest}")
	@ResponseBody
	public String generatePrintNumberV2(@PathVariable String jsonPrintRequest) {
		Gson gson = new Gson();
		ApiPrintNumberRequest apiPrintNumberRequest = gson.fromJson(
				jsonPrintRequest, ApiPrintNumberRequest.class);
		AddClientToLineResponce addClientToLineResponce = LineService
				.registerNewPrintNumber(apiPrintNumberRequest);

		ApiPrintNumberResponce apiPrintNumberResponce = new ApiPrintNumberResponce();
		apiPrintNumberResponce.setLineName(addClientToLineResponce
				.getLineName());
		apiPrintNumberResponce.setClientNumber(addClientToLineResponce
				.getClientDisplayNumber());
		apiPrintNumberResponce.setQrText(MyKeys.PROPERTY_HOME
				+ "ClientPanel/convertPrintTicket/"
				+ addClientToLineResponce.getNotificationPanelId());

		Gson gson2 = new Gson();
		String json = gson2.toJson(apiPrintNumberResponce);
		return json;
	}

	@RequestMapping(value = "Api/warmUpDevice")
	@ResponseBody
	public String warmUpDevice(@Valid GcmWarmUp gcmWarmUp,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "not properly formated request";
		}
		try {
			Long.parseLong(gcmWarmUp.getDeviceShortLongId());
		} catch (NumberFormatException e) {
			return "no valid id";
		}
		MyHttpService.sendWarmUpMessage(gcmWarmUp);
		Gson gson = new Gson();
		return gson.toJson(gcmWarmUp);
	}

	@RequestMapping(value = "Api/activateValidateDevice")
	@ResponseBody
	public String activateValidateDevice(
			@Valid ApiActivateValidateRequest apiActivateValidateRequest,
			BindingResult bindingResult) {
		String email = apiActivateValidateRequest.getEmail();
		String gcmRegId = apiActivateValidateRequest.getGcmRegId();
		String shortAndLongId = apiActivateValidateRequest.getShortAndLongId();
		log.info("bogdan log 1: email = " + email + " gcmRegId = " + gcmRegId
				+ " shortAndLongId = " + shortAndLongId);

		ApiActivateValidateResponce responce = new ApiActivateValidateResponce();
		responce.setSuccesfull(false);
		Gson gson = new Gson();
		if (bindingResult.hasErrors()) {
			responce.setSuccesfull(false);
			responce.setDetailsAboutFailure("Server not able to decode request");
			return gson.toJson(responce);
		}

		String stringId = apiActivateValidateRequest.getShortAndLongId();
		Long longId = Long.parseLong(stringId);
		Device device = DeviceRegistrationService.getDevice(longId);
		
		if (device == null) {
			responce.setDetailsAboutFailure("Ilegal request type 1 [" + longId
					+ "]");
			return gson.toJson(responce);
		}
		log.info("bogdan log 2 located device: "
				+ " longPartId = " + device.getLongPartId()
				+ " userId = " + device.getUserId()
				+ " gcmRegId = " + device.getGcmRegId()
				+ " shortId = " + device.getShortId() 
				);

		// test that cloud gcmId = device gcmId
		String cloudGcmId = device.getGcmRegId();
		String deviceGcmId = device.getGcmRegId();
		if (!cloudGcmId.equals(deviceGcmId)) {
			responce.setDetailsAboutFailure("Ilegal request type 2 [" + longId
					+ "]");
			return gson.toJson(responce);
		}
		// find user by email

		SettingsUser settingsUser = SettingsUserService.getByEmail(email);
		if (settingsUser == null) {
			String content = "No valid user. Please log in into the web "
					+ "interface atleast once";
			responce.setDetailsAboutFailure(content);
			return gson.toJson(responce);
		}

		// activate the device
		log.info("bogdan log 3 settingsUser: "
				+ " userId = " + settingsUser.getUserId()
				+ " userEmail = " + settingsUser.getUserEmail()
				);

		Long deviceId = device.getLongPartId();
		String userId = settingsUser.getUserId();
		DeviceRegistrationService.setUserId(deviceId, userId);

		String content = "Device succesfull activated";
		responce.setDetailsAboutFailure(content);
		responce.setSuccesfull(true);
		return gson.toJson(responce);
	}
}
