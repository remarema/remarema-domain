package remarema.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Die Klasse Network ist bla bla.
 * 
 * Im Detail usw, usw.<code>new Network()</code>
 * 
 * <pre>
 * this is a    x
 * look u  a    e
 * </pre>
 * 
 * @author Rebecca vanLangelaan
 *
 */
@Entity
@Table(name = "networks")
public class Network implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "networkID")
	private int networkID;

	@Column(name = "networkName")
	private String networkName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "networkParentID", referencedColumnName = "networkID")
	private Network parent;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	private List<Network> children;

	public Network getParent() {
		return parent;
	}

	public void setParent(Network parent) {
		this.parent = parent;
	}

	public List<Network> getChildren() {
		return children;
	}

	public void addChildren(Network network) {
		if (network == null) {
			throw new IllegalArgumentException(
					"ein Kind-Netzwerk darf nicht null sein");
		}
		if (children == null) {
			children = new ArrayList<Network>();
		}
		children.add(network);
	}

	public void setChildren(List<Network> children) {
		this.children = children;
	}

	public boolean hasParentNetwork() {
		return parent != null;
	}

	public int getParentNetworkID() {
		if (parent == null) {
			throw new IllegalStateException("network hat keinen parent!");
		}
		return parent.getNetworkID();
	}

	@OneToMany(mappedBy = "network", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Node> nodes;

	public Set<Node> getNode() {
		return nodes;
	}

	public void setNode(Set<Node> nodes) {
		this.nodes = nodes;
	}
	
	@ManyToMany
	@JoinTable(name = "networks_has_deploy", joinColumns = @JoinColumn(name = "networks_networkID", referencedColumnName = "networkID"), 
		inverseJoinColumns = @JoinColumn(name = "deploy_deployID", referencedColumnName = "deployID"))
	private Set<Deploy> deploy;

	public Set<Deploy> getDeploy() {
		return deploy;
	}

	public void setDeploy(Set<Deploy> deploy) {
		this.deploy = deploy;
	}
	
	
	
	
	

	public Network() {
	}

	public Network(String networkName, Network networkParentID) {
		this.networkName = networkName;
		this.parent = networkParentID;
	}

	public int getNetworkID() {
		return networkID;
	}

	public void setNetworkID(int networkID) {
		this.networkID = networkID;
	}

	/**
	 * 
	 * @return
	 */
	public String getNetworkName() {
		return networkName;
	}

	/**
	 * ahgsjhdgfjasdhfgdhagjdfgjsdf
	 * 
	 * @param networkName
	 *            @
	 */
	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	@Override
	public String toString() {
		return getNetworkID() + ", " + getNetworkName();
	}

}
