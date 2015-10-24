package eu.mysmartline.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import eu.mysmartline.models.DeviceRegistrationRequestModel;
import eu.mysmartline.models.DeviceRegistrationResponceModel;
import eu.mysmartline.services.DeviceRegistrationService;

@Controller
public class DeviceEntryPointController {

	/*
	 * @Valid DeviceRegistrationRequestModel deviceRegistrationRequestModel,
	 * BindingResult bindingResult,
	 */
	@RequestMapping(value = "DeviceEntryPoint/registerGet", method = RequestMethod.GET)
	public String registerGet(ModelMap model) {
		DeviceRegistrationRequestModel deviceRegistrationRequestModel = new DeviceRegistrationRequestModel();

		model.addAttribute(deviceRegistrationRequestModel);
		return "DeviceEntryPoint/registerGet";
	}

	@RequestMapping(value = "DeviceEntryPoint/registerPost", method = RequestMethod.POST)
	@ResponseBody
	public String registerPost(
			@Valid DeviceRegistrationRequestModel deviceRegistrationRequestModel,
			BindingResult bindingResult) {
		DeviceRegistrationResponceModel regResponseModel = DeviceRegistrationService
				.registerDevice(deviceRegistrationRequestModel.getGcmRegId());
		Gson gson = new Gson();
		
		return gson.toJson(regResponseModel);
	}
}
