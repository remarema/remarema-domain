package remarema.api;

public class CreateNode {
	public int nodeID;
	public String nodeName;
	public String nodeNetwork;
	public String nodeIP;
	public String softwareVersion;

	public CreateNode(int nodeID, String nodeName, String nodeNetwork,
			String nodeIP, String softwareVersion) {
		this.nodeID = nodeID;
		this.nodeName = nodeName;
		this.nodeNetwork = nodeNetwork;
		this.nodeIP = nodeIP;
		this.softwareVersion = softwareVersion;
	}
	
	
}