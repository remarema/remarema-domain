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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Diese Klasse wurde mit <code>@Entity</code> annotiert und stellt somit eine
 * Tabelle in der Datenbank dar. Sie enhält alle Datenfelder der Tabelle
 * <code>softwarepackage</code>. Des Weiteren gibt es diverse get- und
 * set-Methoden.
 * 
 * Außerdem enthält die Klasse noch eine Beziehungen zur Tabelle
 * <code>softwareversion</code>.
 * 
 * @author Rebecca van Langelaan
 *
 */
@Entity
@Table(name = "softwarepackage")
public class Softwarepackage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "softwarepackageID")
	private int softwarepackageID;

	@Column(name = "softwarepackageName")
	private String softwarepackageName;

	@OneToMany(mappedBy = "softwarepackage", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Softwareversion> softwareversions;

	public Set<Softwareversion> getSoftwareversion() {
		return softwareversions;
	}

	public void setSoftwareversion(Set<Softwareversion> softwareversions) {
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

}
