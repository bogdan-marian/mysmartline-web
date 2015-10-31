package eu.mysmartline.models;

public class OrderModel {
	private String orderId;
	private String priceDefinitionId;
	private Integer pricingValue;
	private Integer nrOfPricingUnits;
	private Integer orderValue;
	//generated items
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPriceDefinitionId() {
		return priceDefinitionId;
	}
	public void setPriceDefinitionId(String priceDefinitionId) {
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
