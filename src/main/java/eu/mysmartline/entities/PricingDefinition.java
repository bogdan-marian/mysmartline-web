package eu.mysmartline.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.datanucleus.api.jpa.annotations.Extension;

import com.google.appengine.api.datastore.Key;

@Entity
public class PricingDefinition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	
	private boolean isValid = true;
	private String pricingName;
	private Integer priceInEuro;
	private Integer monthsValid;
	private Integer nrOfLines;
	//generated items
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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