package remarema.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import remarema.services.network.IPAddress;

@Entity
@Table(name = "nodes")
public class Node implements Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "nodeID")
	private int nodeID;

	@Column(name = "nodeName")
	private String nodeName;
	@Column(name = "nodeIP")
	private int nodeIP;
	@Column(name = "softwareVersion")
	private String softwareVersion;

	@JoinColumn(name = "networks_networkID", referencedColumnName = "networkID")
	@ManyToOne(fetch = FetchType.LAZY)
	private Network network;

	public Network getNodeNetwork() {
		return network;
	}

	public void setNodeNetwork(Network network) {
		this.network = network;
	}

	public boolean hasNetwork() {
		return network != null;
	}

	public int getNodeNetworkID() {
		if (network == null) {
			throw new IllegalStateException("node hat kein netzwerk!");
		}
		return network.getNetworkID();
	}

	@ManyToMany
	@JoinTable(name = "nodes_has_softwareversion", joinColumns = @JoinColumn(name = "nodes_nodeID", referencedColumnName = "nodeID"), 
		inverseJoinColumns = @JoinColumn(name = "softwareversion_softwareID", referencedColumnName = "softwareID"))
	private Set<Softwareversion> softwareversions;

	public Set<Softwareversion> getSoftwareversion() {
		return softwareversions;
	}

	public void setSoftwareversion(Set<Softwareversion> softwareversions) {
		this.softwareversions = softwareversions;
	}

	public Node() {
	}

	public Node(String nodeName, Network network) {
		this.nodeName = nodeName;
		this.network = network;
	}

	public int getID() {
		return nodeID;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public int getNodeIP() {
		return nodeIP;
	}

	public void setNodeIP(int nodeIPInt) {
		this.nodeIP = nodeIPInt;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	@Override
	public String toString() {
		return getID() + ", " + getNodeName() + ", " + getNodeIP() + ", "
				+ getNodeNetwork();
	}

}
