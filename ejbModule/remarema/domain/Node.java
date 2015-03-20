package remarema.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="nodes")
public class Node implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="nodeID")
	private int nodeID;
	@Column(name="nodeName")
	private String nodeName;
	@Column (name="nodeNetworkID")
	private int nodeNetworkID;
	@Column (name="nodeIP")
	private String nodeIP;
	@Column (name="softwareVersion")
	private String softwareVersion;
	
	/*
	
	@JoinColumn(name="networks_networkID", referencedColumnName = "networks_networkID")
	@ManyToOne(fetch = FetchType.LAZY)
	private Network network;
	
	public Network getNetwork(){
		return network;
	}
	public void setNetwork(Network network){
		this.network = network;
	}
	
	
	@ManyToMany
	@JoinTable(name="nodes_has_softwareversion", 
			joinColumns = @JoinColumn(name="nodes_nodeID", referencedColumnName="nodeID"),
			inverseJoinColumns=@JoinColumn(
					name="softwareversion_softwareID", referencedColumnName="softwareID"))
	private Set<Softwareversion> softwareversions;
	
	public Set<Softwareversion> getSoftwareversion(){
		return softwareversions;
	}
	public void setSoftwareversion(Set<Softwareversion> softwareversions){
		this.softwareversions = softwareversions;
	}
	
	
	@ManyToMany
	@JoinTable(name="deploy_has_nodes", 
			joinColumns = @JoinColumn(name="deploy_deployID", referencedColumnName="deployID"),
			inverseJoinColumns=@JoinColumn(
					name="nodes_nodeID", referencedColumnName="nodeID"))
	private Set<Deploy> deploy;
	
	public Set<Deploy> getDeploy(){
		return deploy;
	}
	public void setDeploy(Set<Deploy> deploy){
		this.deploy = deploy;
	}
	
	*/
	
	public Node(){
	}
	
	public Node(int nodeID){
		this.nodeID = nodeID;
	}
	public int getID(){
		return nodeID;
	}
	public void setID(int nodeID){
		this.nodeID = nodeID;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public int getNodeNetworkID(){
		return nodeNetworkID;
	}
	public void setNodeNetworkID(int nodeNetworkID){
		this.nodeNetworkID = nodeNetworkID;
	}
	public String getNodeIP(){
		return nodeIP;
	}
	public void setNodeIP(String nodeIP){
		this.nodeIP = nodeIP;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
	
	@Override
	public String toString(){
		return getID() + ", " + getNodeName()+ ", " + getNodeIP();
	}
} 

