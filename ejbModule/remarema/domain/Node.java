package remarema.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	@Column (name="nodeNetwork")
	private String nodeNetwork;
	@Column (name="nodeIP")
	private String nodeIP;
	@Column (name="softwareVersion")
	private String softwareVersion;
	
	
	
	@ManyToMany(
			cascade = {CascadeType.PERSIST, CascadeType.MERGE},
			mappedBy = "nodes",
			targetEntity = Network.class)
	
	private Set<Network> networks;
	private Set<Network> getNetworks(){
		return networks;
	}
	public void setNetworks(Set<Network> networks){
		this.networks = networks;
	}
	
	@ManyToMany
	private Set<Softwareversion> softwareversions;
	
	
	
	
	
	
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
	public String getNodeNetwork(){
		return nodeNetwork;
	}
	public void setNodeNetwork(String nodeNetwork){
		this.nodeNetwork = nodeNetwork;
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

