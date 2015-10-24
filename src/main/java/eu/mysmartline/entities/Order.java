package eu.mysmartline.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	private Long longPartId;
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
	public Key getPricingDefinitionId() {
		return pricingDefinitionId;
	}
	public void setPricingDefinitionId(Key pricingDefinitionId) {
		this.pricingDefinitionId = pricingDefinitionId;
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
	public Integer getNrOfPricingUnits() {
		return nrOfPricingUnits;
	}
	public void setNrOfPricingUnits(Integer nrOfPricingUnits) {
		this.nrOfPricingUnits = nrOfPricingUnits;
	}
	public Integer getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(Integer orderValue) {
		this.orderValue = orderValue;
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
