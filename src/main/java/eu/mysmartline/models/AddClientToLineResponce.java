package eu.mysmartline.models;

public class AddClientToLineResponce {
	private boolean success;
	private String notificationType;
	private String notificationValue;
	private String lineName;
	private String clientDisplayNumber;
	
	//AKA NotificationItemId
	private Long notificationPanelId;
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	public String getNotificationValue() {
		return notificationValue;
	}
	public void setNotificationValue(String notificationValue) {
		this.notificationValue = notificationValue;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getClientDisplayNumber() {
		return clientDisplayNumber;
	}
	public void setClientDisplayNumber(String clientDisplayNumber) {
		this.clientDisplayNumber = clientDisplayNumber;
	}
	public Long getNotificationPanelId() {
		return notificationPanelId;
	}
	public void setNotificationPanelId(Long notificationPanelId) {
		this.notificationPanelId = notificationPanelId;
	}
}
