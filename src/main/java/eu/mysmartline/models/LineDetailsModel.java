package eu.mysmartline.models;

public class LineDetailsModel {
	private Long companyId;
	private String companyName;
	private Long id;
	private String name;
	private String prefix;
	private Long curentNumberId;
	private	Long curentNumber;
	private String curentNumberDisplay;
	private Long lastNumber;
	private String lastNumberDisplay;
	private Long numberOfWaitingClients;
	private Long nextProbableNumber;
	private String nextProbableDisplayNumber;
	private String registrationUrl;
	private Long nextClientNumber;
	private String nextClientDisplayNumber;
	private Long totalClients;
	private String probableWaitTime;
	private boolean isEmty;
	
	
	
	public String getProbableWaitTime() {
		return probableWaitTime;
	}
	public void setProbableWaitTime(String probableWaitTime) {
		this.probableWaitTime = probableWaitTime;
	}
	public Long getTotalClients() {
		return totalClients;
	}
	public void setTotalClients(Long totalClients) {
		this.totalClients = totalClients;
	}
	public Long getNextClientNumber() {
		return nextClientNumber;
	}
	public void setNextClientNumber(Long nextClientNumber) {
		this.nextClientNumber = nextClientNumber;
	}
	public String getNextClientDisplayNumber() {
		return nextClientDisplayNumber;
	}
	public void setNextClientDisplayNumber(String nextClientDisplayNumber) {
		this.nextClientDisplayNumber = nextClientDisplayNumber;
	}
	public boolean isEmty() {
		return isEmty;
	}
	public void setEmty(boolean isEmty) {
		this.isEmty = isEmty;
	}
	public Long getNumberOfWaitingClients() {
		return numberOfWaitingClients;
	}
	public void setNumberOfWaitingClients(Long numberOfWaitingClients) {
		this.numberOfWaitingClients = numberOfWaitingClients;
	}
	public String getRegistrationUrl() {
		return registrationUrl;
	}
	public void setRegistrationUrl(String registrationUrl) {
		this.registrationUrl = registrationUrl;
	}
	public Long getNextProbableNumber() {
		return nextProbableNumber;
	}
	public void setNextProbableNumber(Long nextProbableNumber) {
		this.nextProbableNumber = nextProbableNumber;
	}
	public String getNextProbableDisplayNumber() {
		return nextProbableDisplayNumber;
	}
	public void setNextProbableDisplayNumber(String nextProbableDisplayNumber) {
		this.nextProbableDisplayNumber = nextProbableDisplayNumber;
	}
	public String getLastNumberDisplay() {
		return lastNumberDisplay;
	}
	public void setLastNumberDisplay(String lastNumberDisplay) {
		this.lastNumberDisplay = lastNumberDisplay;
	}
	public Long getLastNumber() {
		return lastNumber;
	}
	public void setLastNumber(Long lastNumber) {
		this.lastNumber = lastNumber;
	}
	public String getCurentNumberDisplay() {
		return curentNumberDisplay;
	}
	public void setCurentNumberDisplay(String curentNumberDisplay) {
		this.curentNumberDisplay = curentNumberDisplay;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getCurentNumberId() {
		return curentNumberId;
	}
	public void setCurentNumberId(Long curentNumberId) {
		this.curentNumberId = curentNumberId;
	}
	public Long getCurentNumber() {
		return curentNumber;
	}
	public void setCurentNumber(Long curentNumber) {
		this.curentNumber = curentNumber;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
}
