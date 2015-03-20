package remarema.services.network;

public class CreateNetworkParameter {
	public int networkID;
	public String networkName;
	public int networkParentID;

	public CreateNetworkParameter(String networkName,
			int networkParentID) {
		this.networkName = networkName;
		this.networkParentID = networkParentID;
	}
}