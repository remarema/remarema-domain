package remarema.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="softwareversion")
public class Softwareversion {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="softwareID")
	private int softwareID;
	
	@Column(name="versionName")
	private String versionName;
	
	@Column(name="softwarePath")
	private String softwarePath;
	
	public int getSoftwareID() {
		return softwareID;
	}
	public void setSoftwareID(int softwareID) {
		this.softwareID = softwareID;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getSoftwarePath() {
		return softwarePath;
	}
	public void setSoftwarePath(String softwarePath) {
		this.softwarePath = softwarePath;
	}
	
}
