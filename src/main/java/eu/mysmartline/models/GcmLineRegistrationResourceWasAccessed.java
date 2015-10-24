package eu.mysmartline.models;

public class GcmLineRegistrationResourceWasAccessed {
	private String deviceId;

	public static GcmLineRegistrationResourceWasAccessed build() {
		GcmLineRegistrationResourceWasAccessed gcmLineRegistrationResourceWasAccessed = new GcmLineRegistrationResourceWasAccessed();
		gcmLineRegistrationResourceWasAccessed
				.setDeviceId("the object maters not the id");
		return gcmLineRegistrationResourceWasAccessed;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}
