package remarema.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

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
@Table(name = "deploy")
public class Deploy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "deployID")
	private int deployID;

	@Column(name = "deployDateTime")
	private Date deployDateTime;

	@Column(name = "installationDateTime")
	private Date installationDateTime;

	public Deploy(){
		
	}
	
	public Deploy(Softwareversion softwareversion){
		this.softwareversion = softwareversion;
	}
	
	@ManyToMany
	@JoinTable(name = "networks_has_deploy")
	ArrayList<Network> networks;

	public ArrayList<Network> getNetworks() {
		return networks;
	}

	public void setNetworks(ArrayList<Network> networks) {
		this.networks = networks;
	}
	
	@JoinColumn(name = "softwareversion_softwareID", referencedColumnName = "softwareID")
	@ManyToOne(fetch = FetchType.LAZY)
	private Softwareversion softwareversion;

	public Softwareversion getSoftwareversion() {
		return softwareversion;
	}

	public void setSoftwareversion(Softwareversion softwareversion) {
		this.softwareversion = softwareversion;
	}
	
	public boolean hasSoftwareversion() {
		return softwareversion != null;
	}

	public int getDeployID() {
		return deployID;
	}

	public void setDeployID(int deployID) {
		this.deployID = deployID;
	}

	public Date getDeployDateTime() {
		return deployDateTime;
	}

	public void setDeployDateTime(Date deployDateTime) {
		this.deployDateTime = deployDateTime;
	}

	public Date getInstallationDateTime() {
		return installationDateTime;
	}

	public void setInstallationDateTime(Date installationDateTime) {
		this.installationDateTime = installationDateTime;
	}

}
