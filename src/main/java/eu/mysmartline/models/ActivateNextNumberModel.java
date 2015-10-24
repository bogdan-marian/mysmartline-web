package eu.mysmartline.models;


public class ActivateNextNumberModel {
	private Long notificationId;
	private Long servicePointId;
	private String lineId;
	
	public Long getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}
	public Long getServicePointId() {
		return servicePointId;
	}
	public void setServicePointId(Long servicePointId) {
		this.servicePointId = servicePointId;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	
	
	
	
}
