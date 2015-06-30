package remarema.services.software;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import remarema.api.network.NodeDetail;
import remarema.api.software.CreateSoftwareversion;
import remarema.api.software.PackageDetail;
import remarema.api.software.UpdateVersion;
import remarema.api.software.VersionDetail;
import remarema.domain.Network;
import remarema.domain.Node;
import remarema.domain.Softwarepackage;
import remarema.domain.Softwareversion;
import remarema.services.network.IPAddress;

/**
 * Das SoftwareversionServiceBean stellt alle notwendigen Methoden für die
 * Verwaltung von Softwareversionen bereit. Diese Methoden umfassen das Anlegen,
 * Updaten, und Löschen von Softwareversionen.
 * 
 * @author Rebecca van Langelaan
 */
@Stateless
@LocalBean
public class SoftwareversionServiceBean {

	@PersistenceContext
	protected EntityManager em;

	/**
	 * EJB default constructor.
	 */
	public SoftwareversionServiceBean() {
	}

	// Package-protected; dient nur zum Testen!
	SoftwareversionServiceBean(EntityManager em) {
		this.em = em;
	}

	/**
	 * Die Methode <code>execute()</code> dient zur Speicherung einer neuen
	 * Softwareversion in der Datenbank. Dazu wird beim Aufruf der Methode ein
	 * CreateSoftwareversion-Objekt mitübergeben. Dieses beinhaltet die selben
	 * Felder wie die Entity <code>Softwareversion</code>.
	 * 
	 * Zuerst wird der Name des Packages ausgelesen und als String gespeichert.
	 * Dann wird die Methode {@link #findSoftwarePackage(String)} aufgerufen.
	 * 
	 * Danach wird ein neues Softwareversion-Objekt angelegt dem man den
	 * Softwarenamen und das dazugehörige Package mitgibt. Anschließend werden
	 * mit den set-Methoden die Datenfelder gesetzt. Mit <code>em.persist</code>
	 * wird der Datensatz in der Datenbank abgespeichert.
	 * 
	 * @param command
	 */
	public void execute(CreateSoftwareversion command) {
		String pkgName = command.getSoftwarepackageName();
		Softwarepackage pkg = findSoftwarePackage(pkgName);

		Softwareversion version = new Softwareversion(
				command.getSoftwareName(), pkg);
		version.setSoftwarePath(command.getSoftwarePath());
		pkg.getSoftwareversion().add(version);
		em.persist(version);
	}

	/**
	 * Die Methode benötigt bei ihrem Aufruf als Parameter den Package-Namen.
	 * Dann wird ine Query ausgeführt, wo nach dem passenden Datensatz in der
	 * Datenbank gesucht wird. Danach wird der gefundene Datensatz an den
	 * Benutzer zurückgegeben.
	 * 
	 * @param pkgName
	 * @return Softwarepackage-Objekt
	 */
	private Softwarepackage findSoftwarePackage(String pkgName) {
		TypedQuery<Softwarepackage> query = em
				.createQuery(
						"SELECT o From Softwarepackage o WHERE o.softwarepackageName = :name",
						Softwarepackage.class);
		query.setParameter("name", pkgName);
		return query.getSingleResult();
	}

	/**
	 * Die Methode <code>updateVersion()</code> benötigt als Parameter ein
	 * UpdateVersion-Objekt. Es wird mit <code>em.find()</code> der passende
	 * Datensatz zur Id in der Datenbank gesucht. Danach wird der Name des
	 * Softwarepackages und der Pfad der Software gesetzt. Danach wird der
	 * PackageName ausgelesen. Mit {@link #findSoftwarePackage(String)} wird
	 * nach dem Softwarepackage in der Datenbank gesucht. Das Package wird dann
	 * ebenfalls mit der set-Methode gesetzt.
	 *
	 * Mit <code>em.flush()</code> wird der Datensatz in der Datenbank
	 * upgedated.
	 * 
	 * @param command
	 */
	public void updateVersion(UpdateVersion command) {
		Softwareversion version = em.find(Softwareversion.class,
				command.getSoftwareID());
		version.setVersionName(command.getSoftwareName());
		version.setSoftwarePath(command.getSoftwarePath());

		String pkgName = command.getSoftwarepackageName();
		Softwarepackage pkg = findSoftwarePackage(pkgName);
		version.setSoftwarepackage(pkg);

		em.flush();
	}

	/**
	 * Diese Methode dient zum Löschen einer Softwareversion. Beim Aufruf der
	 * Methode wird ein VersionDetail-Objekt mitübergeben. Von diesem wird die
	 * Id ausgelesen und mit <code>em.find()</code> wird der passende Datensatz
	 * in der Datenbank gesucht.
	 * 
	 * Ist das gesuchte Objekt nicht null, wird der Datensatz mit
	 * <code>em.remove()</code> gelöscht.
	 * 
	 * @param command
	 */
	public void removeVersion(VersionDetail command) {
		Softwareversion version = em.find(Softwareversion.class,
				command.getSoftwareID());
		if (version != null) {
			em.remove(version);
		}
	}

