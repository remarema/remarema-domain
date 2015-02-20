package remarema.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="deploy")
public class Deploy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column (name="deployID")
	private int deployID;
	
	@Column (name="deployDateTime")
	private String deployDateTime;
	
	@Column (name="installationDateTime")
	private String installationDateTime;

	public int getDeployID() {
		return deployID;
	}

	public void setDeployID(int deployID) {
		this.deployID = deployID;
	}

	public String getDeployDateTime() {
		return deployDateTime;
	}

	public void setDeployDateTime(String deployDateTime) {
		this.deployDateTime = deployDateTime;
	}

	public String getInstallationDateTime() {
		return installationDateTime;
	}

	public void setInstallationDateTime(String installationDateTime) {
		this.installationDateTime = installationDateTime;
	}
	
}
