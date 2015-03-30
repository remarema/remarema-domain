package remarema.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="softwareversion")
public class Softwareversion implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="softwareID")
	private int softwareID;
	
	@Column(name="versionName")
	private String versionName;
	
	@Column(name="softwarePath")
	private String softwarePath;
	

	@ManyToMany
	@JoinTable(name="nodes_has_softwareversion")
	Set<Node> nodes;
	
	public Set<Node> getNode(){
		return nodes;
	}
	public void setNode(Set<Node> nodes){
		this.nodes = nodes;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Softwarepackage softwarepackage;
	
	public Softwarepackage getSoftwarepackage(){
		return softwarepackage;
	}
	public void setSoftwarepackage(Softwarepackage softwarepackage){
		this.softwarepackage = softwarepackage;
	}
	
	public Softwareversion(){
		
	}
	public Softwareversion(String path, Softwarepackage packageID){
		
	}
	
	public int getSoftwareID() {
		return softwareID;
	}
	public void setSoftwareID(int softwareID) {
		this.softwareID = softwareID;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getSoftwarePath() {
		return softwarePath;
	}
	public void setSoftwarePath(String softwarePath) {
		this.softwarePath = softwarePath;
	}
	
	
	
}
