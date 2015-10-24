package eu.mysmartline.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class PricingDefinition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	private Long longPartId;
	
	private boolean isValid = true;
	private String pricingName;
	private Integer priceInEuro;
	private Integer monthsValid;
	private Integer nrOfLines;
	
	
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
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public String getPricingName() {
		return pricingName;
	}
	public void setPricingName(String pricingName) {
		this.pricingName = pricingName;
	}
	public Integer getPriceInEuro() {
		return priceInEuro;
	}
	public void setPriceInEuro(Integer priceInEuro) {
		this.priceInEuro = priceInEuro;
	}
	public Integer getMonthsValid() {
		return monthsValid;
	}
	public void setMonthsValid(Integer monthsValid) {
		this.monthsValid = monthsValid;
	}
	public Integer getNrOfLines() {
		return nrOfLines;
	}
	public void setNrOfLines(Integer nrOfLines) {
		this.nrOfLines = nrOfLines;
	}
	
}
