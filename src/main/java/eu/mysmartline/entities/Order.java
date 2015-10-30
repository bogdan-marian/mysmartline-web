package eu.mysmartline.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.datanucleus.api.jpa.annotations.Extension;

import com.google.appengine.api.datastore.Key;

@Entity
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	private String userId;
	
	/**this is the id that will be used to identify
	 * the order when received from the payment service
	 */
	private String payServiceId;
	private String payServiceName;
	
	private Key pricingDefinitionId;
	private Integer nrOfPricingUnits;
	
	private String pricingName;
	private Integer pricingValue;
	private Integer orderValue;
	private Date dateOfOrder;
	private Date dateOfPayment;
	private boolean processed = false;
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
	public String getPayServiceId() {
		return payServiceId;
	}
	public void setPayServiceId(String payServiceId) {
		this.payServiceId = payServiceId;
	}
	public String getPayServiceName() {
		return payServiceName;
	}
	public void setPayServiceName(String payServiceName) {
		this.payServiceName = payServiceName;
	}
	public Key getPricingDefinitionId() {
		return pricingDefinitionId;
	}
	public void setPricingDefinitionId(Key pricingDefinitionId) {
		this.pricingDefinitionId = pricingDefinitionId;
	}
	public Integer getNrOfPricingUnits() {
		return nrOfPricingUnits;
	}
	public void setNrOfPricingUnits(Integer nrOfPricingUnits) {
		this.nrOfPricingUnits = nrOfPricingUnits;
	}
	public String getPricingName() {
		return pricingName;
	}
	public void setPricingName(String pricingName) {
		this.pricingName = pricingName;
	}
	public Integer getPricingValue() {
		return pricingValue;
	}
	public void setPricingValue(Integer pricingValue) {
		this.pricingValue = pricingValue;
	}
	public Integer getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
	}
	public Date getDateOfOrder() {
		return dateOfOrder;
	}
	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	public Date getDateOfPayment() {
		return dateOfPayment;
	}
	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
	public boolean isProcessed() {
		return processed;
	}
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
}
