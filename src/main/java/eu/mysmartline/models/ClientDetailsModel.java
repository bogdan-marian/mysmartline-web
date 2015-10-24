package eu.mysmartline.models;

/**
 * The ClientDetailsModel is used to collect the information
 * that will be presented to the next potential client.
 */
public class ClientDetailsModel {
	private String lineId;
	private String lineName;
	private String currentNumber;
	private String nextProbableNumber;
	private int clientsAhead;
	private int probableWaitTime;
	private String notifType;
	private String email;
	//start of gennerated content
	
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getCurrentNumber() {
		return currentNumber;
	}
	public void setCurrentNumber(String currentNumber) {
		this.currentNumber = currentNumber;
	}
	public String getNextProbableNumber() {
		return nextProbableNumber;
	}
	public void setNextProbableNumber(String nextProbableNumber) {
		this.nextProbableNumber = nextProbableNumber;
	}
	public int getClientsAhead() {
		return clientsAhead;
	}
	public void setClientsAhead(int clientsAhead) {
		this.clientsAhead = clientsAhead;
	}
	public int getProbableWaitTime() {
		return probableWaitTime;
	}
	public void setProbableWaitTime(int probableWaitTime) {
		this.probableWaitTime = probableWaitTime;
	}
	public String getNotifType() {
		return notifType;
	}
	public void setNotifType(String notifType) {
		this.notifType = notifType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}