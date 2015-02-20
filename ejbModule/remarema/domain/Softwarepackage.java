package remarema.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="softwarepackage")
public class Softwarepackage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="softwarepackageID")
	private int softwarepackageID;
	
	@Column(name="softwarepackageName")
	private String softwarepackageName;

	public int getSoftwarepackageID() {
		return softwarepackageID;
	}

	public void setSoftwarepackageID(int softwarepackageID) {
		this.softwarepackageID = softwarepackageID;
	}

	public String getSoftwarepackageName() {
		return softwarepackageName;
	}

	public void setSoftwarepackageName(String softwarepackageName) {
		this.softwarepackageName = softwarepackageName;
	}
}
