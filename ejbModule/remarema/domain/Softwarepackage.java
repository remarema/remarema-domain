package remarema.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="softwarepackage")
public class Softwarepackage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="softwarepackageID")
	private int softwarepackageID;
	
	@Column(name="softwarepackageName")
	private String softwarepackageName;
	
	

	@OneToMany(mappedBy = "softwarepackage", fetch = FetchType.LAZY)
	private Set<Softwareversion> softwareversions;
	
	public Set<Softwareversion> getSoftwareversion(){
		return softwareversions;
	}
	public void setSoftwareversion(Set<Softwareversion> softwareversions){
		this.softwareversions = softwareversions;
	}
	
	
	

	public int getSoftwarepackageID() {
		return softwarepackageID;
	}

	public void setSoftwarepackageID(int softwarepackageID) {
		this.softwarepackageID = softwarepackageID;
	}

	public String getSoftwarepackageName() {
		return softwarepackageName;
	}

	public void setSoftwarepackageName(String softwarepackageName) {
		this.softwarepackageName = softwarepackageName;
	}
	
	/*
	private Set<Softwareversion> softwareversions = new HashSet<Softwareversion>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy="softwarepackage")
	public Set<Softwareversion> getSoftwareversion(){
		return softwareversions;
	}
	public void setSoftwareversion(Set<Softwareversion> softwareversions){
		this.softwareversions = softwareversions;
	}
	*/
	
}
