package eu.mysmartline.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Device {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	private Long longPartId;
	private String userId;
	
	private Date dateCreated;
	private String gcmRegId;
	private String shortId;
	private String userFrendlyName;
	
	//getters and setters
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getGcmRegId() {
		return gcmRegId;
	}
	public void setGcmRegId(String gcmRegId) {
		this.gcmRegId = gcmRegId;
	}
	public String getShortId() {
		return shortId;
	}
	public void setShortId(String shortId) {
		this.shortId = shortId;
	}
	public String getUserFrendlyName() {
		return userFrendlyName;
	}
	public void setUserFrendlyName(String userFrendlyName) {
		this.userFrendlyName = userFrendlyName;
	}
	
	
}
