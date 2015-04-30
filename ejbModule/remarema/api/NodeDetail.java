package remarema.api;

import remarema.services.network.IPAddress;


public class NodeDetail {

	public Integer nodeID;
	private String nodeName;
	private IPAddress nodeIP;
	private String softwareversion;
	private Integer nodeNetworkID;
	private String nodeNetworkName;

	public Integer getNodeID() {
		return nodeID;
	}

	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public IPAddress getNodeIP() {
		return nodeIP;
	}

	public void setNodeIP(IPAddress nodeIP) {
		this.nodeIP = nodeIP;
	}

	public String getSoftwareversion() {
		return softwareversion;
	}

	public void setSoftwareversion(String softwareversion) {
		this.softwareversion = softwareversion;
	}

	public Integer getNodeNetworkID() {
		return nodeNetworkID;
	}

	public void setNodeNetworkID(int nodeNetworkID) {
		this.nodeNetworkID = nodeNetworkID;
	}

	public String getNodeNetworkName() {
		return nodeNetworkName;
	}

	public void setNodeNetworkName(String nodeNetworkName) {
		this.nodeNetworkName = nodeNetworkName;
	}

}
