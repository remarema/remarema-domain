package remarema.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	/*
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="softwarepackage_softwarepackageID", nullable=false)
	private Softwarepackage softwarepackage;
	public Softwarepackage getSoftwarepackage(){
		return softwarepackage;
	}
	*/
	
}
