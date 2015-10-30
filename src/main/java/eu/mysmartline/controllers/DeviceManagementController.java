package eu.mysmartline.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.mysmartline.entities.Device;
import eu.mysmartline.services.DeviceRegistrationService;
import eu.mysmartline.services.MySecurity;

@Controller
public class DeviceManagementController {

	@RequestMapping(value = "DeviceManagement/activateAutomatic/{activationId}", method = RequestMethod.GET)
	public String activateAutomatic(@PathVariable String activationId,
			ModelMap model) {
		Device device = DeviceRegistrationService
				.activateByShortId(activationId);
		
		if (device == null) {
			model.addAttribute("activationId", activationId);
			return "DeviceManagement/goToNotPosible";
			
		} else {
			model.addAttribute("activationId", device.getId());
			return "DeviceManagement/goToRename";
			
		}
	}
	

	@RequestMapping(value = "DeviceManagement/activationNotPosible/", method = RequestMethod.GET)
	public String activationNotPosible(@PathVariable String activationId,
			ModelMap model) {
		
		return "DeviceManagement/activationNotPosible";
	}

	@RequestMapping(value = "DeviceManagement/renameGet/{deviceId}", method = RequestMethod.GET)
	public String renameGet(@PathVariable String deviceId, ModelMap model) {
		if(!MySecurity.canManageDevice(deviceId)){
			return "forward:/Error/securityViolation";
		}
		Device device = DeviceRegistrationService.getDevice(deviceId);
		model.addAttribute(device);
		return "DeviceManagement/renameGet";
	}

	@RequestMapping(value = "DeviceManagement/renamePost", method = RequestMethod.POST)
	public String renamePost(@Valid Device device, BindingResult bindingResult,
			ModelMap model) {
		if(!MySecurity.canManageDevice(device.getId())){
			return "forward:/Error/securityViolation";
		}
		DeviceRegistrationService.reneme(device.getId(), device.getUserFrendlyName());
		return "redirect: index";
	}
	
	@RequestMapping(value = "DeviceManagement/index")
	public String index(ModelMap model){
		List <Device> device = DeviceRegistrationService.getDevices();
		model.addAttribute("device", device);
		return "DeviceManagement/index";
	}
	@RequestMapping(value = "DeviceManagement/details/{deviceId}", method = RequestMethod.GET)
	public String details(@PathVariable String deviceId, ModelMap model){
		if (!MySecurity.canManageDevice(deviceId)){
			return "forward:/Error/securityViolation";
		}
		Device vipDevice = DeviceRegistrationService.getDevice(deviceId);
		model.addAttribute("vipDevice", vipDevice);
		return "DeviceManagement/details";
	}
	@RequestMapping(value = "DeviceManagement/delete/{deviceId}", method = RequestMethod.GET)
	public String delete(@PathVariable String deviceId, ModelMap model){
		if (!MySecurity.canManageDevice(deviceId)){
			return "forward:/Error/securityViolation";
		}
		DeviceRegistrationService.resetDeviceFromServerSide(deviceId);
		return "redirect:/DeviceManagement/index";
	}
	
	@RequestMapping(value = "DeviceManagement/renameByUser/{deviceId}", method = RequestMethod.GET)
	public String renameByUser(@PathVariable String deviceId, ModelMap model){
		if(!MySecurity.canManageDevice(deviceId)){
			return "forward:/Error/securityViolation";
		}
		Device device = DeviceRegistrationService.getDevice(deviceId);
		model.addAttribute(device);
		return "DeviceManagement/renameByUser";
	}
	@RequestMapping(value = "DeviceManagement/renameByUserPost", method = RequestMethod.POST)
	public String renameByUserPost(@Valid Device device, BindingResult bindingResult,
			ModelMap model) {
		if(!MySecurity.canManageDevice(device.getId())){
			return "forward:/Error/securityViolation";
		}
		DeviceRegistrationService.reneme(device.getId(), device.getUserFrendlyName());
		return "redirect: index";
	}
}
