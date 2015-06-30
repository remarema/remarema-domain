package remarema.api.software;

/**
 * Dient als DTO. Beinhaltet alle Datenfelder, die zum Bearbeiten eines
 * Softwarepackages benötigt werden. Enthält auch alle get- und set-Methoden.
 * 
 * @author Rebecca van Langelaan
 *
 */

public class UpdatePackage {

	public int softwarepackageID;
	public String softwarepackageName;

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
