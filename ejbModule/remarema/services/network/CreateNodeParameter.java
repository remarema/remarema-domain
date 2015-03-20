package remarema.services.network;

public class CreateNodeParameter {
	public int nodeID;
	public String nodeName;
	public String nodeIP;
	public String softwareVersion;
	public int nodeNetworkID;

	public CreateNodeParameter(String nodeName, int nodeNetworkID,
			String nodeIP) {
		this.nodeName = nodeName;
		this.nodeNetworkID = nodeNetworkID;
		this.nodeIP = nodeIP;
	}
	
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
}