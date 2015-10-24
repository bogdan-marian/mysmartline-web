package eu.mysmartline.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.datanucleus.api.jpa.annotations.Extension;

@Entity
public class Line {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	private String userId;
	private String archivedUserId;
	
	/*@OneToMany(mappedBy="line", cascade = CascadeType.ALL)
	private List<LineNumber> lineNumbers = new ArrayList<LineNumber>();*/
	
	private String lineName;
	private String lineLabel;
	private boolean resetNumbers = false;
	private boolean resetByDay = true;
	private Integer notifyBefore;
	private Date validUntil;
	
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
	public String getArchivedUserId() {
		return archivedUserId;
	}
	public void setArchivedUserId(String archivedUserId) {
		this.archivedUserId = archivedUserId;
	}
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public String getLineLabel() {
		return lineLabel;
	}
	public void setLineLabel(String lineLabel) {
		this.lineLabel = lineLabel;
	}
	public boolean isResetNumbers() {
		return resetNumbers;
	}
	public void setResetNumbers(boolean resetNumbers) {
		this.resetNumbers = resetNumbers;
	}
	public boolean isResetByDay() {
		return resetByDay;
	}
	public void setResetByDay(boolean resetByDay) {
		this.resetByDay = resetByDay;
	}
	public Integer getNotifyBefore() {
		return notifyBefore;
	}
	public void setNotifyBefore(Integer notifyBefore) {
		this.notifyBefore = notifyBefore;
	}
	public Date getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}
	
}
