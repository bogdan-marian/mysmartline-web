package eu.mysmartline.models;

public class RegistrationResultModel {
	public static enum ReasonNotSuccesfull {
		ALREADY_IN_LINE, PERSISTANCE_ERROR, MISTERY
	}
	
	
	
	private boolean succesfullRegistration;
	private ReasonNotSuccesfull reasonNotSuccesfull;
	private Long longNotificationItemId;
	
	public RegistrationResultModel() {
	}
	
	public RegistrationResultModel(RegistrationResultModel.ReasonNotSuccesfull reson){
		succesfullRegistration = false;
		switch (reson) {
		case ALREADY_IN_LINE:
			succesfullRegistration = false;
			reasonNotSuccesfull = RegistrationResultModel.ReasonNotSuccesfull.ALREADY_IN_LINE;
			break;
		case PERSISTANCE_ERROR:
			succesfullRegistration = false;
			reasonNotSuccesfull = RegistrationResultModel.ReasonNotSuccesfull.PERSISTANCE_ERROR;
			break;
		case MISTERY:
			succesfullRegistration = false;
			reasonNotSuccesfull = RegistrationResultModel.ReasonNotSuccesfull.MISTERY;
			break;
		}
	}
	public RegistrationResultModel(Long longNotificationItemId){
		this.longNotificationItemId = longNotificationItemId;
		succesfullRegistration = true;
	}
	
	//start getters and setters
	public boolean isSuccesfullRegistration() {
		return succesfullRegistration;
	}
	public void setSuccesfullRegistration(boolean succesfullRegistration) {
		this.succesfullRegistration = succesfullRegistration;
	}
	public Long getLongNotificationItemId() {
		return longNotificationItemId;
	}
	public void setLongNotificationItemId(Long longNotificationItemId) {
		this.longNotificationItemId = longNotificationItemId;
	}
	public ReasonNotSuccesfull getReasonNotSuccesfull() {
		return reasonNotSuccesfull;
	}
	public void setReasonNotSuccesfull(ReasonNotSuccesfull reasonNotSuccesfull) {
		this.reasonNotSuccesfull = reasonNotSuccesfull;
	}
	
}
