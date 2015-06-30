package remarema.api.network;

/**
 * Diese Klasse besitzt Datenfelder, die zum Bearbeiten von Netzwerken benötigt
 * werden. Hier gibt es ebenfalls get- und set-Methoden. Dient als DTO.
 * 
 * @author Rebecca van Langelaan
 *
 */
public class UpdateNetwork {

	private int networkID;
	private String networkName;
	private String networkParentID;
	private String networkParentName;

	public int getNetworkID() {
		return networkID;
	}

	public void setNetworkID(int networkID) {
		this.networkID = networkID;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public String getNetworkParentID() {
		return networkParentID;
	}

	public void setNetworkParentID(String networkParentID) {
		this.networkParentID = networkParentID;
	}

	public String getNetworkParentName() {
		return networkParentName;
	}

	public void setNetworkParentName(String networkParentName) {
		this.networkParentName = networkParentName;
	}

}
