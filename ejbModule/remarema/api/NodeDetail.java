package remarema.api;

import remarema.domain.Node;

public class NodeDetail {

	public int nodeID;
	public String nodeName;
	public String nodeIP;
	public int nodeNetworkID;


	
	public NodeDetail() {

	}
	
	public NodeDetail(int nodeID){
		this.nodeID = nodeID;
	}
	
	public NodeDetail(int nodeID, String nodeName, String nodeIP, int nodeNetworkID){
		this.nodeID = nodeID;
		this.nodeName = nodeName;
		this.nodeIP = nodeIP;
		this.nodeNetworkID = nodeNetworkID;
	}
	
	
	public NodeDetail(String nodeName, String nodeIP, int nodeNetworkID){
		this.nodeName = nodeName;
		this.nodeIP = nodeIP;
		this.nodeNetworkID = nodeNetworkID;
	}
	
	public int getNodeID() {
		return nodeID;
	}

	
	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeIP() {
		return nodeIP;
	}

	public void setNodeIP(String nodeIP) {
		this.nodeIP = nodeIP;
	}

	public int getNodeNetworkID() {
		return nodeNetworkID;
	}

	public void setNodeNetworkID(int nodeNetworkID) {
		this.nodeNetworkID = nodeNetworkID;
	}

	@Override
	public String toString() {
		return getNodeID() +", " + getNodeName() + ", " + getNodeIP() + ", " + getNodeNetworkID();
	}

}
