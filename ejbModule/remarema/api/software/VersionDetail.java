package remarema.api.software;

/**
 * Diese Klasse dient als DTO. Sie enthält alle Informationen, die es zu einer
 * Softwareversion gibt. Vorhanden sind auch alle get- und set-Methoden.
 * 
 * @author Rebecca van Langelaan
 *
 */
public class VersionDetail {

	public int softwareID;
	public String softwareName;
	public String softwarePath;
	private int softwarepackageID;
	private String softwarepackageName;

	public int getSoftwareID() {
		return softwareID;
	}

	public void setSoftwareID(int softwareID) {
		this.softwareID = softwareID;
	}

	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	public String getSoftwarePath() {
		return softwarePath;
	}

	public void setSoftwarePath(String softwarePath) {
		this.softwarePath = softwarePath;
	}

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
