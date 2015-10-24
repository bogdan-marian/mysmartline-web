package eu.mysmartline.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

@Entity
public class LineNumber {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	private Long longPartId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Line line;
	
	
	@Unowned
	private NotificationItem notificationItem;
	
	@Unowned
	private ServicePoint servicePoint;
	
	private int number;
	private boolean isCurrent = false;
	private boolean isArchived = false;
	private Date dateAsigned = new Date();
	private Date dateActivated;
	private Date dateArchived;
	private boolean sameDay;
	private Long durationInMiliseconds;
	
	
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
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public NotificationItem getNotificationItem() {
		return notificationItem;
	}
	public void setNotificationItem(NotificationItem notificationItem) {
		this.notificationItem = notificationItem;
	}
	public Date getDateAsigned() {
		return dateAsigned;
	}
	public void setDateAsigned(Date dateAsigned) {
		this.dateAsigned = dateAsigned;
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
	public Date getDateArchived() {
		return dateArchived;
	}
	public void setDateArchived(Date dateArchived) {
		this.dateArchived = dateArchived;
	}
	public ServicePoint getServicePoint() {
		return servicePoint;
	}
	public void setServicePoint(ServicePoint servicePoint) {
		this.servicePoint = servicePoint;
	}
	public Date getDateActivated() {
		return dateActivated;
	}
	public void setDateActivated(Date dateActivated) {
		this.dateActivated = dateActivated;
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
