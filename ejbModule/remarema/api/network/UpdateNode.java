package remarema.api.network;

/**
 * Diese Klasse besitzt Datenfelder, die zum Bearbeiten von Clients benötigt
 * werden. Hier gibt es ebenfalls get- und set-Methoden. Dient als DTO.
 * 
 * @author Rebecca van Langelaan
 *
 */
public class UpdateNode {

	private int nodeID;
	private String nodeName;
	private String nodeIP;
	private int nodeNetworkID;
	private String nodeNetworkName;

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

	public String getNodeNetworkName() {
		return nodeNetworkName;
	}

	public void setNodeNetworkName(String nodeNetworkName) {
		this.nodeNetworkName = nodeNetworkName;
	}

	public int getNodeNetworkID() {
		return nodeNetworkID;
	}

	public void setNodeNetworkID(int nodeNetworkID) {
		this.nodeNetworkID = nodeNetworkID;
	}

}
