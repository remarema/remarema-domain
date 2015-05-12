package remarema.api;

import java.util.ArrayList;
import java.util.List;

import org.h2.util.DateTimeUtils;


public class CreateDeployment {
	public static ArrayList<NetworkDetail> networks = new ArrayList<>();

	public int deployID;
	public DateTimeUtils deployDateTime;
	public DateTimeUtils installationDateTime;
	public int softwareversionID;
	public String softwareversionName;

	public int getDeployID() {
		return deployID;
	}

	public void setDeployID(int deployID) {
		this.deployID = deployID;
	}

	public DateTimeUtils getDeployDateTime() {
		return deployDateTime;
	}

	public void setDeployDateTime(DateTimeUtils deployDateTime) {
		this.deployDateTime = deployDateTime;
	}

	public DateTimeUtils getInstallationDateTime() {
		return installationDateTime;
	}

	public void setInstallationDateTime(DateTimeUtils installationDateTime) {
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

	public static ArrayList<NetworkDetail> getNetworks() {
		return networks;
	}

	public  void setNetworks(ArrayList<NetworkDetail> networks) {
		this.networks = networks;
	}
	

}
