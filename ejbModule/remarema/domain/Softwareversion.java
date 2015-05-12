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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "softwareversion")
public class Softwareversion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "softwareID")
	private int softwareID;

	@Column(name = "versionName")
	private String versionName;

	@Column(name = "softwarePath")
	private String softwarePath;

	public Softwareversion() {

	}

	public Softwareversion(String versionName, Softwarepackage softwarepackage) {
		this.versionName = versionName;
		this.softwarepackage = softwarepackage;
	}

	@ManyToMany
	@JoinTable(name = "nodes_has_softwareversion")
	Set<Node> nodes;

	public Set<Node> getNode() {
		return nodes;
	}

	public void setNode(Set<Node> nodes) {
		this.nodes = nodes;
	}

	@JoinColumn(name = "softwarepackage_softwarepackageID", referencedColumnName = "softwarepackageID")
	@ManyToOne(fetch = FetchType.LAZY)
	private Softwarepackage softwarepackage;

	public Softwarepackage getSoftwarepackage() {
		return softwarepackage;
	}

	public void setSoftwarepackage(Softwarepackage softwarepackage) {
		this.softwarepackage = softwarepackage;
	}

	public boolean hasPackage() {
		return softwarepackage != null;
	}

	public int getPackageID() {
		if (softwarepackage == null) {
			throw new IllegalStateException("softwareversion hat kein package!");
		}
		return softwarepackage.getSoftwarepackageID();
	}

	@OneToMany(mappedBy = "softwareversion", fetch = FetchType.LAZY)
	private Set<Deploy> deploy;

	public Set<Deploy> getDeploy() {
		return deploy;
	}

	public void setDeploy(Set<Deploy> deploy) {
		this.deploy = deploy;
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
