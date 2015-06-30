package remarema.api.network;

/**
 * Diese Klasse dient als DTO. Sie enthält alle Informationen, die es zu einem
 * Netzwerk gibt. Vorhanden sind auch alle get- und set-Methoden.
 * 
 * @author Rebecca van Langelaan
 *
 */
public class NetworkDetail {

	public Integer networkID;
	public String networkName;
	public Integer networkParentID;
	public String networkParentName;

	public NetworkDetail() {

	}

	public Integer getNetworkID() {
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

	public Integer getNetworkParentID() {
		return networkParentID;
	}

	public void setNetworkParentID(int networkParentID) {
		this.networkParentID = networkParentID;
	}

	public String getNetworkParentName() {
		return networkParentName;
	}

	public void setNetworkParentName(String networkParentName) {
		this.networkParentName = networkParentName;
	}

	@Override
	public String toString() {
		return "[" + super.toString() + ", networkName = " + getNetworkName()
				+ "]";

	}
}
