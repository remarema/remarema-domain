package remarema.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeployDetail {

	public int deployID;
	public Date deployDateTime;
	public Date installationDateTime;
	public int softwareID;
	public String versionName;
	public static ArrayList<NetworkDetail> networks;
	private String packageName;
	private int packageID;

	public int getDeployID() {
		return deployID;
	}

	public void setDeployID(int deployID) {
		this.deployID = deployID;
	}

	@SuppressWarnings("deprecation")
	public String getDeployDateTime() {
		Date t = deployDateTime;
		int year = t.getYear() + 1900;

		return t.getDate() + "." + t.getMonth() + "." + year + " "
				+ t.getHours() + ":" + t.getMinutes();
	}

	public void setDeployDateTime(Date deployDateTime) {
		this.deployDateTime = deployDateTime;
	}

	@SuppressWarnings("deprecation")
	public String getInstallationDateTime() {
		Date t = installationDateTime;
		int year = t.getYear() + 1900;

		return t.getDate() + "." + t.getMonth() + "." + year + " "
				+ t.getHours() + ":" + t.getMinutes();

	}

	public void setInstallationDateTime(Date installationDateTime) {
		this.installationDateTime = installationDateTime;
	}

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

	public ArrayList<NetworkDetail> getNetworks() {
		return networks;
	}

	public void setNetworks(ArrayList<NetworkDetail> networks) {
		this.networks = networks;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public void setPackageID(int packageID) {
		this.packageID = packageID;
	}

	public int getPackageID() {
		return packageID;
	}

}
