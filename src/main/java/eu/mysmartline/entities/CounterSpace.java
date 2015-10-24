package eu.mysmartline.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class CounterSpace {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	private Long longPartId;
	private String userId;
	
	private Date dateSent;
	private String type;//
	private String receaver;
	
	
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
	public Date getDateSent() {
		return dateSent;
	}
	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReceaver() {
		return receaver;
	}
	public void setReceaver(String receaver) {
		this.receaver = receaver;
	}
}
