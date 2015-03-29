package remarema.services.network;

public class NodeUpdateParameter {
	public int nodeID;
	public String nodeName;
	public String nodeIP;
	public int nodeNetworkID;

	public NodeUpdateParameter(int nodeID, String nodeName, String nodeIP,
			int nodeNetworkID) {
		this.nodeID = nodeID;
		this.nodeName = nodeName;
		this.nodeIP = nodeIP;
		this.nodeNetworkID = nodeNetworkID;
	}
}