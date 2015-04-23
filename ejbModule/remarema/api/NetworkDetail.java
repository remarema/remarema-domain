package remarema.api;

public class NetworkDetail {
	
	public int networkID;
	public String networkName;
	public int networkParentID;
	public String networkParentName;
	
	public NetworkDetail(){
		
	}
	
	public int getNetworkID() {
		return networkID;
	}
	public void setNetworkID(int networkID) {
		this.networkID = networkID;
	}

	
	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public int getNetworkParentID() {
		return networkParentID;
	}

	public void setNetworkParentID(int networkParentID) {
		this.networkParentID = networkParentID;
	}

	public String getNetworkParentName() {
		return networkParentName;
	}

	public void setNetworkParentName(String networkParentName) {
		this.networkParentName = networkParentName;
	}
}
