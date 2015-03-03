package remarema.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name = "networks")
public class Network implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="networkID")
	private int networkID;
	
	@Column(name="networkName")
	private String networkName;
	
	@Column (name="networkIP")
	private String networkIP;
	
	/*
	private Collection<Node> nodes;
	@ManyToMany(targetEntity = remarema.domain.Node.class, 
			cascade={CascadeType.PERSIST, CascadeType.MERGE})
	
	@JoinTable(name = "network_has_node",
		joinColumns = {@JoinColumn(name="networks_networkID")},
		inverseJoinColumns = @JoinColumn(name="nodes_nodeID")
	)
	
	
	private Collection<Node> getNodes(){
		return nodes;
	}
	public void setNodes(Collection<Node> nodes){
		this.nodes = nodes;
	}
	*/
	
	
	
	public Network(){
	}
	
	public Network(int networkID){
		this.networkID = networkID;
	}
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

	public String getNetworkIP() {
		return networkIP;
	}

	public void setNetworkIP(String networkIP) {
		this.networkIP = networkIP;
	}
	
	@Override
	public String toString(){
		return getNetworkID() + ", " + getNetworkName() + ", " + getNetworkIP();
	}
}
