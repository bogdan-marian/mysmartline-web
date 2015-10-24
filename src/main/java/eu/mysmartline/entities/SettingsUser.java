package eu.mysmartline.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class SettingsUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	private Long longPartId;
	
	private String userId;
	private String userEmail;
	private boolean trialUsed = false;
	
	
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public boolean isTrialUsed() {
		return trialUsed;
	}

	public void setTrialUsed(boolean trialUsed) {
		this.trialUsed = trialUsed;
	}
	
}
