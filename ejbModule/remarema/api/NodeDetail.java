package remarema.api;


public class NodeDetail {

	public int nodeID;
	private String nodeName;
	private String nodeIP;
	private String softwareversion;
	private int nodeNetworkID;

	public int getNodeID() {
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

	public String getNodeIP() {
		return nodeIP;
	}

	public void setNodeIP(String nodeIP) {
		this.nodeIP = nodeIP;
	}

	public String getSoftwareversion() {
		return softwareversion;
	}

	public void setSoftwareversion(String softwareversion) {
		this.softwareversion = softwareversion;
	}

	public int getNodeNetworkID() {
		return nodeNetworkID;
	}

	public void setNodeNetworkID(int nodeNetworkID) {
		this.nodeNetworkID = nodeNetworkID;
	}

	@Override
	public String toString() {
		return getNodeID() + ", " + getNodeName() + ", " + getNodeIP() + ", "
				+ getNodeNetworkID();
	}

}
