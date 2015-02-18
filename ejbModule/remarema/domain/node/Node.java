package remarema.domain.node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import remarema.domain.network.*;

@Entity
@Table(name="nodes")
public class Node implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="nodeID")
	private int nodeID;
	@Column(name="nodeName")
	private String nodeName;
	@Column(name="nodeCredential")
	private String nodeCredential;
	@Column (name="nodeNetwork")
	private String nodeNetwork;
	@Column (name="nodeIP")
	private String nodeIP;
	@Column (name="softwareVersion")
	private String softwareVersion;
	

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
	public String getNodeCredential() {
		return nodeCredential;
	}
	public void setNodeCredential(String nodeCredential) {
		this.nodeCredential = nodeCredential;
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
}

