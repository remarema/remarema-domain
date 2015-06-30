package remarema.api.network;

import javax.validation.constraints.NotNull;

/**
 * Die Klasse <code>CreateNode</code> enthält alle Datenfelder, die zum Anlegen
 * von Clients benötigt werden. Get- und Set-Methoden sind ebenfalls vorhanden.
 * Dient als DTO.
 * 
 * @author Rebecca van Langelaan
 *
 */
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