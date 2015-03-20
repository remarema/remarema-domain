package remarema.domain;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Die Klasse Network ist bla bla.
 * 
 * Im Detail usw, usw.<code>new Network()</code>
 * <pre>
 * this is a    x
 * look u  a    e
 * </pre>
 * @author Rebecca vanLangelaan
 *
 */
@Entity
@Table(name = "networks")
public class Network implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue
	@Column(name="networkID")
	private int networkID;
	
	@Column(name="networkName")
	private String networkName;
	
	@Column (name="networkParentID")
	private int networkParentID;
	
	
	/*
	@OneToMany(mappedBy = "network", fetch = FetchType.LAZY)
	private Set<Node> nodes;
	
	public Set<Node> getNode(){
		return nodes;
	}
	
	public void setNode(Set<Node> nodes){
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

	/**
	 * 
	 * @return
	 */
	public String getNetworkName() {
		return networkName;
	}

	/**
	 * ahgsjhdgfjasdhfgdhagjdfgjsdf
	 * @param networkName
	 * @
	 */
	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public int getNetworkParentID() {
		return networkParentID;
	}

	public void setNetworkParentID(int networkParentID) {
		this.networkParentID = networkParentID;
	}
	
	@Override
	public String toString(){
		return getNetworkID() + ", " + getNetworkName() + ", " + getNetworkParentID();
	}
}
