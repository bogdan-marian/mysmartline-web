package eu.mysmartline.models;

public class OrderModel {
	private Long orderId;
	private Long priceDefinitionId;
	private Integer pricingValue;
	private Integer nrOfPricingUnits;
	private Integer orderValue;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getPriceDefinitionId() {
		return priceDefinitionId;
	}
	public void setPriceDefinitionId(Long priceDefinitionId) {
		this.priceDefinitionId = priceDefinitionId;
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
}
