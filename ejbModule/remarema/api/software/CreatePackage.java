package remarema.api.software;

import javax.validation.constraints.NotNull;

/**
 * Dient als DTO. Beinhaltet alle Datenfelder, die zum Anlegen eines
 * Softwarepackages benötigt werden. Enthält auch alle get- und set-Methoden.
 * 
 * @author Rebecca van Langelaan
 *
 */

public class CreatePackage {

	@NotNull
	public String softwarepackageName;

	public String getSoftwarepackageName() {
		return softwarepackageName;
	}

	public void setSoftwarepackageName(String softwarepackageName) {
		this.softwarepackageName = softwarepackageName;
	}

}
