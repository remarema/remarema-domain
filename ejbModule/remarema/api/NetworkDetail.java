package remarema.api;

public class NetworkDetail {
	
	

	public int networkID;
	public String networkName;
	public int networkParentID;
	
	public NetworkDetail(){
		
	}
	
	public NetworkDetail(int networkID){
		this.networkID = networkID;
	}
	
	public NetworkDetail(int networkID, String networkName, int networkParentID){
		this.networkID = networkID;
		this.networkName = networkName;
		this.networkParentID = networkParentID;
	}
	
	public NetworkDetail(String networkName, int networkParentID){
		this.networkName = networkName;
		this.networkParentID = networkParentID;
	}
	
	public int getNetworkID() {
		return networkID;
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
}
