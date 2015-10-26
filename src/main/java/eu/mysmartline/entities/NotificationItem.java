package eu.mysmartline.entities;

import javax.jdo.annotations.Persistent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.datanucleus.api.jpa.annotations.Extension;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

@Entity
public class NotificationItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	
	private String lineNumberId;
	
	private String notificationType;
	private String notificationValue;
	private Integer notifyBefore;
	//---- start getters and setters\
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLineNumberId() {
		return lineNumberId;
	}
	public void setLineNumberId(String lineNumberId) {
		this.lineNumberId = lineNumberId;
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
	public Integer getNotifyBefore() {
		return notifyBefore;
	}
	public void setNotifyBefore(Integer notifyBefore) {
		this.notifyBefore = notifyBefore;
	}
	
	
}
