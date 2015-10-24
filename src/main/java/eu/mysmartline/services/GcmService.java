package eu.mysmartline.services;

import com.google.gson.Gson;

import eu.mysmartline.models.GcmActivateDevice;
import eu.mysmartline.models.GcmCurrentNumberChanged;
import eu.mysmartline.models.GcmLineRegistrationResourceWasAccessed;
import eu.mysmartline.models.GcmMessage;
import eu.mysmartline.models.GcmResetDevice;
import eu.mysmartline.models.GcmTestMessage;
import eu.mysmartline.models.GcmType;
import eu.mysmartline.models.GcmWarmUp;
/**
 * This class should be used to format the Google Cloud messages
 * @author bogdan
 *
 */
public class GcmService {
	
	/**
	 * This method formats the device activation message and then converts the
	 * message to json 
	 * @param gcmActivateDevice
	 * @return
	 */
	public static String buildGcmMessage(GcmActivateDevice gcmActivateDevice){
		GcmMessage message = new GcmMessage();
		message.setType(GcmType.GCM_ACTIVATE_DEVICE);
		Gson gson = new Gson();
		message.setJsonObject(gson.toJson(gcmActivateDevice));
		return gson.toJson(message);
	}
	public static String buildGcmMessage(GcmCurrentNumberChanged gcmCurrentNumberChanged){
		GcmMessage message = new GcmMessage();
		message.setType(GcmType.GCM_CURRENT_NUMBER_CHANGED);
		Gson gson = new Gson();
		message.setJsonObject(gson.toJson(gcmCurrentNumberChanged));
		return gson.toJson(message);
	}
	public static String buildGcmMessage(GcmTestMessage gcmTestMessage){
		GcmMessage message = new GcmMessage();
		message.setType(GcmType.GCM_TEST_MESSAGE);
		Gson gson = new Gson();
		message.setJsonObject(gson.toJson(gcmTestMessage));
		return gson.toJson(message);
	}
	public static String buildGcmMessage(GcmLineRegistrationResourceWasAccessed resorceAccessed){
		GcmMessage message = new GcmMessage();
		message.setType(GcmType.GCM_LINE_REGISTRATION_RESOURCE_WAS_ACCESSED);
		Gson gson = new Gson();
		message.setJsonObject(gson.toJson(resorceAccessed));
		return gson.toJson(message);
	}
	public static String buildGcmMessage(GcmResetDevice gcmResetDevice){
		GcmMessage message = new GcmMessage();
		message.setType(GcmType.GCM_RESET_DEVICE);
		Gson gson = new Gson();
		message.setJsonObject(gson.toJson(gcmResetDevice));
		return gson.toJson(message);
	}
	public static String buildGcmMessage(GcmWarmUp gcmWarmUp){
		GcmMessage message = new GcmMessage();
		message.setType(GcmType.GCM_WORM_UP);
		Gson gson = new Gson();
		message.setJsonObject(gson.toJson(gcmWarmUp));
		return gson.toJson(message);
	}
}
