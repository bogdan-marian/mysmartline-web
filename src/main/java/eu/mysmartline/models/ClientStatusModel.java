package eu.mysmartline.models;

/**
 * The ClientStatusModel is used to collect the information
 * that will be presented to an active client.
 */
public class ClientStatusModel {
	private String notificationId;
	private boolean isActive;
	private String lineName;
	private String clientNumber;
	private String currentNumber;
	private int clientsAhead;
	private int probableWaitMinutes;
	private String notificationValue;
	private String notificationType = "email";
	//getters and setters
	public String getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getClientNumber() {
		return clientNumber;
	}
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
	public String getCurrentNumber() {
		return currentNumber;
	}
	public void setCurrentNumber(String currentNumber) {
		this.currentNumber = currentNumber;
	}
	public int getClientsAhead() {
		return clientsAhead;
	}
	public void setClientsAhead(int clientsAhead) {
		this.clientsAhead = clientsAhead;
	}
	public int getProbableWaitMinutes() {
		return probableWaitMinutes;
	}
	public void setProbableWaitMinutes(int probableWaitMinutes) {
		this.probableWaitMinutes = probableWaitMinutes;
	}
	public String getNotificationValue() {
		return notificationValue;
	}
	public void setNotificationValue(String notificationValue) {
		this.notificationValue = notificationValue;
	}
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
}
