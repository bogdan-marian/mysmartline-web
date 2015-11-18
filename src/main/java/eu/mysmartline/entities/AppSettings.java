package eu.mysmartline.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AppSettings {
	@Id
	private String id;
	private String value;
	//getters and setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
