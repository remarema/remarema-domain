package remarema.domain.network;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import remarema.domain.node.*;

@Entity
@Table(name = "networks")
public class Network {
	
	
	@Id
	@GeneratedValue
	@Column(name="networkID")
	public int networkID;
	
	@Column(name="networkName")
	public String networkName;
	
	@Column (name="networkIP")
	public String networkIP;
	
	/*
	
	public Collection<Node> nodes;
	@ManyToMany
	@JoinTable(name="network_has_node",
		joinColumns = {@JoinColumn(name="networkID", referencedColumnName="ID")},
		inverseJoinColumns = {@JoinColumn(name="nodeID", referencedColumnName="ID")})
	
	public Collection<Node> getNodes(){
		return this.nodes;
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
}
