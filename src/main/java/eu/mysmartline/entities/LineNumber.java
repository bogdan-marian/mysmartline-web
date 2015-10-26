package eu.mysmartline.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.datanucleus.api.jpa.annotations.Extension;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

@Entity
public class LineNumber {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	
	private String lineId;
	private String notificationItemId;
	private String servicePointId;
	
	private int number;
	private boolean isCurrent = false;
	private boolean isArchived = false;
	private Date dateAsigned = new Date();
	private Date dateActivated;
	private Date dateArchived;
	private boolean sameDay;
	private Long durationInMiliseconds;
	//start of generated content
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getNotificationItemId() {
		return notificationItemId;
	}
	public void setNotificationItemId(String notificationItemId) {
		this.notificationItemId = notificationItemId;
	}
	public String getServicePointId() {
		return servicePointId;
	}
	public void setServicePointId(String servicePointId) {
		this.servicePointId = servicePointId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public boolean isCurrent() {
		return isCurrent;
	}
	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
	public boolean isArchived() {
		return isArchived;
	}
	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}
	public Date getDateAsigned() {
		return dateAsigned;
	}
	public void setDateAsigned(Date dateAsigned) {
		this.dateAsigned = dateAsigned;
	}
	public Date getDateActivated() {
		return dateActivated;
	}
	public void setDateActivated(Date dateActivated) {
		this.dateActivated = dateActivated;
	}
	public Date getDateArchived() {
		return dateArchived;
	}
	public void setDateArchived(Date dateArchived) {
		this.dateArchived = dateArchived;
	}
	public boolean isSameDay() {
		return sameDay;
	}
	public void setSameDay(boolean sameDay) {
		this.sameDay = sameDay;
	}
	public Long getDurationInMiliseconds() {
		return durationInMiliseconds;
	}
	public void setDurationInMiliseconds(Long durationInMiliseconds) {
		this.durationInMiliseconds = durationInMiliseconds;
	}
	
}
