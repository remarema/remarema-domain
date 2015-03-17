package remarema.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table (name="deploy")
public class Deploy implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column (name="deployID")
	private int deployID;
	
	@Column (name="deployDateTime")
	private String deployDateTime;
	
	@Column (name="installationDateTime")
	private String installationDateTime;
	
	/*
	@ManyToMany
	Set<Node> nodes;
	public Set<Node> getNode(){
		return nodes;
	}
	public void setNode(Set<Node> nodes){
		this.nodes = nodes;
	}
	*/

	public int getDeployID() {
		return deployID;
	}

	public void setDeployID(int deployID) {
		this.deployID = deployID;
	}

	public String getDeployDateTime() {
		return deployDateTime;
	}

	public void setDeployDateTime(String deployDateTime) {
		this.deployDateTime = deployDateTime;
	}

	public String getInstallationDateTime() {
		return installationDateTime;
	}

	public void setInstallationDateTime(String installationDateTime) {
		this.installationDateTime = installationDateTime;
	}
	
}
