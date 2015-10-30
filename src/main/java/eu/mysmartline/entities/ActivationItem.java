package eu.mysmartline.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.datanucleus.api.jpa.annotations.Extension;

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
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	private String userId;
	
	private Key orderKey;
	private Integer nrOfMonts;
	private boolean archived = false;
	/**
	 * Mark this activationItem to be used when you effectively use the item
	 */
	private boolean identified = false;
	//getters and setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
