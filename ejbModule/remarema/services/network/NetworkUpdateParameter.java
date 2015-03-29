package remarema.services.network;

public class NetworkUpdateParameter {
	public int networkID;
	public String networkName;
	public int networkParentID;

	public NetworkUpdateParameter(int networkID, String networkName,
			int networkParentID) {
		this.networkID = networkID;
		this.networkName = networkName;
		this.networkParentID = networkParentID;
	}
}