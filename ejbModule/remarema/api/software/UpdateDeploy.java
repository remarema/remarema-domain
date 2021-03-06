package remarema.api.software;

/**
 * Dient als DTO. Beinhaltet alle Datenfelder, die zum Bearbeiten einer
 * Softwareverteilung benötigt werden. Enthält auch alle get- und set-Methoden.
 * 
 * @author Rebecca van Langelaan
 *
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import remarema.api.network.NetworkDetail;

public class UpdateDeploy {

	public ArrayList<NetworkDetail> networks = new ArrayList<>();

	public int deployID;
	public Date deployDateTime;
	public Date installationDateTime;
	public int softwareversionID;
	public String softwareversionName;
	private String packageName;
	private int packageID;

	public int getDeployID() {
		return deployID;
	}

	public void setDeployID(int deployID) {
		this.deployID = deployID;
	}

	public Date getDeployDateTime() {
		return deployDateTime;
	}

	public void setDeployDateTime(Date deployDateTime) {
		this.deployDateTime = deployDateTime;
	}

	public Date getInstallationDateTime() {
		return installationDateTime;
	}

	public void setInstallationDateTime(Date installationDateTime) {
		this.installationDateTime = installationDateTime;
	}

	public int getSoftwareversionID() {
		return softwareversionID;
	}

	public void setSoftwareversionID(int softwareversionID) {
		this.softwareversionID = softwareversionID;
	}

	public String getSoftwareversionName() {
		return softwareversionName;
	}

	public void setSoftwareversionName(String softwareversionName) {
		this.softwareversionName = softwareversionName;
	}

	public ArrayList<NetworkDetail> getNetworks() {
		return networks;
	}

	public void setNetworks(ArrayList<NetworkDetail> networks) {
		this.networks = networks;
	}

	public int getPackageID() {
		return packageID;
	}

	public void setPackageID(int packageID) {
		this.packageID = packageID;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

}
