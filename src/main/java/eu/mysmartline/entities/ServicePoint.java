package eu.mysmartline.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class ServicePoint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	private Long longPartId;
	private String userId;
	
	private String shortName;
	private String description;
	private boolean active = true;
	
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
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
