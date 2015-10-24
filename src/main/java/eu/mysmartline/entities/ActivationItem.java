package eu.mysmartline.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

/**
 * An actiationItem is intended to be used for
 * - Create one line
 * - Extend the validity of a line
 * @author bogdan
 *
 */
@Entity
public class ActivationItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	private Long longPartId;
	private String userId;
	
	private Key orderKey;
	private Integer nrOfMonts;
	private boolean archived = false;
	
	/**
	 * Mark this activationItem to be used when you effectively use the item
	 */
	private boolean identified = false;
	
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
	public Key getOrderKey() {
		return orderKey;
	}
	public void setOrderKey(Key orderKey) {
		this.orderKey = orderKey;
	}
	public Integer getNrOfMonts() {
		return nrOfMonts;
	}
	public void setNrOfMonts(Integer nrOfMonts) {
		this.nrOfMonts = nrOfMonts;
	}
	public boolean isArchived() {
		return archived;
	}
	public void setArchived(boolean archived) {
		this.archived = archived;
	}
	public boolean isIdentified() {
		return identified;
	}
	public void setIdentified(boolean identified) {
		this.identified = identified;
	}
	
}
