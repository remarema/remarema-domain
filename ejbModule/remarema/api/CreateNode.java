package remarema.api;

import javax.validation.constraints.NotNull;

public class CreateNode {

	@NotNull
	private String nodeName;
	
	@NotNull
	private String nodeIP;
	
	@NotNull
	private String nodeNetworkName;
	
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
}