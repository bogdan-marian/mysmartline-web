package eu.mysmartline.models;

public class GcmActivateDevice {
	private boolean activationSuccessful;
	public static GcmActivateDevice buildPositiveActivationMessage(){
		GcmActivateDevice message = new GcmActivateDevice();
		message.setActivationSuccessful(true);
		return message;
	}

	public boolean isActivationSuccessful() {
		return activationSuccessful;
	}

	public void setActivationSuccessful(boolean activationSuccessful) {
		this.activationSuccessful = activationSuccessful;
	}
}
