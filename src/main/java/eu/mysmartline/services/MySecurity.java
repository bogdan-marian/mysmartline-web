package eu.mysmartline.services;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import eu.mysmartline.entities.Device;
import eu.mysmartline.entities.Line;
import eu.mysmartline.entities.ServicePoint;

public class MySecurity {

	public static String getCurrentUserEmail() {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user == null) {
			return "";
		} else {
			return user.getEmail();
		}
	}

	public static boolean canManageLine(String id) {
		Line line = LineService.getLine(id);
		String lineUserId = line.getUserId();
		String userId = getUserId();
		return userId.equals(lineUserId);
	}

	public static boolean canManageDevice(String deviceId) {
		Device device = DeviceRegistrationService.getDevice(deviceId);
		if (device == null) {
			return false;
		}
		String deviceUserId = device.getUserId();
		if (deviceUserId == null) {
			return false;
		} else {
			String userId = getUserId();
			return userId.equals(deviceUserId);
		}

	}

	/**
	 * @return the string id of the current logged in user
	 */
	public static String getUserId() {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user != null) {
			return (user.getUserId());
		} else {
			return "emptyUser";
		}
	}

	public static boolean canManageServicePoint(String servicePointId) {
		// TODO Auto-generated method stub
		ServicePoint servicePoint = ServicePointService
				.getServicePoint(servicePointId);
		if (servicePoint == null){
			return false;
		}
		String serviceUserId = servicePoint.getUserId();
		if (serviceUserId == null){
			return false;
		}else{
			String userId = MySecurity.getUserId();
			return userId.equals(serviceUserId);
		}
	}
}
