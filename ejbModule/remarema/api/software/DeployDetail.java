package remarema.api.software;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.helpers.FormattingTuple;

import remarema.api.network.NetworkDetail;

public class DeployDetail {

	public int deployID;
	public Date deployDateTime;
	public Date installationDateTime;
	public int softwareID;
	public String versionName;
	public ArrayList<NetworkDetail> networks = new ArrayList<>();
	private String packageName;
	private int packageID;

	public int getDeployID() {
		return deployID;
	}

	public void setDeployID(int deployID) {
		this.deployID = deployID;
	}

	public String getDeployDateTime() {
		Date t = deployDateTime;
		return formatDateTime(t);
	}


	public void setDeployDateTime(Date deployDateTime) {
		this.deployDateTime = deployDateTime;
	}

	public String getInstallationDateTime() {
		Date t = installationDateTime;
		return formatDateTime(t);

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
	
	private String formatDateTime(Date t) {
		int year = t.getYear() + 1900;
		String month = ""+(t.getMonth()+1);
		String hours = ""+t.getHours();
		String minutes = ""+ t.getMinutes();
		String day = ""+ t.getDate();
		
		if(t.getDate() < 10){
			day = "0" + day;
		}
		if(t.getHours() < 10){
			hours = "0" + hours; 
		}
		if(t.getMinutes() < 10){
			minutes = "0" + minutes; 
		}
		if(t.getMonth() < 9){
			month = "0" + month; 
		}
		return day + "." + month + "." + year + " "
				+ hours + ":" + minutes;
	}
	
	@Override
	public String toString(){
		return "[" + super.toString() + ", " + getDeployID() + ", " + getSoftwareID() + ", " + getNetworks() + "@" + Integer.toHexString(getNetworks().hashCode()) + "]";
		
	}

}
