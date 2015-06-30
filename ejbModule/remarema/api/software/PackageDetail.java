package remarema.api.software;

/**
 * Diese Klasse dient als DTO. Sie enthält alle Informationen, die es zu einem
 * Softwarepackage gibt. Vorhanden sind auch alle get- und set-Methoden.
 * 
 * @author Rebecca van Langelaan
 *
 */
public class PackageDetail {

	private Integer softwarepackageID;
	public String softwarepackageName;

	public Integer getSoftwarepackageID() {
		return softwarepackageID;
	}

	public void setSoftwarepackageID(Integer softwarepackageID) {
		this.softwarepackageID = softwarepackageID;
	}

	public String getSoftwarepackageName() {
		return softwarepackageName;
	}

	public void setSoftwarepackageName(String softwarepackageName) {
		this.softwarepackageName = softwarepackageName;
	}

}
