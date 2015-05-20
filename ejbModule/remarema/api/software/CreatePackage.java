package remarema.api.software;

import javax.validation.constraints.NotNull;

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
