package eu.mysmartline.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

import eu.mysmartline.entities.Device;
import eu.mysmartline.models.GcmCurrentNumberChanged;
import eu.mysmartline.models.GcmLineRegistrationResourceWasAccessed;
import eu.mysmartline.models.GcmResetDevice;
import eu.mysmartline.models.GcmWarmUp;

@SuppressWarnings("deprecation")
public class MyHttpService {

	public static void sendMessage(String registrationId, String message)
			throws Exception {

		String url = "https://android.googleapis.com/gcm/send";
		String deviceRegistetionID = registrationId;

		@SuppressWarnings("resource")
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		// add header
		/**
		 * initial value post.setHeader("Authorization",
		 * "key=AIzaSyCDIQ7kpx2rfEQvHiJBTKk4fd1kxL-3PB8");
		 */
		post.setHeader("Authorization",
				"key=AIzaSyBNcSqZK-ci4dNtjhSQZ5Gp6gkyeeu5xqM");
		post.setHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>(1);
		urlParameters.add(new BasicNameValuePair("registration_id",
				deviceRegistetionID));
		urlParameters.add(new BasicNameValuePair("data1", message));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);

		/*
		 * System.out.println("\nSending 'POST' request to URL : " + url);
		 * System.out.println("Post parameters : " + post.getEntity());
		 * System.out.println("Response Code : " +
		 * response.getStatusLine().getStatusCode());
		 */

		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		/* System.out.println(result.toString()); */
	}

	public static void sendMessageV2(String registrationId, String message) {

		String jsonMessage = "Content-Type:application/json\n"
				+ "Authorization:key=AIzaSyBNcSqZK-ci4dNtjhSQZ5Gp6gkyeeu5xqM\n"
				+ " {" + "\"registration_ids\" : [\"" + registrationId
				+ "\"], " + "\"data1\" : {" + message + "},}";
		try {
			URL url = new URL("https://android.googleapis.com/gcm/send");
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream());
			writer.write(jsonMessage);
			writer.close();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			}

		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
	}

	public static void sendGcmMessage(String registrationId, String message) {
		Sender sender = new Sender("AIzaSyBNcSqZK-ci4dNtjhSQZ5Gp6gkyeeu5xqM");
		Message gcmMessage = new Message.Builder().addData("data1", message)
				.build();
		List<String> devices = new ArrayList<String>();
		devices.add(registrationId);
		MulticastResult result = null;
		try {
			result = sender.send(gcmMessage, devices, 5);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(result);
	}

	public static void notifyDevicesResourceAccessed(Long lineId) {
		Sender sender = new Sender("AIzaSyBNcSqZK-ci4dNtjhSQZ5Gp6gkyeeu5xqM");

		GcmLineRegistrationResourceWasAccessed resourceAccessed;
		resourceAccessed = GcmLineRegistrationResourceWasAccessed.build();
		String message = GcmService.buildGcmMessage(resourceAccessed);

		Message gcmMessage = new Message.Builder().addData("data1", message)
				.build();

		List<Device> devicesList = DeviceRegistrationService
				.getDevicesByLineId(lineId);
		// notify only if wee have devices in the notification list
		if (devicesList.size() > 0) {
			List<String> devices = new ArrayList<String>();
			for (Device device : devicesList) {
				devices.add(device.getGcmRegId());
			}
			// MulticastResult result = null;
			try {
				// result = sender.send(gcmMessage, devices, 5);
				sender.send(gcmMessage, devices, 5);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// System.out.println(result);
		}
	}
	public static void sendWarmUpMessage(GcmWarmUp gcmWarmUp){
		String longAsString = gcmWarmUp.getDeviceShortLongId();
		Long longId = Long.parseLong(longAsString);
		Device myDev = DeviceRegistrationService.getDevice(longId);
		if (myDev == null){
			return;
		}
		List<Device> devicesList = new ArrayList<Device>();
		devicesList.add(myDev);
		
		String message = GcmService.buildGcmMessage(gcmWarmUp);
		
		// notify only if wee have devices in the notification list
		if (devicesList.size() > 0) {
			Sender sender = new Sender(
					"AIzaSyBNcSqZK-ci4dNtjhSQZ5Gp6gkyeeu5xqM");
			Message gcmMessage = new Message.Builder()
					.addData("data1", message).build();
			List<String> devices = new ArrayList<String>();
			for (Device device : devicesList) {
				devices.add(device.getGcmRegId());
			}
			// MulticastResult result = null;
			try {
				// result = sender.send(gcmMessage, devices, 5);
				sender.send(gcmMessage, devices, 5);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// System.out.println(result);
		}
	}

	public static void notifyDevicesCurrentNumberChanjed(String lineId,
			Long ServicePointId) {
		Sender sender = new Sender("AIzaSyBNcSqZK-ci4dNtjhSQZ5Gp6gkyeeu5xqM");
/*
		String myLabel = LineService.getLabel(lineId);
		Integer numVal = LineService.getCurentNumber(lineId);
		String displayMessage;
		if (ServicePointId == null){
			displayMessage = myLabel + numVal;
		}
		else{
			String servicePoint = ServicePointService.getShortName(ServicePointId);
			displayMessage = myLabel + numVal + " - " + servicePoint;
		}
		

		GcmCurrentNumberChanged gcmCurrentNumberChanged = new GcmCurrentNumberChanged();
		gcmCurrentNumberChanged.setCurrentNumber(displayMessage);
		String message = GcmService.buildGcmMessage(gcmCurrentNumberChanged);

		Message gcmMessage = new Message.Builder().addData("data1", message)
				.build();
		List<Device> devicesList = DeviceRegistrationService
				.getDevicesByLineId(lineId);
		List<String> devices = new ArrayList<String>();
		// notify only if wee have devices in the notification list
		if (devicesList.size() > 0) {
			for (Device device : devicesList) {
				devices.add(device.getGcmRegId());
			}
			try {
				sender.send(gcmMessage, devices, 5);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		throw new UnsupportedOperationException("Please finish this");
	}
	public static void nofityDeviceWasReset(String deviceGcmId){
		GcmResetDevice gcmResetDevice = new GcmResetDevice();
		gcmResetDevice.setDeviceGcmId(deviceGcmId);
		String message = GcmService.buildGcmMessage(gcmResetDevice);
		Sender sender = new Sender("AIzaSyBNcSqZK-ci4dNtjhSQZ5Gp6gkyeeu5xqM");
		Message gcmMessage = new Message.Builder().addData("data1", message).build();
		List<String> devices = new ArrayList<String>();
		devices.add(deviceGcmId);
		try{
			sender.send(gcmMessage, devices, 5);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