	/**
	 * Diese Methode benötigt als Parameter ein VersionDetail-Objekt, bei dem
	 * die Id der Softwareversion gesetzt sein muss. Zuerst wird die Id der
	 * Softwareversion ausgelesen, danach wird die Methode
	 * {@link #mapVersionToVersionDetail(Softwareversion)} aufgerufen.
	 * 
	 * @param detail
	 * @return VersionDetail-Objekt
	 */
	public VersionDetail getVersionDetailForVersionID(VersionDetail detail) {
		int softwareID = detail.getSoftwareID();
		return mapVersionToVersionDetail(loadSoftware(softwareID));

	}

	/**
	 * Diese Methode "wandelt" ein einzelnes Softwareversion-Objekt in ein
	 * VersionDetail-Objekt um.
	 * 
	 * @param softwareversion
	 * @return VersionDetail-Objekt.
	 */
	VersionDetail mapVersionToVersionDetail(Softwareversion softwareversion) {
		VersionDetail version = new VersionDetail();
		version.setSoftwareID(softwareversion.getSoftwareID());
		version.setSoftwareName(softwareversion.getVersionName());
		version.setSoftwarePath(softwareversion.getSoftwarePath());

		Softwarepackage pkg = softwareversion.getSoftwarepackage();
		if (pkg != null) {
			version.setSoftwarepackageID(pkg.getSoftwarepackageID());
			version.setSoftwarepackageName(pkg.getSoftwarepackageName());
		}
		return version;
	}

	/**
	 * Die Methode <code>loadSoftware()</code> benötigt als Parameter die Id
	 * einer Softwareversion. Das gefundene Objekt wird dem Benutzer
	 * zurückgegeben.
	 * 
	 * @param softwareID
	 * @return Softwareversion-Objekt
	 */
	Softwareversion loadSoftware(int softwareID) {
		TypedQuery<Softwareversion> query = em.createQuery(
				"SELECT o FROM Softwareversion o WHERE o.softwareID ="
						+ softwareID, Softwareversion.class);
		return query.getSingleResult();
	}

	/**
	 * Diese Methode wird auf der Weboberfläche aufgerufen, um alle
	 * Softwareversionen in einer Übersicht darstellen zu können. Zuerst wird
	 * die Methode {@link #loadAllSoftware()} aufgerufen, danach die Methode
	 * {@link #mapVersionsToVersionDetail(List)}.
	 * 
	 * @return Zurückgegeben wird eine Liste von PackageDetail-Objekten.
	 */
	public List<VersionDetail> getVersionDetailForAllVersions(
			PackageDetail packageDetail) {
		List<Softwareversion> results = loadAllSoftware(packageDetail);
		return mapVersionsToVersionDetail(results);
	}

	/**
	 * Diese Methode "wandelt" die aus der Datenbank ausgelesenen
	 * Softwareversionen in VersionDetail-Objekte um. Diese werden dann wiederum
	 * in eine Liste gespeichert.Diese müssen in VersionDetail-Objekte
	 * umgewandelt werden.
	 * 
	 * @param results
	 * @return Liste vom Typ VersionDetail
	 */
	List<VersionDetail> mapVersionsToVersionDetail(List<Softwareversion> results) {
		List<VersionDetail> versionDetail = new ArrayList<VersionDetail>();

		for (Softwareversion result : results) {
			VersionDetail detail = new VersionDetail();
			detail.setSoftwareID(result.getSoftwareID());
			detail.setSoftwareName(result.getVersionName());
			detail.setSoftwarePath(result.getSoftwarePath());

			if (result.hasPackage()) {
				Softwarepackage pkg = result.getSoftwarepackage();
				detail.setSoftwarepackageID(pkg.getSoftwarepackageID());
				detail.setSoftwarepackageName(pkg.getSoftwarepackageName());
			}
			versionDetail.add(detail);
		}
		return versionDetail;

	}

	/**
	 * Diese Methode ließt mit Hilfe einer Query alle Datensätze, die in der
	 * Tabelle <code>softwareversion</code> gespeichert wurden, aus der
	 * Datenbank aus. Die Datensätze werden in eine List gespeichert - diese
	 * wird an den Benutzer zurückgegeben.
	 * 
	 * @return
	 */
	List<Softwareversion> loadAllSoftware(PackageDetail packageDetail) {
		int packageID = packageDetail.getSoftwarepackageID();
		TypedQuery<Softwareversion> query = em.createQuery(
				"SELECT o From Softwareversion o WHERE o.softwarepackage.softwarepackageID = "
						+ packageID + " ORDER BY o.versionName ",
				Softwareversion.class);
		List<Softwareversion> results = query.getResultList();
		return results;
	}

}
