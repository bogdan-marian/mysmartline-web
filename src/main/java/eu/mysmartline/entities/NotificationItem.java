package eu.mysmartline.entities;

import javax.jdo.annotations.Persistent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

@Entity
public class NotificationItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	private Long longPartId;
	
	@Unowned
	@Persistent(mappedBy="notificationItem")
	LineNumber lineNumber;
	
	
	
	private String notificationType;
	private String notificationValue;
	private Integer notifyBefore;
	
	
	//---- start getters and setters\
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public Long getLongPartId() {
		return longPartId;
	}
	public void setLongPartId(Long longPartId) {
		this.longPartId = longPartId;
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
	public LineNumber getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(LineNumber lineNumber) {
		this.lineNumber = lineNumber;
	}
	public Integer getNotifyBefore() {
		return notifyBefore;
	}
	public void setNotifyBefore(Integer notifyBefore) {
		this.notifyBefore = notifyBefore;
	}
	
}
