package remarema.services.network;

public class CreateNodeParameter {
	public String nodeName;
	public String nodeIP;
	public int nodeNetworkID;

	public CreateNodeParameter(String nodeName, String nodeIP,  int nodeNetworkID) {
		this.nodeName = nodeName;
		this.nodeIP = nodeIP;
		this.nodeNetworkID = nodeNetworkID;
	}
}