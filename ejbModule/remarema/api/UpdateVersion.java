package remarema.api;

public class UpdateVersion {
	
	public int softwareID;
	public String softwareName;
	public String softwarePath;
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

	public String getSoftwarepackageName() {
		return softwarepackageName;
	}

	public void setSoftwarepackageName(String softwarepackageName) {
		this.softwarepackageName = softwarepackageName;
	}

}
